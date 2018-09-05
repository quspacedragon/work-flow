package com.quspacedragon.workflow.controller.app;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.quspacedragon.workflow.annoation.LoginIntercept;
import com.quspacedragon.workflow.common.LoginHelper;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.BaseEntity;
import com.quspacedragon.workflow.entity.WorkOrder;
import com.quspacedragon.workflow.enums.WorkOrderStatusEnum;
import com.quspacedragon.workflow.service.IWorkOrderService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 * @since 2018-09-02
 */
@Controller("appWorkOrderController")
@RequestMapping("/app/workOrder")
public class WorkOrderController {


    @Resource
    IWorkOrderService workOrderService;
    @Resource
    LoginHelper loginHelper;

    @ApiOperation(value = "工单保存", response = WorkOrder.class)
    @RequestMapping(value = "/v1/save", method = {RequestMethod.POST})
    @ApiImplicitParam(name = "workOrder", value = "创建", required = true, dataType = "WorkOrder")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result save(@RequestBody(required = true) WorkOrder workOrder,
                       HttpServletRequest httpServletRequest,
                       @RequestParam(value = "userId", required = true) Long userId,
                       @RequestParam(value = "token", required = true) String token) {
        workOrder.setCreateUserId(loginHelper.getLoginUserId(token,httpServletRequest));
//        workOrder.setProcessOpinion();
        boolean insert = workOrderService.insert(workOrder);
        if (!insert) {
            return ApiResultUtils.failResult("保存失败");
        }
        return ApiResultUtils.successResult(workOrderService.selectById(workOrder.getId()));
    }

    @ApiOperation(value = "工单列表", response = Page.class)
    @RequestMapping(value = "/v1/list", method = {RequestMethod.GET})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result list(
            @ApiParam(value = "工单状态 TODO(0, \"待分派\"),\n" +
                    "    DISPATCHED(1, \"已分派\"),\n" +
                    "    PROCESSING(2, \"处理中\"),\n" +
                    "    PROCESSED(3,\"已处理\"),", required = false) @RequestParam(value = "status", required = false) Integer status,
            @ApiParam(value = "页码", required = false) @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            @ApiParam(value = "类型 1设备工单 2报事", required = true) @RequestParam(value = "type", required = true) Integer type,
            @RequestParam(value = "token", required = true) String token,
            @RequestParam(value = "userId", required = true) Long userId,
            HttpServletRequest httpServletRequest) {

        Page<WorkOrder> workOrderPage = new Page<>();
        WorkOrder workOrder = new WorkOrder();
        Integer loginUserId = loginHelper.getLoginUserId(token, httpServletRequest);
        workOrder.setProcessUserId(loginUserId);
        EntityWrapper<WorkOrder> workOrderEntityWrapper = new EntityWrapper<>();
        workOrderEntityWrapper.setEntity(workOrder);
        if (status != 0) {
            workOrderEntityWrapper.eq(true, BaseEntity.STATUS, status);
        }
        workOrderPage.setCurrent(pageNo);
        Page<WorkOrder> page = workOrderService.selectPage(workOrderPage, workOrderEntityWrapper);
        int num = workOrderService.selectCount(workOrderEntityWrapper);
        page.setTotal(num);
        return ApiResultUtils.successResult(page);
    }


}
