package com.quspacedragon.workflow.controller.back;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.quspacedragon.workflow.annoation.LoginIntercept;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.Unit;
import com.quspacedragon.workflow.service.IUnitService;
import com.quspacedragon.workflow.service.IUnitService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
@Controller
@RequestMapping("/unit")
@Api("单元管理")
public class UnitController {
    @Resource
    IUnitService unitService;

    @ApiOperation(value = "单元保存", response = Unit.class)
    @RequestMapping(value = "/v1/save", method = {RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result save(@ApiParam(value = "单元", required = true) @RequestBody(required = true) Unit unit,
                       HttpServletRequest httpServletRequest) {
        boolean insert = unitService.insert(unit);
        if (!insert) {
            return ApiResultUtils.failResult("保存失败");
        }
        return ApiResultUtils.successResult(unitService.selectById(unit.getId()));
    }

    @ApiOperation(value = "单元删除", response = Result.class)
    @RequestMapping(value = "/v1/delete", method = {RequestMethod.GET})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@ApiParam(value = "id数组", required = true) @RequestParam(value = "ids") List<Integer> ids,
                         HttpServletRequest httpServletRequest) {
        return ApiResultUtils.successResult(unitService.deleteBatchIds(ids));
    }




    @ApiOperation(value = "单元列表", response = Page.class)
    @RequestMapping(value = "/v1/list", method = {RequestMethod.GET})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result list(
            @ApiParam(value = "名称或者编码", required = false) @RequestParam(value = "code", required = false) String code,
            @ApiParam(value = "页码", required = false) @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            HttpServletRequest httpServletRequest) {
        Page<Unit> buildingPage = new Page<>();
        EntityWrapper<Unit> buildingEntityWrapper = new EntityWrapper<>();
        if (StringUtils.isNotEmpty(code)) {
            buildingEntityWrapper.like(true, Unit.NAME, code);
        }
        buildingPage.setCurrent(pageNo);
        Page<Unit> page = unitService.selectPage(buildingPage, buildingEntityWrapper);
        int num = unitService.selectCount(buildingEntityWrapper);
        page.setTotal(num);
        return ApiResultUtils.successResult(page);
    }

}
