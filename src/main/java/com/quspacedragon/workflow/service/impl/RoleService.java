package com.quspacedragon.workflow.service.impl;

import com.quspacedragon.workflow.entity.Role;
import com.quspacedragon.workflow.mapper.RoleMapper;
import com.quspacedragon.workflow.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> implements IRoleService {
	
}
