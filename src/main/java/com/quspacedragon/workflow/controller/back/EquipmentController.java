package com.quspacedragon.workflow.controller.back;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.quspacedragon.workflow.annoation.LoginIntercept;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.Building;
import com.quspacedragon.workflow.entity.Equipment;
import com.quspacedragon.workflow.entity.ProductType;
import com.quspacedragon.workflow.service.IBuildingService;
import com.quspacedragon.workflow.service.IEquipmentService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author quspacedragon
 * @since 2018-08-21
 */
@Api("设备类型管理")
@Controller
@RequestMapping("/back/equipment")
public class EquipmentController {
    @Resource
    IEquipmentService equipmentService;

    @ApiOperation(value = "设备类型保存", response = Building.class)
    @RequestMapping(value = "/v1/save", method = {RequestMethod.POST})
    @ApiImplicitParam(name = "equipment", value = "创建", required = true, dataType = "Equipment")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result save(@RequestBody(required = true) Equipment equipment,
                       HttpServletRequest httpServletRequest) {
        Integer id = equipment.getId();
        if (id == null) {
            String parentCode = StringUtils.EMPTY;
            Integer parentId = equipment.getParentId();
            Integer maxCode = equipmentService.findMaxCode(equipment.getLevel(), parentId);
            if (parentId != null) {
                Equipment parentEquipment = equipmentService.selectById(parentId);
                parentCode = parentEquipment.getCode();
            }
            equipment.setCode(createCode(maxCode, parentCode));
        }


        boolean insert = equipmentService.insert(equipment);
        if (!insert) {
            return ApiResultUtils.failResult("保存失败");
        }
        return ApiResultUtils.successResult(equipmentService.selectById(equipment.getId()));
    }

    private String createCode(Integer levlelCode, String parentCode) {
        return parentCode + "." + levlelCode;
    }

    @ApiOperation(value = "设备类型删除", response = Result.class)
    @RequestMapping(value = "/v1/delete", method = {RequestMethod.GET, RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@ApiParam(value = "id", required = true) @RequestParam(value = "id") Integer id,
                         HttpServletRequest httpServletRequest) {
        return ApiResultUtils.successResult(equipmentService.deleteById(id));
    }

    @ApiOperation(value = "设备类型查询", response = Page.class)
    @RequestMapping(value = "/v1/list", method = {RequestMethod.GET, RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result list(
//            @ApiParam(value = "名称或者编码", required = false) @RequestParam(value = "code", required = false) String code,
            @ApiParam(value = "页码", required = false) @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            HttpServletRequest httpServletRequest) {
        Page<Equipment> buildingPage = new Page<>();
        EntityWrapper<Equipment> buildingEntityWrapper = new EntityWrapper<>();
//        if (StringUtils.isNotEmpty(code)) {
//            buildingEntityWrapper.like(true, ProductType.NAME, code);
//            buildingEntityWrapper.or();
//            buildingEntityWrapper.like(true, ProductType.CODE, code);
//            buildingEntityWrapper.andNew();
//        }
        buildingPage.setCurrent(pageNo);
        Page<Equipment> page = equipmentService.selectPage(buildingPage, buildingEntityWrapper);
        int num = equipmentService.selectCount(buildingEntityWrapper);
        page.setTotal(num);
        return ApiResultUtils.successResult(page);
    }

}
