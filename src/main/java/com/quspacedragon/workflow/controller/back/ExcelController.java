package com.quspacedragon.workflow.controller.back;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.common.SegmentLock;
import com.quspacedragon.workflow.common.SpringContext;
import com.quspacedragon.workflow.entity.BaseEntity;
import com.quspacedragon.workflow.entity.Building;
import com.quspacedragon.workflow.enums.UploadTypeEnum;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.util.LogFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Title: ExcelController
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/8/10
 */
@Api("excel导入导出类")
@Controller
@Log4j
public class ExcelController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelController.class);
    private SegmentLock<String> lock = new SegmentLock();

    @ApiOperation(value = "excel通用导入方法", response = Building.class)
    @RequestMapping(value = "/v1/import", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "文件", required = true, dataType = "file"),
            @ApiImplicitParam(name = "上传类型", required = true, dataType = "String", allowableValues = "{productType}")
    })
    @ResponseBody
    public Result excelImport(@RequestParam(value = "file") MultipartFile file,
                              @RequestParam(value = "uploadType") String uploadType,
                              HttpServletRequest httpServletRequest) {

        if (file == null || file.getSize() == 0) {//文件大小
            return ApiResultUtils.failResult("请选择需要导入的文件！");
        }
        String originalFilename = file.getOriginalFilename();
        if (!(originalFilename.endsWith(".xls") || originalFilename.endsWith(".xlsx"))) {//文件类型
            return ApiResultUtils.failResult("该文件不是正规的Excel文件，目前只支持xls、xlsx格式，请检查!");
        }
        String key = uploadType + originalFilename;
        try {
            //文件内容校验f
            InputStream in = file.getInputStream();
            XSSFWorkbook workbook;
            try {
                workbook = (XSSFWorkbook) WorkbookFactory.create(in);
            } catch (Exception e) {
                LOGGER.error("上传Excel出错", e);
                return ApiResultUtils.failResult("请使用Excel2007及之后版本。");
            }
            Sheet sheet = workbook.getSheetAt(0);//第一个表格页
            if (sheet.getLastRowNum() < 1) {//文件内容
                return ApiResultUtils.failResult("文件内未找到需要导入的信息，请检查！");
            }
            if (sheet.getPhysicalNumberOfRows() > 50001) {
                return ApiResultUtils.failResult("文件内容超出限制，每次导入最多允许5W条数据！");
            }
            UploadTypeEnum uploadTypeEnum = UploadTypeEnum.findByType(uploadType);
            if (uploadTypeEnum == null) {
                return ApiResultUtils.failResult("参数不合法");
            }
            if (!lock.lock(key)) {
                return ApiResultUtils.failResult("数据正在处理中，请稍后再试");
            }
            String name = uploadTypeEnum.getName();
            Class clazz = uploadTypeEnum.getClazz();

            Map<String, UploadTypeEnum.Title> titleMap = uploadTypeEnum.getTitleMap();
            List importList = Lists.newArrayList();
            Row row1 = sheet.getRow(2);
            Iterator<Cell> cellIterator1 = row1.cellIterator();

            //获取第二行的列表名字
            Map<Integer, String> columnMap = Maps.newHashMap();
            int columnNo = 0;
            while (cellIterator1.hasNext()) {
                Cell cell = cellIterator1.next();
                try {
                    String column = cell.getStringCellValue();
                    if (StringUtils.isEmpty(column)) {
                        break;
                    }
                    columnMap.put(columnNo, column);
                    columnNo++;
                } catch (Exception e) {
                    log.error(String.format("第%s行格式异常！", columnNo), e);
                }
            }
            if (columnMap.size() == 0) {
                return ApiResultUtils.failResult("列名格式有错，请使用标准模板导入");
            }

            //  开始处理excel数据
            for (int i = 3; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Object entity = clazz.newInstance();

                Iterator<Cell> cellIterator = row.cellIterator();
                columnNo = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    try {
                        String value = cell.getStringCellValue();
                        if (StringUtils.isEmpty(value)) {
                            break;
                        }
                        String fieldName = columnMap.get(columnNo);
                        Field field = clazz.getField(fieldName);
                        Class<?> type = field.getType();
                        field.set(entity, type.cast(value));
                        columnNo++;
                    } catch (Exception e) {
                        log.error(String.format("第%s行手机号码格式异常！", i), e);
                    }
                }
                importList.add(entity);
            }

            if (CollectionUtils.isEmpty(importList)) {
                return ApiResultUtils.failResult("导入数据为空，请检查文件是否正确");
            }
            IService service = getBean(clazz.getSimpleName());
            service.insertOrUpdateBatch(importList);

            return ApiResultUtils.successResult(importList.size());
        } catch (Exception e) {
            LOGGER.error("保存导入文件异常", e);
        } finally {
            lock.unlock(key);
        }
        return ApiResultUtils.failResult("导入文件失败");

    }

    private IService getBean(String simpleClassName) {
        String big = simpleClassName.substring(0, 1);// 获取首字母（类名首字母大写）
        String small = big.toLowerCase();// 将首字母变为小写
        String smallName = small + simpleClassName.substring(1);// 获得已小写字母开头的类名
        return (IService) SpringContext.getBean(smallName + "Service");
    }


    @ApiOperation(value = "excel通用导出方法")
    @RequestMapping(value = "/v1/export", method = RequestMethod.GET)
    @ResponseBody
    public Result export(
            @RequestParam(value = "uploadType") String uploadType,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        UploadTypeEnum uploadTypeEnum = UploadTypeEnum.findByType(uploadType);
        if (uploadTypeEnum == null) {
            return ApiResultUtils.failResult("参数不合法");
        }
        String name = uploadTypeEnum.getName();
        Class clazz = uploadTypeEnum.getClazz();

        Map<String, UploadTypeEnum.Title> titleMap = uploadTypeEnum.getTitleMap();
        IService service = getBean(clazz.getSimpleName());
        EntityWrapper entityWrapper = new EntityWrapper();
        List list = service.selectList(entityWrapper);
        //组装excel数据
        HSSFWorkbook wb = this.exportBatchDetails(list, uploadTypeEnum);

//        HSSFWorkbook wb = new HSSFWorkbook();
//        HSSFSheet sheet = wb.createSheet("测试");
//        HSSFRow row = sheet.createRow(1);
//        HSSFCell cell = row.createCell(1);
//        cell.setCellValue("222");

        String excelFileName = name + "【导出明细数据】.xls";
        String userAgent = request.getHeader("User-Agent");
        // name.getBytes("UTF-8")处理safari的乱码问题
        byte[] bytes = userAgent.contains("MSIE") ? excelFileName.getBytes() : excelFileName.getBytes("UTF-8");
        // 各浏览器基本都支持ISO编码
        excelFileName = new String(bytes, "ISO-8859-1");
        // 文件名外的双引号处理firefox的空格截断问题
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", excelFileName));
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
        return null;
    }


    /**
     * excel加载数据
     */

    private HSSFWorkbook exportBatchDetails(List<? extends BaseEntity> list, UploadTypeEnum uploadTypeEnum) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(uploadTypeEnum.getName());
        Class clazz = uploadTypeEnum.getClazz();
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFRow rowTitle = sheet.createRow(1);
        HSSFRow rowName = sheet.createRow(2);
        Map<String, UploadTypeEnum.Title> titleMap = uploadTypeEnum.getTitleMap();
        int columnNo = 0;
        for (Map.Entry<String, UploadTypeEnum.Title> entry : titleMap.entrySet()) {
            UploadTypeEnum.Title value = entry.getValue();
            HSSFCell cellTitle = rowTitle.createCell(columnNo);
            cellTitle.setCellValue(value.getName());
            cellTitle.setCellStyle(style);
            HSSFCell cellName = rowName.createCell(columnNo);
            cellName.setCellValue(value.getColumn());
            cellName.setCellStyle(style);
            columnNo++;
        }


        for (int i = 0; i < list.size(); i++) {
            BaseEntity baseEntity = list.get(0);
            HSSFRow row  = sheet.createRow(i + 3);
            columnNo = 0;
            for (Map.Entry<String, UploadTypeEnum.Title> entry : titleMap.entrySet()) {
                try {
                    UploadTypeEnum.Title value = entry.getValue();
                    HSSFCell cellTitle = row.createCell(columnNo);
                    cellTitle.setCellValue("11111");
//                    cellTitle.setCellValue(value.getValue().apply(getValue(value.getColumn(), clazz, baseEntity)));
                    cellTitle.setCellStyle(style);
                    columnNo++;
                } catch (Exception e) {
                    LOGGER.error(LogFormat.init("导出异常").add("entity", baseEntity).buildLogMsg(), e);
                    continue;
                }
            }
        }
        //列宽
        sheet.setDefaultColumnWidth(20);
        return wb;
    }

    private Object getValue(String name, Class clazz, BaseEntity baseEntity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String getMethodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
        Method method = clazz.getMethod(getMethodName);
        return method.invoke(baseEntity);
    }


}
