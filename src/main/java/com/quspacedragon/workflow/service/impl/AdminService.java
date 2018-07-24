package com.quspacedragon.workflow.service.impl;

import com.quspacedragon.workflow.entity.Admin;
import com.quspacedragon.workflow.mapper.AdminMapper;
import com.quspacedragon.workflow.service.IAdminService;
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
public class AdminService extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
	
}
