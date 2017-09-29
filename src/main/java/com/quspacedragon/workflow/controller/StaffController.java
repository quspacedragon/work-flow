package com.quspacedragon.workflow.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.common.UserHelper;
import com.quspacedragon.workflow.entity.Dict;
import com.quspacedragon.workflow.entity.Staff;
import com.quspacedragon.workflow.service.IStaffService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.util.ConverUtils;
import com.quspacedragon.workflow.vo.DictVo;
import com.quspacedragon.workflow.vo.EnterpriseVo;
import com.quspacedragon.workflow.vo.StaffVo;
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
@RequestMapping("/staff")
public class StaffController {
    @Resource
    private IStaffService staffService;


    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "员工添加", httpMethod = "POST", response = StaffVo.class, notes = "添加员工")
    public Result<StaffVo> insert(@ApiParam(name = "customerNo", value = "员工编号") String customerNo,
                                  @ApiParam(name = "customerName", value = "员工姓名") String customerName,
                                  @ApiParam(name = "shortName", value = "拼音简码") String shortName,
                                  @ApiParam(name = "loginPwd", value = "登录密码") String loginPwd,
                                  @ApiParam(name = "department", value = "所属部门") String department,
                                  @ApiParam(name = "phone", value = "手机") String phone

    ) {
        EnterpriseVo user = UserHelper.getUser();
        Staff staff = new Staff();
        staff.setCustomerNo(customerNo);
        staff.setShortName(shortName);
        staff.setLoginPwd(loginPwd);
        staff.setPhone(phone);
        staff.setEnterpriseId(user.getId());
        staff.setCustomerName(customerName);
        staff.setDepartment(department);
        staffService.insert(staff);
        staff = staffService.selectById(staff.getId());
        return ApiResultUtils.successResult(ConverUtils.conver(staff, StaffVo.class));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "员工删除", httpMethod = "DELETE", response = Boolean.class, notes = "删除")
    public Result<Boolean> delete(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Integer id
    ) {
        Staff staff = staffService.selectById(id);
        if (staff == null) {
            return ApiResultUtils.failResult(HttpStatus.NO_CONTENT.ordinal(), "员工不存在");
        }
        boolean b = staffService.deleteById(id);
        return ApiResultUtils.successResult(b);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "员工修改", httpMethod = "PUT", response = StaffVo.class, notes = "修改")
    public Result<DictVo> update(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Integer id,
                                 @ApiParam(name = "customerNo", value = "员工编号") String customerNo,
                                 @ApiParam(name = "customerName", value = "员工姓名") String customerName,
                                 @ApiParam(name = "shortName", value = "拼音简码") String shortName,
                                 @ApiParam(name = "loginPwd", value = "登录密码") String loginPwd,
                                 @ApiParam(name = "department", value = "所属部门") String department,
                                 @ApiParam(name = "phone", value = "手机") String phone) {
        Dict dict = new Dict();
        Staff staff = staffService.selectById(id);
        if (staff == null) {
            return ApiResultUtils.failResult(HttpStatus.NO_CONTENT.ordinal(), "员工不存在");
        }
        staff.setCustomerNo(customerNo);
        staff.setShortName(shortName);
        staff.setLoginPwd(loginPwd);
        staff.setPhone(phone);
        staff.setCustomerName(customerName);
        staff.setDepartment(department);
        staffService.updateById(staff);
        return ApiResultUtils.successResult(ConverUtils.conver(staff, StaffVo.class));
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "员工查询", httpMethod = "GET", response = StaffVo.class, notes = "查询")
    public Result<DictVo> get(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Integer id) {
        Staff staff = staffService.selectById(id);
        if (staff == null) {
            return ApiResultUtils.failResult(HttpStatus.NO_CONTENT.ordinal(), "员工不存在");
        }
        return ApiResultUtils.successResult(ConverUtils.conver(staff, StaffVo.class));
    }

    @GetMapping("/")
    @ResponseBody
    @ApiOperation(value = "员工分页查询", httpMethod = "GET", response = StaffVo.class, notes = "查询")
    public Result<Page<StaffVo>> list(@ApiParam(name = "current", value = "页码", required = false) Integer current,
                                      @ApiParam(name = "pageSize", value = "每页条数", required = false) Integer pageSize) {
        Page staffPage = new Page<>();
        staffPage.setCurrent(current);
        staffPage.setSize(pageSize);
        staffPage = staffService.selectPage(null);
        ImmutableList immutableList = FluentIterable.from(staffPage.getRecords()).transform(r -> ConverUtils.conver(r, StaffVo.class)).toList();
        staffPage.setRecords(immutableList);
        return ApiResultUtils.successResult(staffPage);
    }

}
