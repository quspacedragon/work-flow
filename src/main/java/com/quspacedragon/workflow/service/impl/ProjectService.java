package com.quspacedragon.workflow.service.impl;

import com.quspacedragon.workflow.entity.Project;
import com.quspacedragon.workflow.mapper.ProjectMapper;
import com.quspacedragon.workflow.service.IProjectService;
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
public class ProjectService extends ServiceImpl<ProjectMapper, Project> implements IProjectService {
	
}
