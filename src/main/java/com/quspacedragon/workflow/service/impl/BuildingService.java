package com.quspacedragon.workflow.service.impl;

import com.quspacedragon.workflow.entity.Building;
import com.quspacedragon.workflow.mapper.BuildingMapper;
import com.quspacedragon.workflow.service.IBuildingService;
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
public class BuildingService extends ServiceImpl<BuildingMapper, Building> implements IBuildingService {
	
}
