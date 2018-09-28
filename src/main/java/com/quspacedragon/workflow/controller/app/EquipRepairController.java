package com.quspacedragon.workflow.controller.app;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.quspacedragon.workflow.annoation.LoginIntercept;
import com.quspacedragon.workflow.common.LoginHelper;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.EquipRepair;
import com.quspacedragon.workflow.entity.WorkOrder;
import com.quspacedragon.workflow.service.IEquipRepairService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import io.swagger.annotations.Api;
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
 * @since 2018-09-05
 */
@Api("app-设备报修")
@Controller("appEquipRepairController")
@RequestMapping("/app/equipRepair")
public class EquipRepairController {
    @Resource
    IEquipRepairService equipRepairService;
    @Resource
    LoginHelper loginHelper;


    @ApiOperation(value = "报修登记", response = WorkOrder.class)
    @RequestMapping(value = "/v1/save", method = {RequestMethod.POST})
    @ApiImplicitParam(name = "equipRepair", value = "登记", required = true, dataType = "EquipRepair")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result save(@RequestBody(required = true) EquipRepair equipRepair,
                       HttpServletRequest httpServletRequest) {
        boolean insert = equipRepairService.insert(equipRepair);
        if (!insert) {
            return ApiResultUtils.failResult("保存失败");
        }
        return ApiResultUtils.successResult(equipRepairService.selectById(equipRepair.getId()));
    }


}
