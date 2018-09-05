package com.quspacedragon.workflow.controller.back;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.quspacedragon.workflow.annoation.LoginIntercept;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.Building;
import com.quspacedragon.workflow.entity.CheckProject;
import com.quspacedragon.workflow.entity.ProductType;
import com.quspacedragon.workflow.service.ICheckProjectService;
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
@Api("设备巡检")
@RequestMapping("/checkProject")
public class CheckProjectController {
    @Resource
    ICheckProjectService checkProjectService;

    @ApiOperation(value = "巡检项目保存", response = Building.class)
    @RequestMapping(value = "/v1/save", method = {RequestMethod.POST})
    @ApiImplicitParam(name = "checkProject", value = "创建", required = true, dataType = "CheckProject")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result save(@RequestBody(required = true) CheckProject checkProject,
                       HttpServletRequest httpServletRequest) {
        boolean insert = checkProjectService.insert(checkProject);
        if (!insert) {
            return ApiResultUtils.failResult("保存失败");
        }
        return ApiResultUtils.successResult(checkProjectService.selectById(checkProject.getId()));
    }

    @ApiOperation(value = "巡检项目删除", response = Result.class)
    @RequestMapping(value = "/v1/delete", method = {RequestMethod.GET})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@ApiParam(value = "id", required = true) @RequestParam(value = "id") Integer id,
                         HttpServletRequest httpServletRequest) {
        return ApiResultUtils.successResult(checkProjectService.deleteById(id));
    }

    @ApiOperation(value = "巡检项目列表", response = Page.class)
    @RequestMapping(value = "/v1/list", method = {RequestMethod.GET})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result list(
            @ApiParam(value = "名称或者编码", required = false) @RequestParam(value = "code", required = false) String code,
            @ApiParam(value = "页码", required = false) @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            HttpServletRequest httpServletRequest) {
        Page<CheckProject> buildingPage = new Page<>();
        EntityWrapper<CheckProject> buildingEntityWrapper = new EntityWrapper<>();
        if (StringUtils.isNotEmpty(code)) {
            buildingEntityWrapper.like(true, ProductType.NAME, code);
            buildingEntityWrapper.or();
            buildingEntityWrapper.like(true, ProductType.CODE, code);
        }
        buildingPage.setCurrent(pageNo);
        Page<CheckProject> page = checkProjectService.selectPage(buildingPage, buildingEntityWrapper);
        int num = checkProjectService.selectCount(buildingEntityWrapper);
        page.setTotal(num);
        return ApiResultUtils.successResult(page);
    }


}
