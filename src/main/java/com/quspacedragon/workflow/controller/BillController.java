package com.quspacedragon.workflow.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.common.UserHelper;
import com.quspacedragon.workflow.entity.Bill;
import com.quspacedragon.workflow.service.IBillService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.util.ConverUtils;
import com.quspacedragon.workflow.vo.BillVo;
import com.quspacedragon.workflow.vo.DictVo;
import com.quspacedragon.workflow.vo.EnterpriseVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
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
            @ApiParam(name = "vatNo", value = "缸号") String vatNo,
            @ApiParam(name = "varieties", value = "品种id") Long varieties,
            @ApiParam(name = "color", value = "颜色id") Long color,
            @ApiParam(name = "unpackName", value = "拆包人") String unpackName,
            @ApiParam(name = "amout", value = "数量") Integer amout,
            @ApiParam(name = "customerId", value = "商户id") Long customerId,
            @ApiParam(name = "processOrder", value = "处理序列,','隔开") String processOrder
    ) {
        try {
            Bill bill = new Bill();
            bill.setWeight(weight);
            bill.setVatNo(vatNo);
            bill.setVarieties(varieties);
            bill.setColor(color);
            bill.setUnpackName(unpackName);
            bill.setAmout(amout);
            bill.setProcessOrder(processOrder);
            bill.setCustomerId(customerId);
            Long billId = billService.createBill(bill);
            return ApiResultUtils.successResult(ConverUtils.conver(billService.selectById(billId), BillVo.class));
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return ApiResultUtils.failResult(HttpStatus.INTERNAL_SERVER_ERROR.ordinal(), "添加失败");
    }


    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "订单状态变更", httpMethod = "PUT", response = BillVo.class, notes = "订单状态变更")
    public Result<BillVo> update(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Integer id,
                                 @ApiParam(name = "status", value = "变更状态") Integer status) {

        Bill bill = billService.selectById(id);
        String processOrder = bill.getProcessOrder();
        Integer formStatus = bill.getStatus();
        int position = processOrder.indexOf(formStatus.toString());
        int toPosion = processOrder.indexOf(status.toString());
        if (toPosion < 0) {
            return ApiResultUtils.failResult(HttpStatus.INTERNAL_SERVER_ERROR.ordinal(), "变更状态异常");
        }
        if (position > toPosion) {
            return ApiResultUtils.failResult(HttpStatus.INTERNAL_SERVER_ERROR.ordinal(), "不能变更到前面状态");
        }
        bill.setStatus(status);
        billService.updateById(bill);
        return ApiResultUtils.successResult(ConverUtils.conver(billService.selectById(bill.getId()), BillVo.class));
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "订单查询", httpMethod = "GET", response = BillVo.class, notes = "查询")
    public Result<DictVo> get(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Integer id) {
        return ApiResultUtils.successResult(ConverUtils.conver(billService.selectById(id), BillVo.class));
    }


    @GetMapping("/")
    @ResponseBody
    @ApiOperation(value = "订单分页查询", httpMethod = "GET", response = BillVo.class, notes = "查询")
    public Result<Page<BillVo>> list(@ApiParam(name = "current", value = "页码", required = false, defaultValue = "0") @RequestParam(defaultValue = "0") Integer current,
                                     @ApiParam(name = "pageSize", value = "每页条数", required = false, defaultValue = "10") @RequestParam(defaultValue = "10") Integer pageSize) {
        EnterpriseVo user = UserHelper.getUser();
        Bill bill = new Bill();
        bill.setEnterpriseId(user.getId());
        EntityWrapper<Bill> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(bill);
        Page staffPage = new Page<>();
        staffPage.setCurrent(current);
        staffPage.setSize(pageSize);
        staffPage = billService.selectPage(staffPage, entityWrapper);
        ImmutableList immutableList = FluentIterable.from(staffPage.getRecords()).transform(r -> ConverUtils.conver(r, BillVo.class)).toList();
        staffPage.setRecords(immutableList);
        return ApiResultUtils.successResult(staffPage);
    }


}
