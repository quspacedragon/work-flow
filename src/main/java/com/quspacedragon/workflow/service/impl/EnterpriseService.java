package com.quspacedragon.workflow.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.quspacedragon.workflow.entity.Enterprise;
import com.quspacedragon.workflow.mapper.EnterpriseMapper;
import com.quspacedragon.workflow.service.IEnterpriseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-27
 */
@Service
public class EnterpriseService extends ServiceImpl<EnterpriseMapper, Enterprise> implements IEnterpriseService {
    @Override
    public Enterprise findByUsernameAndPwd(String loginName, String pwd) {
        Enterprise enterprise = new Enterprise();
        enterprise.setLoginName(loginName);
        enterprise.setLoginPwd(pwd);
        return selectOne(new EntityWrapper<>(enterprise));
    }
}
