package com.quspacedragon.workflow.service;

import com.baomidou.mybatisplus.service.IService;
import com.quspacedragon.workflow.entity.User;
import com.quspacedragon.workflow.request.UserMoveinRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
public interface IUserService extends IService<User> {

    public User findByPhone(String phone);

    public Boolean moveIn(UserMoveinRequest userMoveinRequest);

}
