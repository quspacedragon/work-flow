package com.quspacedragon.workflow.controller.back;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.quspacedragon.workflow.annoation.LoginIntercept;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.Building;
import com.quspacedragon.workflow.entity.ProductType;
import com.quspacedragon.workflow.service.IBuildingService;
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
 * @since 2018-07-24
 */
@Api("楼栋相关")
@Controller
@RequestMapping("/back/building")
@LoginIntercept
public class BuildingController {

    @Resource
    IBuildingService buildingService;

    @ApiOperation(value = "楼栋保存", response = Building.class)
    @RequestMapping(value = "/v1/save", method = {RequestMethod.POST})
    @ApiImplicitParam(name = "building", value = "创建", required = true, dataType = "Building")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result save(@RequestBody(required = true) Building building,
                       HttpServletRequest httpServletRequest) {
        Building byCode = buildingService.findByCode(building.getCode());
        if (byCode != null) {
            return ApiResultUtils.failResult("重复编码");
        }
        boolean insert = buildingService.insert(building);
        if (!insert) {
            return ApiResultUtils.failResult("保存失败");
        }
        return ApiResultUtils.successResult(buildingService.selectById(building.getId()));
    }

    @ApiOperation(value = "楼栋删除", response = Result.class)
    @RequestMapping(value = "/v1/delete", method = {RequestMethod.GET, RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@ApiParam(value = "id", required = true) @RequestParam(value = "id") Integer id,
                         HttpServletRequest httpServletRequest) {
        return ApiResultUtils.successResult(buildingService.deleteById(id));
    }

    @ApiOperation(value = "产品类型列表", response = Page.class)
    @RequestMapping(value = "/v1/list", method = {RequestMethod.GET, RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result list(
            @ApiParam(value = "名称或者编码", required = false) @RequestParam(value = "code", required = false) String code,
            @ApiParam(value = "页码", required = false) @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            HttpServletRequest httpServletRequest) {
        Page<Building> buildingPage = new Page<>();
        EntityWrapper<Building> buildingEntityWrapper = new EntityWrapper<>();
        if (StringUtils.isNotEmpty(code)) {
            buildingEntityWrapper.like(true, ProductType.NAME, code);
            buildingEntityWrapper.or();
            buildingEntityWrapper.like(true, ProductType.CODE, code);
        }
        buildingPage.setCurrent(pageNo);
        Page<Building> page = buildingService.selectPage(buildingPage, buildingEntityWrapper);
        int num = buildingService.selectCount(buildingEntityWrapper);
        page.setTotal(num);
        return ApiResultUtils.successResult(page);
    }

}
