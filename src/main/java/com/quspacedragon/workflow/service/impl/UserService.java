package com.quspacedragon.workflow.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.quspacedragon.workflow.entity.User;
import com.quspacedragon.workflow.exception.BizException;
import com.quspacedragon.workflow.mapper.UserMapper;
import com.quspacedragon.workflow.request.UserMoveinRequest;
import com.quspacedragon.workflow.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public Boolean moveIn(UserMoveinRequest userMoveinRequest) {
        User user = userMoveinRequest.getUser();
        boolean insert = this.insert(user);
        if (!insert) {
            throw new BizException("迁入失败");
        }
        List<User> familyList = userMoveinRequest.getFamilyList();
        familyList.stream().forEach(r -> r.setParentId(user.getId()));
        boolean insertBatch = this.insertBatch(familyList);
        if (!insertBatch) {
            throw new BizException("迁入失败");
        }
        return null;
    }
}
