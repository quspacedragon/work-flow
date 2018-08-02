package com.quspacedragon.workflow.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.quspacedragon.workflow.entity.Room;
import com.quspacedragon.workflow.exception.BizException;
import com.quspacedragon.workflow.mapper.RoomMapper;
import com.quspacedragon.workflow.request.RoomMergeRequest;
import com.quspacedragon.workflow.request.RoomSplitRequest;
import com.quspacedragon.workflow.service.IRoomService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
@Service
public class RoomService extends ServiceImpl<RoomMapper, Room> implements IRoomService {
    @Override
    public Room findByCode(String code) {
        Room building = new Room();
        building.setCode(code);
        return this.selectOne(new EntityWrapper<>(building));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean mergeRoom(RoomMergeRequest roomMergeRequest) {
        List<Integer> idList = roomMergeRequest.getIdList();
        Room room = new Room();
        BeanUtils.copyProperties(roomMergeRequest, room);
        boolean insert = this.insert(room);
        if (!insert) {
            throw new BizException("合并失败");
        }
        boolean b = this.deleteBatchIds(idList);
        if (!b) {
            throw new BizException("合并失败");
        }
        return true;
    }

    @Override
    public boolean splitRoom(RoomSplitRequest roomSplitRequest) {
        boolean insertBatch = this.insertBatch(roomSplitRequest.getList());
        if (!insertBatch) {
            throw new BizException("拆分失败");
        }
        boolean b = this.deleteById(roomSplitRequest.getId());
        if (!b) {
            throw new BizException("拆分失败");
        }
        return false;
    }
}
