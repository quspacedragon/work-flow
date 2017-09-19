package com.quspacedragon.workflow.service.impl;

import com.quspacedragon.workflow.entity.User;
import com.quspacedragon.workflow.mapper.UserMapper;
import com.quspacedragon.workflow.service.BaseTest;
import com.quspacedragon.workflow.service.IUserService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by quspacedragon on 2017/9/14.
 */
public class UserServiceTest extends BaseTest {
    @Resource
    IUserService userService;
    @Resource
    UserMapper userMapper;

    @Test
    public void setUserServiceTest() {
        userService.deleteById(907507511806955521L);

    }

    @Test
    public void select() {
        User user = userMapper.selectById(907507511806955521L);
        System.out.println(user);

    }
}