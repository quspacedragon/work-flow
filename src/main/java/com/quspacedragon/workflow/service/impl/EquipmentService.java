package com.quspacedragon.workflow.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.quspacedragon.workflow.entity.Equipment;
import com.quspacedragon.workflow.mapper.EquipmentMapper;
import com.quspacedragon.workflow.service.IEquipmentService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author quspacedragon
 * @since 2018-08-21
 */
@Service
public class EquipmentService extends ServiceImpl<EquipmentMapper, Equipment> implements IEquipmentService {
    @Override
    public Integer findMaxCode(Integer level, Integer equipmentId) {
        Equipment equipment = new Equipment();
        equipment.setLevel(level);
        equipment.setParentId(equipmentId);
        EntityWrapper<Equipment> equipmentEntityWrapper = new EntityWrapper<Equipment>();
        equipmentEntityWrapper.setEntity(equipment);
        equipmentEntityWrapper.orderBy(Equipment.LEVEL_NO, false);
        Page<Equipment> page = new Page();
        page.setSize(1);
        page = selectPage(page, equipmentEntityWrapper);
        List<Equipment> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return 1;
        }
        return records.get(0).getLevelNo();
    }
}
