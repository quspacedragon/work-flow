package com.quspacedragon.workflow.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quspacedragon.workflow.mapper.UserMapper;
import com.quspacedragon.workflow.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Title: UserService
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/7
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User getUser(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public PageInfo<User> getPage(User userQuery) {
        PageHelper.startPage(userQuery.getPage(), userQuery.getRows());
        return new PageInfo<>(userMapper.selectByExample(userQuery));
    }
}
