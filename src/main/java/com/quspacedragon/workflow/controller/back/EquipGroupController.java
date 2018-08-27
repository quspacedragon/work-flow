package com.quspacedragon.workflow.controller.back;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.quspacedragon.workflow.annoation.LoginIntercept;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.Building;
import com.quspacedragon.workflow.entity.EquipGroup;
import com.quspacedragon.workflow.service.IEquipGroupService;
import com.quspacedragon.workflow.util.ApiResultUtils;
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
 *  前端控制器
 * </p>
 *
 * @author quspacedragon
 * @since 2018-08-27
 */
@Controller
@RequestMapping("/equipGroup")
public class EquipGroupController {
    @Resource
    IEquipGroupService equipGroupService;

    @ApiOperation(value = "设备分组保存", response = Building.class)
    @RequestMapping(value = "/v1/save", method = {RequestMethod.POST})
    @ApiImplicitParam(name = "equipGroup", value = "创建", required = true, dataType = "EquipGroup")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result save(@RequestBody(required = true) EquipGroup equipGroup,
                       HttpServletRequest httpServletRequest) {
        boolean insert = equipGroupService.insert(equipGroup);
        if (!insert) {
            return ApiResultUtils.failResult("保存失败");
        }
        return ApiResultUtils.successResult(equipGroupService.selectById(equipGroup.getId()));
    }

    @ApiOperation(value = "设备分组删除", response = Result.class)
    @RequestMapping(value = "/v1/delete", method = {RequestMethod.GET})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@ApiParam(value = "id", required = true) @RequestParam(value = "id") Integer id,
                         HttpServletRequest httpServletRequest) {
        return ApiResultUtils.successResult(equipGroupService.deleteById(id));
    }

    @ApiOperation(value = "设备分组列表", response = Page.class)
    @RequestMapping(value = "/v1/list", method = {RequestMethod.GET})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result list(
            @ApiParam(value = "名称或者编码", required = false) @RequestParam(value = "code", required = false) String code,
            @ApiParam(value = "页码", required = false) @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            HttpServletRequest httpServletRequest) {
        Page<EquipGroup> buildingPage = new Page<>();
        EntityWrapper<EquipGroup> buildingEntityWrapper = new EntityWrapper<>();
        if (StringUtils.isNotEmpty(code)) {
            buildingEntityWrapper.like(true, EquipGroup.NAME, code);
            buildingEntityWrapper.or();
            buildingEntityWrapper.like(true, EquipGroup.CODE, code);
            buildingEntityWrapper.andNew();
        }
        buildingPage.setCurrent(pageNo);
        Page<EquipGroup> page = equipGroupService.selectPage(buildingPage, buildingEntityWrapper);
        int num = equipGroupService.selectCount(buildingEntityWrapper);
        page.setTotal(num);
        return ApiResultUtils.successResult(page);
    }
}
