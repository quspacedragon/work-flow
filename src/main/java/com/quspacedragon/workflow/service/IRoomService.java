package com.quspacedragon.workflow.service;

import com.baomidou.mybatisplus.service.IService;
import com.quspacedragon.workflow.entity.Room;
import com.quspacedragon.workflow.request.RoomMergeRequest;
import com.quspacedragon.workflow.request.RoomSplitRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
public interface IRoomService extends IService<Room> {
    public Room findByCode(String code);

    public boolean mergeRoom(RoomMergeRequest roomMergeRequest);


    public boolean splitRoom(RoomSplitRequest roomSplitRequest);

}
