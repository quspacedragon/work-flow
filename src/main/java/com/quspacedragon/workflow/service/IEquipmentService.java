package com.quspacedragon.workflow.service;

import com.quspacedragon.workflow.entity.Equipment;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author quspacedragon
 * @since 2018-08-21
 */
public interface IEquipmentService extends IService<Equipment> {

    public Integer findMaxCode(Integer level, Integer equipmentId);


}
