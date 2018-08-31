package com.quspacedragon.workflow.controller.back;


import com.quspacedragon.workflow.annoation.LoginIntercept;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.Notice;
import com.quspacedragon.workflow.entity.Room;
import com.quspacedragon.workflow.enums.NoticeStatusEnum;
import com.quspacedragon.workflow.service.INoticeService;
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
@Controller
@RequestMapping("/notice")
@Api("通知管理")
public class NoticeController {
    @Autowired
    INoticeService noticeService;

    @ApiOperation(value = "保存", response = Room.class)
    @RequestMapping(value = "/v1/save", method = {RequestMethod.GET, RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result save(@ApiParam(value = "通知", required = true) @RequestBody(required = true) Notice notice,
                       HttpServletRequest httpServletRequest) {
        boolean insert = noticeService.insert(notice);
        if (!insert) {
            return ApiResultUtils.failResult("保存失败");
        }
        return ApiResultUtils.successResult(noticeService.selectById(notice.getId()));
    }


    @ApiOperation(value = "删除", response = Result.class)
    @RequestMapping(value = "/v1/delete", method = {RequestMethod.GET, RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@ApiParam(value = "id数组", required = true) @RequestParam(value = "ids") List<Integer> ids,
                         HttpServletRequest httpServletRequest) {

        return ApiResultUtils.successResult(noticeService.deleteBatchIds(ids));
    }

    @ApiOperation(value = "发布", response = Result.class)
    @RequestMapping(value = "/v1/release", method = {RequestMethod.GET, RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result release(@ApiParam(value = "id数组", required = true) @RequestParam(value = "ids") List<Integer> ids,
                          HttpServletRequest httpServletRequest) {

        if (CollectionUtils.isNotEmpty(ids)) {
            return ApiResultUtils.failResult("请选择数据");
        }
        List<Notice> noticeList = ids.stream().map(r -> {
            Notice notice = new Notice();
            notice.setId(r);
            notice.setStatus(NoticeStatusEnum.release.getStatus());
            return notice;
        }).collect(Collectors.toList());
        return ApiResultUtils.successResult(noticeService.updateBatchById(noticeList));
    }


    @ApiOperation(value = "作废", response = Result.class)
    @RequestMapping(value = "/v1/invalid", method = {RequestMethod.GET, RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result recover(@ApiParam(value = "id数组", required = true) @RequestParam(value = "ids") List<Integer> ids,
                          HttpServletRequest httpServletRequest) {

        if (CollectionUtils.isNotEmpty(ids)) {
            return ApiResultUtils.failResult("请选择数据");
        }
        List<Notice> noticeList = ids.stream().map(r -> {
            Notice notice = new Notice();
            notice.setId(r);
            notice.setStatus(NoticeStatusEnum.invalid.getStatus());
            return notice;
        }).collect(Collectors.toList());
        return ApiResultUtils.successResult(noticeService.updateBatchById(noticeList));
    }

}
