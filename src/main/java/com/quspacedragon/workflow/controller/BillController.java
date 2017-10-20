package com.quspacedragon.workflow.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.common.UserHelper;
import com.quspacedragon.workflow.entity.Bill;
import com.quspacedragon.workflow.entity.Customer;
import com.quspacedragon.workflow.entity.Goods;
import com.quspacedragon.workflow.service.IBillService;
import com.quspacedragon.workflow.service.ICustomerService;
import com.quspacedragon.workflow.service.IGoodsService;
import com.quspacedragon.workflow.umen.UmengService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.util.ConverUtils;
import com.quspacedragon.workflow.vo.BillVo;
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
    @Resource
    private UmengService umengService;
    @Resource
    private ICustomerService customerService;
    @Resource
    private IGoodsService goodsService;


    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "订单添加", httpMethod = "POST", response = BillVo.class, notes = "添加订单")
    public Result<BillVo> insert(
            @ApiParam(name = "weight", value = "重量") Integer weight,
            @ApiParam(name = "vatNo", value = "缸号") String vatNo,
            @ApiParam(name = "varieties", value = "品种id") String varieties,
            @ApiParam(name = "goodsId", value = "货物id") Long goodsId,
            @ApiParam(name = "color", value = "颜色id") String color,
            @ApiParam(name = "unpackName", value = "拆包人") String unpackName,
            @ApiParam(name = "amout", value = "数量") Integer amout,
            @ApiParam(name = "customerId", value = "商户id") Long customerId,
            @ApiParam(name = "processOrder", value = "处理序列,','隔开") String processOrder,
            @ApiParam(name = "memo", value = "备注") String memo
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
            bill.setGoodsId(goodsId);
            bill.setMemo(memo);
            Long billId = billService.createBill(bill);
            bill = billService.selectById(billId);
            return ApiResultUtils.successResult(ConverUtils.conver(bill, BillVo.class));
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return ApiResultUtils.failResult(HttpStatus.INTERNAL_SERVER_ERROR.ordinal(), "添加失败");
    }


    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "订单状态变更", httpMethod = "PUT", response = BillVo.class, notes = "订单状态变更")
    public Result<BillVo> update(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Long id,
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
        //是否完成流程
        if (toPosion == processOrder.length()) {
            bill.setType(1);
        }
        bill.setStatus(status);
        //如果订单都完成更新货物的完成状态
        boolean flag = billService.updateById(bill);
        if (flag) {
            int num = billService.countUnComplete(bill.getGoodsId());
            if (num == 0) {
                Goods goods = goodsService.selectById(bill.getGoodsId());
                goods.setStatus(1);
                goodsService.updateById(goods);
            }
        }


        //umeng通知
        Customer customer = customerService.selectById(bill.getCustomerId());
        BillVo billVo = (BillVo) ConverUtils.conver(billService.selectById(bill.getId()), BillVo.class);
        if (customer != null) {
            billVo.setCantat(customer.getCantat());
            billVo.setPhone(customer.getPhone());
            try {
                if (customer.getCanPush()) {
                    umengService.sendAndroidUnicast(customer.getDeviceToken(), "订单变更通知", "您有一笔订单状态发生改变");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return ApiResultUtils.successResult(billVo);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "订单查询", httpMethod = "GET", response = BillVo.class, notes = "查询")
    public Result<BillVo> get(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Long id) {
        return ApiResultUtils.successResult(ConverUtils.conver(billService.selectById(id), BillVo.class));
    }


    @GetMapping("/")
    @ResponseBody
    @ApiOperation(value = "订单分页查询", httpMethod = "GET", response = BillVo.class, notes = "查询")
    public Result<Page<BillVo>> list(@ApiParam(name = "current", value = "页码", required = false, defaultValue = "0") @RequestParam(defaultValue = "0") Integer current,
                                     @ApiParam(name = "pageSize", value = "每页条数", required = false, defaultValue = "10") @RequestParam(defaultValue = "10") Integer pageSize,
                                     @ApiParam(name = "goodsId", value = "货物id", required = false) @RequestParam(required = false) Long goodsId,
                                     @ApiParam(name = "billNo", value = "订单id", required = false) @RequestParam(required = false) String billNo,
                                     @ApiParam(name = "startTime", value = "开始时间，unix时间戳", required = false) @RequestParam(required = false) Long startTime,
                                     @ApiParam(name = "endTime", value = "结束时间，unix时间戳", required = false) @RequestParam(required = false) Long endTime) {
        EnterpriseVo user = UserHelper.getUser();
        Bill bill = new Bill();
        bill.setEnterpriseId(user.getId());
        bill.setGoodsId(goodsId);
        bill.setBillNo(billNo);
        EntityWrapper<Bill> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(bill);
        if (startTime != null) {
            entityWrapper.gt(true, "create_time", startTime);

        }
        if (endTime != null) {
            entityWrapper.lt(true, "create_time", endTime);

        }
        Page staffPage = new Page<>(current, pageSize);
        entityWrapper.orderBy(true, "create_time", false);
        staffPage = billService.selectPage(staffPage, entityWrapper);
        staffPage.setTotal(billService.selectCount(entityWrapper));
        ImmutableList immutableList = FluentIterable.from(staffPage.getRecords()).transform(r -> {
            Bill billEntity = (Bill) r;
            BillVo billVo = (BillVo) ConverUtils.conver(r, BillVo.class);
            Customer customer = customerService.selectById(billEntity.getCustomerId());
            if (customer != null) {
                billVo.setCustomerName(customer.getCustomerName());
                billVo.setCustomerNo(customer.getCustomerNo());
            }
            Goods goods = goodsService.selectById(billEntity.getGoodsId());
            if (goods != null) {
                billVo.setGoodsNo(goods.getGoodsNo());
            }
            return billVo;
        }).toList();
        staffPage.setRecords(immutableList);
        return ApiResultUtils.successResult(staffPage);
    }


}
