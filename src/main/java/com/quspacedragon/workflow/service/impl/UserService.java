package com.quspacedragon.workflow.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.quspacedragon.workflow.entity.User;
import com.quspacedragon.workflow.mapper.UserMapper;
import com.quspacedragon.workflow.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public User findByPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        return this.selectOne(new EntityWrapper<>(user));
    }
}
