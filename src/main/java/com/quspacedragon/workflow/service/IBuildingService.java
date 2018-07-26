package com.quspacedragon.workflow.service;

import com.quspacedragon.workflow.entity.Building;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
public interface IBuildingService extends IService<Building> {
    public Building findByCode(String code);

}
