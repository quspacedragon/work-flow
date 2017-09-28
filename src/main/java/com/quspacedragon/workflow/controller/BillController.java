package com.quspacedragon.workflow.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.Bill;
import com.quspacedragon.workflow.service.IBillService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.vo.BillVo;
import com.quspacedragon.workflow.vo.DictVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-27
 */
@Controller
@RequestMapping("/bill")
public class BillController {

    @Resource
    private IBillService billService;


    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "订单添加", httpMethod = "POST", response = BillVo.class, notes = "添加订单")
    public Result<BillVo> insert(
            @ApiParam(name = "weight", value = "重量") Integer weight,
            @ApiParam(name = "vatNo", value = "缸号") Integer vatNo,
            @ApiParam(name = "varieties", value = "品种id") Integer varieties,
            @ApiParam(name = "color", value = "颜色id") Integer color,
            @ApiParam(name = "unpackName", value = "拆包人") Integer unpackName,
            @ApiParam(name = "amout", value = "数量") Integer amout,
            @ApiParam(name = "processOrder", value = "处理序列,','隔开") Integer processOrder
    ) {

        BillVo dict = new BillVo();

        return ApiResultUtils.successResult(dict);
    }


    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "订单状态变更", httpMethod = "PUT", response = BillVo.class, notes = "订单状态变更")
    public Result<BillVo> update(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Integer id,
                                 @ApiParam(name = "status", value = "变更状态") Integer status) {

        BillVo dict = new BillVo();

        return ApiResultUtils.successResult(dict);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "订单查询", httpMethod = "GET", response = BillVo.class, notes = "查询")
    public Result<DictVo> get(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Integer id) {
        BillVo dict = new BillVo();

        return ApiResultUtils.successResult(dict);
    }


    @GetMapping("/")
    @ResponseBody
    @ApiOperation(value = "订单分页查询", httpMethod = "GET", response = BillVo.class, notes = "查询")
    public Result<Page<BillVo>> list(@ApiParam(name = "current", value = "页码", required = false) Integer current,
                                     @ApiParam(name = "pageSize", value = "每页条数", required = false) Integer pageSize) {
        Page<Bill> staffPage = billService.selectPage(null);
        return ApiResultUtils.successResult(staffPage);
    }


}
