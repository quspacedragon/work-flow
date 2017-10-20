package com.quspacedragon.workflow.controller.app;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.common.UserHelper;
import com.quspacedragon.workflow.service.IGoodsService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.util.ConverUtils;
import com.quspacedragon.workflow.vo.BillVo;
import com.quspacedragon.workflow.vo.CustomerVo;
import com.quspacedragon.workflow.vo.GoodsVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-27
 */
@Controller("appGoodsController")
@RequestMapping("/app/goods")
public class GoodsController {

    @Resource
    private IGoodsService goodsService;


    @GetMapping("/")
    @ResponseBody
    @ApiOperation(value = "货物分页查询", httpMethod = "GET", response = BillVo.class, notes = "查询")
    public Result<PageInfo<GoodsVo>> list(@ApiParam(name = "current", value = "页码", required = false, defaultValue = "0") @RequestParam(defaultValue = "0") Integer current,
                                          @ApiParam(name = "pageSize", value = "每页条数", required = false, defaultValue = "10") @RequestParam(defaultValue = "10") Integer pageSize,
                                          @ApiParam(name = "startTime", value = "开始时间，unix时间戳", required = false) @RequestParam(required = false) Long startTime,
                                          @ApiParam(name = "endTime", value = "结束时间，unix时间戳", required = false) @RequestParam(required = false) Long endTime) {
        CustomerVo appUser = UserHelper.getAppUser();
        PageHelper.startPage(current, pageSize);
        PageInfo pageInfo = new PageInfo(goodsService.findByCustomerId(appUser.getId(), startTime, endTime));
        ImmutableList immutableList = FluentIterable.from(pageInfo.getList()).transform(r ->
                ConverUtils.conver(r, GoodsVo.class)

        ).toList();
        pageInfo.setList(immutableList);
        return ApiResultUtils.successResult(pageInfo);
    }
}
