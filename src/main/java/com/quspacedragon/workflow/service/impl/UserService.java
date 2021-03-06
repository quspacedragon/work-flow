package com.quspacedragon.workflow.service.impl;

import com.quspacedragon.workflow.entity.User;
import com.quspacedragon.workflow.mapper.UserMapper;
import com.quspacedragon.workflow.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-20
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {
	
}
