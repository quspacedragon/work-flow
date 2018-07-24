package com.quspacedragon.workflow.service.impl;

import com.quspacedragon.workflow.entity.Job;
import com.quspacedragon.workflow.mapper.JobMapper;
import com.quspacedragon.workflow.service.IJobService;
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
public class JobService extends ServiceImpl<JobMapper, Job> implements IJobService {
	
}
