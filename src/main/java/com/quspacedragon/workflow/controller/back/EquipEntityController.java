package com.quspacedragon.workflow.controller.back;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.quspacedragon.workflow.annoation.LoginIntercept;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.Building;
import com.quspacedragon.workflow.entity.CheckProject;
import com.quspacedragon.workflow.entity.EquipEntity;
import com.quspacedragon.workflow.entity.ProductType;
import com.quspacedragon.workflow.service.ICheckProjectService;
import com.quspacedragon.workflow.service.IEquipEntityService;
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
 * @since 2018-08-27
 */
@Controller
@Api("设备台账")
@RequestMapping("/equipEntity")
public class EquipEntityController {
    @Resource
    IEquipEntityService equipEntityService;

    @ApiOperation(value = "设备台账保存", response = Building.class)
    @RequestMapping(value = "/v1/save", method = {RequestMethod.POST})
    @ApiImplicitParam(name = "equipEntity", value = "创建", required = true, dataType = "equipEntity")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result save(@RequestBody(required = true) EquipEntity equipEntity,
                       HttpServletRequest httpServletRequest) {
        boolean insert = equipEntityService.insert(equipEntity);
        if (!insert) {
            return ApiResultUtils.failResult("保存失败");
        }
        return ApiResultUtils.successResult(equipEntityService.selectById(equipEntity.getId()));
    }

    @ApiOperation(value = "设备台账删除", response = Result.class)
    @RequestMapping(value = "/v1/delete", method = {RequestMethod.GET})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@ApiParam(value = "id", required = true) @RequestParam(value = "id") Integer id,
                         HttpServletRequest httpServletRequest) {
        return ApiResultUtils.successResult(equipEntityService.deleteById(id));
    }

    @ApiOperation(value = "设备台账列表", response = Page.class)
    @RequestMapping(value = "/v1/list", method = {RequestMethod.GET})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result list(
            @ApiParam(value = "名称或者编码", required = false) @RequestParam(value = "code", required = false) String code,
            @ApiParam(value = "页码", required = false) @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            HttpServletRequest httpServletRequest) {
        Page<EquipEntity> buildingPage = new Page<>();
        EntityWrapper<EquipEntity> buildingEntityWrapper = new EntityWrapper<>();
        if (StringUtils.isNotEmpty(code)) {
            buildingEntityWrapper.like(true, EquipEntity.NAME, code);
            buildingEntityWrapper.or();
            buildingEntityWrapper.like(true, EquipEntity.CODE, code);
            buildingEntityWrapper.andNew();
        }
        buildingPage.setCurrent(pageNo);
        Page<EquipEntity> page = equipEntityService.selectPage(buildingPage, buildingEntityWrapper);
        int num = equipEntityService.selectCount(buildingEntityWrapper);
        page.setTotal(num);
        return ApiResultUtils.successResult(page);
    }

    @ApiOperation(value = "变更责任人", response = EquipEntity.class)
    @RequestMapping(value = "/v1/updateDuty", method = {RequestMethod.POST})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result updateDuty(
            @ApiParam(value = "维保责任人", required = false) @RequestParam(value = "maintainId", required = false) Integer maintainId,
            @ApiParam(value = "巡检责任人", required = false) @RequestParam(value = "fixId", required = false) Integer fixId,
            @ApiParam(value = "主键id", required = false) @RequestParam(value = "id", required = false) Integer id,
            HttpServletRequest httpServletRequest) {
        EquipEntity equipEntity = equipEntityService.selectById(id);
        equipEntity.setMaintainId(maintainId);
        equipEntity.setFixId(fixId);
        boolean insert = equipEntityService.updateById(equipEntity);
        if (!insert) {
            return ApiResultUtils.failResult("修改失败");
        }
        return ApiResultUtils.successResult("");
    }

}
