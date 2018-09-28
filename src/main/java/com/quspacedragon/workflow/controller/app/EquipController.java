package com.quspacedragon.workflow.controller.app;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.quspacedragon.workflow.annoation.LoginIntercept;
import com.quspacedragon.workflow.common.LoginHelper;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.CheckProject;
import com.quspacedragon.workflow.entity.CheckRecord;
import com.quspacedragon.workflow.entity.EquipEntity;
import com.quspacedragon.workflow.enums.CheckRecordStatusEnum;
import com.quspacedragon.workflow.service.ICheckProjectService;
import com.quspacedragon.workflow.service.ICheckRecordService;
import com.quspacedragon.workflow.service.IEquipEntityService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.vo.AppEquipVo;
import com.quspacedragon.workflow.vo.EquipCheckDetail;
import com.quspacedragon.workflow.vo.MeterDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Title: LoginController
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/7/25
 */
@Api("app-设备")
@Controller("appEquipController")
@RequestMapping("/app/equip")
@LoginIntercept
public class EquipController {

    @Resource
    ICheckRecordService checkRecordService;
    @Resource
    LoginHelper loginHelper;

    @Resource
    IEquipEntityService equipEntityService;
    @Resource
    ICheckProjectService checkProjectService;


    @ApiOperation(value = "设备巡查列表", response = AppEquipVo.class)
    @RequestMapping(value = "/v1/list", method = {RequestMethod.GET, RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result list(
            @ApiParam(value = "名称或者编码", required = false) @RequestParam(value = "code", required = false) String code,
            @ApiParam(value = "页码", required = false) @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            HttpServletRequest httpServletRequest,
            @ApiParam(value = "每页条数", required = false) @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @ApiParam(value = "token", required = true) @RequestParam(value = "token", required = true) String token
    ) {
        Integer loginUserId = loginHelper.getLoginUserId(token, httpServletRequest);

        Page<CheckRecord> buildingPage = new Page<>(pageNo, pageSize);
        CheckRecord checkRecord = new CheckRecord();
        checkRecord.setUserId(loginUserId);

        EntityWrapper<CheckRecord> buildingEntityWrapper = new EntityWrapper<>();
        buildingEntityWrapper.setEntity(checkRecord);
        long now = System.currentTimeMillis();
        buildingEntityWrapper.ge(CheckRecord.VALID_START_TIME, now);
        buildingEntityWrapper.le(CheckRecord.VALID_END_TIME, now);


        int num = checkRecordService.selectCount(buildingEntityWrapper);

        AppEquipVo appEquipVo = new AppEquipVo();
//        result.put("")
        appEquipVo.setTodoNum(num);
        return ApiResultUtils.successResult(appEquipVo);
    }

    @ApiOperation(value = "设备巡检", response = Boolean.class)
    @RequestMapping(value = "/v1/check", method = {RequestMethod.GET, RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result check(
            @ApiParam(value = "设备实体id", required = false) @RequestParam(value = "equipEntityId", required = true) Integer equipEntityId,
            HttpServletRequest httpServletRequest,
            @ApiParam(value = "token", required = true) @RequestParam(value = "token", required = true) String token
    ) {
        Integer loginUserId = loginHelper.getLoginUserId(token, httpServletRequest);

        Page<CheckRecord> buildingPage = new Page<>();
        CheckRecord checkRecord = new CheckRecord();
        checkRecord.setUserId(loginUserId);
        checkRecord.setEquipEntityId(equipEntityId);
        EntityWrapper<CheckRecord> buildingEntityWrapper = new EntityWrapper<>();
        buildingEntityWrapper.setEntity(checkRecord);
        long now = System.currentTimeMillis();
        buildingEntityWrapper.ge(CheckRecord.VALID_START_TIME, now);
        buildingEntityWrapper.le(CheckRecord.VALID_END_TIME, now);

        CheckRecord checkRecordResult = checkRecordService.selectOne(buildingEntityWrapper);
        if (checkRecordResult == null) {
            return ApiResultUtils.failResult("没有巡检任务");
        }
        Integer status = checkRecordResult.getStatus();
        if (CheckRecordStatusEnum.CHECKED.getStatus().equals(status)) {
            return ApiResultUtils.failResult("该设备已巡检过");
        }
        checkRecordResult.setStatus(CheckRecordStatusEnum.CHECKED.getStatus());
        checkRecordService.updateById(checkRecord);
        return ApiResultUtils.successResult(true);
    }


    @ApiOperation(value = "巡检详情", response = EquipCheckDetail.class)
    @RequestMapping(value = "/v1/detail", method = {RequestMethod.GET, RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result list(
            @ApiParam(value = "设备实体id", required = false) @RequestParam(value = "equipEntityId", required = true) Integer equipEntityId,
            @ApiParam(value = "token", required = true) @RequestParam(value = "token", required = true) String token,
            HttpServletRequest httpServletRequest) {

        EquipEntity equipEntity = equipEntityService.selectById(equipEntityId);
        CheckProject checkProject = checkProjectService.selectById(equipEntity.getCheckProjectId());
        CheckRecord nowCheckRecord = checkRecordService.findNowCheckRecord(equipEntityId);


        EquipCheckDetail equipCheckDetail = new EquipCheckDetail();
        equipCheckDetail.setAddress(equipEntity.getPosition());
        equipCheckDetail.setCheckMethod(checkProject.getCheckContent());
        equipCheckDetail.setCheckRequire(checkProject.getCheckRequire());
        if (nowCheckRecord != null) {
            equipCheckDetail.setCheckTime(nowCheckRecord.getCheckTime());
            equipCheckDetail.setDesc(nowCheckRecord.getAttribute());
            equipCheckDetail.setImgList(nowCheckRecord.getImgList());
            equipCheckDetail.setStatus(nowCheckRecord.getStatus());
            equipCheckDetail.setCheckRecordId(nowCheckRecord.getId());
        } else {
            equipCheckDetail.setStatus(CheckRecordStatusEnum.TODO.getStatus());
        }

        return ApiResultUtils.successResult(equipCheckDetail);
    }


    @ApiOperation(value = "巡检开工", response = EquipCheckDetail.class)
    @RequestMapping(value = "/v1/work", method = {RequestMethod.GET, RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result list(
            @ApiParam(value = "巡检记录id", required = true) @RequestParam(value = "checkRecordId", required = true) Integer checkRecordId,
            HttpServletRequest httpServletRequest) {

        CheckRecord checkRecord = checkRecordService.selectById(checkRecordId);
        checkRecord.setStatus(CheckRecordStatusEnum.TO_WORK.getStatus());

        checkRecordService.updateById(checkRecord);
        return ApiResultUtils.successResult(true);
    }


}
