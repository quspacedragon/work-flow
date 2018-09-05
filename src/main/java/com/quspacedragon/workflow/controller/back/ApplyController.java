package com.quspacedragon.workflow.controller.back;


import com.quspacedragon.workflow.annoation.LoginIntercept;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.Apply;
import com.quspacedragon.workflow.entity.Room;
import com.quspacedragon.workflow.service.IApplyService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
@Api("申请相关")
@Controller
@RequestMapping("/back/apply")
public class ApplyController {
    @Autowired
    IApplyService applyService;

    @ApiOperation(value = "保存", response = Room.class)
    @RequestMapping(value = "/v1/save", method = {RequestMethod.GET, RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result save(@ApiParam(value = "通知", required = true) @RequestBody(required = true) Apply apply,
                       HttpServletRequest httpServletRequest) {
        boolean insert = applyService.insert(apply);
        if (!insert) {
            return ApiResultUtils.failResult("保存失败");
        }
        return ApiResultUtils.successResult(applyService.selectById(apply.getId()));
    }


    @ApiOperation(value = "删除", response = Result.class)
    @RequestMapping(value = "/v1/delete", method = {RequestMethod.GET, RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@ApiParam(value = "id数组", required = true) @RequestParam(value = "ids") List<Integer> ids,
                         HttpServletRequest httpServletRequest) {

        return ApiResultUtils.successResult(applyService.deleteBatchIds(ids));
    }

}
