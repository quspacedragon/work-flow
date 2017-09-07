package com.quspacedragon.workflow.controller;

import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.model.User;
import com.quspacedragon.workflow.service.UserService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public Result getAll(User userInfo) {
        return ApiResultUtils.successResult(userService.getPage(userInfo));
    }


    @RequestMapping(value = "/view/{id}")
    public Result view(@PathVariable Integer id) {
        User userInfo = userService.getUser(id);
        return ApiResultUtils.successResult(userInfo);
    }

//    @RequestMapping(value = "/delete/{id}")
//    public ModelMap delete(@PathVariable Integer id) {
//        ModelMap result = new ModelMap();
//        userInfoService.deleteById(id);
//        result.put("msg", "删除成功!");
//        return result;
//    }
//
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public ModelMap save(User userInfo) {
//        ModelMap result = new ModelMap();
//        String msg = userInfo.getId() == null ? "新增成功!" : "更新成功!";
//        userInfoService.save(userInfo);
//        result.put("userInfo", userInfo);
//        result.put("msg", msg);
//        return result;
//    }
}
