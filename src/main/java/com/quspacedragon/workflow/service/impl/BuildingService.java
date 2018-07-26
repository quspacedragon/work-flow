package com.quspacedragon.workflow.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.quspacedragon.workflow.entity.Building;
import com.quspacedragon.workflow.entity.ProductType;
import com.quspacedragon.workflow.mapper.BuildingMapper;
import com.quspacedragon.workflow.service.IBuildingService;
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
    @Override
    public Building findByCode(String code) {
        Building building = new Building();
        building.setCode(code);
        return this.selectOne(new EntityWrapper<>(building));
    }
	
}
