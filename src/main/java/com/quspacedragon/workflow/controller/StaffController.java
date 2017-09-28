package com.quspacedragon.workflow.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.Dict;
import com.quspacedragon.workflow.entity.Staff;
import com.quspacedragon.workflow.service.IStaffService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.vo.DictVo;
import com.quspacedragon.workflow.vo.StaffVo;
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
        StaffVo staff = new StaffVo();

        return ApiResultUtils.successResult(staff);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "员工删除", httpMethod = "DELETE", response = Boolean.class, notes = "删除")
    public Result<Boolean> delete(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Integer id) {

        return ApiResultUtils.successResult(true);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "员工修改", httpMethod = "PUT", response = StaffVo.class, notes = "修改")
    public Result<DictVo> update(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Integer id,
                                 @ApiParam(name = "name", value = "名称") String name) {
        Dict dict = new Dict();

        return ApiResultUtils.successResult(dict);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "员工查询", httpMethod = "GET", response = StaffVo.class, notes = "查询")
    public Result<DictVo> get(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Integer id) {
        Dict dict = new Dict();

        return ApiResultUtils.successResult(dict);
    }

    @GetMapping("/")
    @ResponseBody
    @ApiOperation(value = "员工分页查询", httpMethod = "GET", response = StaffVo.class, notes = "查询")
    public Result<Page<Staff>> list(@ApiParam(name = "current", value = "页码", required = false) Integer current,
                                    @ApiParam(name = "pageSize", value = "每页条数", required = false) Integer pageSize) {
        Page<Staff> staffPage = staffService.selectPage(null);
        return ApiResultUtils.successResult(staffPage);
    }

}
