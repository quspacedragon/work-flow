package com.quspacedragon.workflow.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.User;
import com.quspacedragon.workflow.service.IUserService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-12
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @GetMapping("/add")
    @ResponseBody
    public Result insert() {
        User user = new User();
        user.setName("123123");
        boolean flag = user.insert();
        if (flag) {
            return ApiResultUtils.successResult(user.selectById());
        } else {
            return ApiResultUtils.failResult("插入失败");
        }
    }

    @GetMapping("/update")
    @ResponseBody
    public Result update(Long id) {
        User user = new User();
        user.setId(id);
        user = user.selectById();
        user.setName("11111");
        boolean flag = user.updateById();
        if (flag) {
            return ApiResultUtils.successResult(user.selectById());
        } else {
            return ApiResultUtils.failResult("更新失败");
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public Result list() {
        User user = new User();
        PageHelper.startPage(1, 100);
        return ApiResultUtils.successResult(new PageInfo<User>(user.selectAll()));
    }
}
