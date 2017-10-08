package com.quspacedragon.workflow.controller.app;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.common.UserHelper;
import com.quspacedragon.workflow.entity.Bill;
import com.quspacedragon.workflow.entity.Enterprise;
import com.quspacedragon.workflow.entity.ScanRecord;
import com.quspacedragon.workflow.service.IBillService;
import com.quspacedragon.workflow.service.IEnterpriseService;
import com.quspacedragon.workflow.service.IScanRecordService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.util.ConverUtils;
import com.quspacedragon.workflow.vo.BillVo;
import com.quspacedragon.workflow.vo.CustomerVo;
import com.quspacedragon.workflow.vo.EnterpriseVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-27
 */

@Controller("appBillController")
@RequestMapping("/app/bill")
public class BillController {

    @Resource
    private IBillService billService;
    @Resource
    private IEnterpriseService enterpriseService;
    @Resource
    private IScanRecordService scanRecordService;

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "订单查询", httpMethod = "GET", response = BillVo.class, notes = "查询")
    public Result<BillVo> get(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Long id) {
        List<ScanRecord> list = scanRecordService.findByBillId(id);
        BillVo billVo = (BillVo) ConverUtils.conver(billService.selectById(id), BillVo.class);
        billVo.setScanRecords(list);
        return ApiResultUtils.successResult(billVo);
    }


    @GetMapping("/")
    @ResponseBody
    @ApiOperation(value = "订单分页查询", httpMethod = "GET", response = BillVo.class, notes = "查询")
    public Result<Page<BillVo>> list(@ApiParam(name = "current", value = "页码", required = false, defaultValue = "0") @RequestParam(defaultValue = "0") Integer current,
                                     @ApiParam(name = "pageSize", value = "每页条数", required = false, defaultValue = "10") @RequestParam(defaultValue = "10") Integer pageSize,
                                     @ApiParam(name = "enterpriseId", value = "企业id", required = false) @RequestParam Long enterpriseId,
                                     @ApiParam(name = "startTime", value = "开始时间，unix时间戳", required = false) @RequestParam Long startTime,
                                     @ApiParam(name = "endTime", value = "结束时间，unix时间戳", required = false) @RequestParam Long endTime) {
        CustomerVo appUser = UserHelper.getAppUser();
        Bill bill = new Bill();
        bill.setCustomerId(appUser.getId());
        bill.setEnterpriseId(enterpriseId);
        EntityWrapper<Bill> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(bill);
        if (startTime != null) {
            entityWrapper.gt(true, "create_time", startTime);

        }
        if (endTime != null) {
            entityWrapper.gt(true, "create_time", endTime);

        }
        Page staffPage = new Page<>();
        staffPage.setCurrent(current);
        staffPage.setSize(pageSize);
        staffPage = billService.selectPage(staffPage, entityWrapper);
        ImmutableList immutableList = FluentIterable.from(staffPage.getRecords()).transform(r -> {
                    Bill billEntity = (Bill) r;
                    Enterprise enterprise = enterpriseService.selectById(billEntity.getEnterpriseId());
                    EnterpriseVo enterpriseVo = (EnterpriseVo) ConverUtils.conver(enterprise, EnterpriseVo.class);
                    BillVo billVo = (BillVo) ConverUtils.conver(r, BillVo.class);
                    billVo.setEnterpriseVo(enterpriseVo);
                    return billVo;
                }

        ).toList();
        staffPage.setRecords(immutableList);
        return ApiResultUtils.successResult(staffPage);
    }


}
