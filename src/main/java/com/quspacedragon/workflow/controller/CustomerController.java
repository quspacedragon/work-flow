package com.quspacedragon.workflow.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.common.UserHelper;
import com.quspacedragon.workflow.entity.Customer;
import com.quspacedragon.workflow.service.ICustomerService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.util.ConverUtils;
import com.quspacedragon.workflow.vo.CustomerVo;
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
 * @since 2017-09-29
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private ICustomerService customerService;


    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "商户添加", httpMethod = "POST", response = CustomerVo.class, notes = "添加商户")
    public Result<CustomerVo> insert(@ApiParam(name = "customerName", value = "商户名称") String customerName,
                                     @ApiParam(name = "contat", value = "联系人") String contat,
                                     @ApiParam(name = "phone", value = "联系电话") String phone,
                                     @ApiParam(name = "code", value = "拼音简码") String code) {
        try {
            EnterpriseVo user = UserHelper.getUser();
            Customer customer = new Customer();
            customer.setCustomerName(customerName);
            customer.setPhone(phone);
            customer.setCantat(contat);
            customer.setCode(code);
            customer.setEnterpriseId(user.getId());
            Long customerId = customerService.createCustomer(customer);
            return ApiResultUtils.successResult(ConverUtils.conver(customerService.selectById(customerId), CustomerVo.class));
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return ApiResultUtils.failResult(HttpStatus.INTERNAL_SERVER_ERROR.ordinal(), "添加失败");
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "商户删除", httpMethod = "DELETE", response = Boolean.class, notes = "删除")
    public Result<Boolean> delete(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Long id) {
        customerService.deleteById(id);
        return ApiResultUtils.successResult(true);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "商户修改", httpMethod = "PUT", response = CustomerVo.class, notes = "修改")
    public Result<CustomerVo> update(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Long id,
                                     @ApiParam(name = "customerName", value = "商户名称") String customerName,
                                     @ApiParam(name = "contat", value = "联系人") String contat,
                                     @ApiParam(name = "phone", value = "联系电话") String phone,
                                     @ApiParam(name = "code", value = "拼音简码") String code) {

        Customer customer = customerService.selectById(id);
        customer.setCustomerName(customerName);
        customer.setPhone(phone);
        customer.setCantat(contat);
        customer.setCode(code);
        customerService.updateById(customer);
        customer = customerService.selectById(id);
        return ApiResultUtils.successResult(ConverUtils.conver(customer, CustomerVo.class));
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "商户查询", httpMethod = "GET", response = CustomerVo.class, notes = "查询")
    public Result<CustomerVo> get(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Integer id) {
        Customer dict = customerService.selectById(id);
        return ApiResultUtils.successResult(ConverUtils.conver(dict, CustomerVo.class));
    }

    @GetMapping("/")
    @ResponseBody
    @ApiOperation(value = "商户分页查询", httpMethod = "GET", response = CustomerVo.class, notes = "查询")
    public Result<Page<CustomerVo>> list(@ApiParam(name = "current", value = "页码", required = false, defaultValue = "0") @RequestParam(defaultValue = "0") Integer current,
                                         @ApiParam(name = "pageSize", value = "每页条数", required = false, defaultValue = "10") @RequestParam(defaultValue = "10") Integer pageSize) {
        Page staffPage = new Page<>(current, pageSize);
        staffPage = customerService.selectPage(staffPage);
        staffPage.setTotal(customerService.selectCount(new EntityWrapper<>(new Customer())));
        ImmutableList immutableList = FluentIterable.from(staffPage.getRecords()).transform(r -> ConverUtils.conver(r, CustomerVo.class)).toList();
        staffPage.setRecords(immutableList);
        return ApiResultUtils.successResult(staffPage);
    }
}
