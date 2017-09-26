package com.quspacedragon.workflow.service.impl;

import com.quspacedragon.workflow.entity.Staff;
import com.quspacedragon.workflow.mapper.StaffMapper;
import com.quspacedragon.workflow.service.IStaffService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-24
 */
@Service
public class StaffService extends ServiceImpl<StaffMapper, Staff> implements IStaffService {
	
}
