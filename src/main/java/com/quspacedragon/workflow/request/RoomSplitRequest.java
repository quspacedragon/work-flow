package com.quspacedragon.workflow.request;

import com.quspacedragon.workflow.entity.Room;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Title: RoomSplitRequest
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/7/29
 */
@Data
public class RoomSplitRequest {
    @ApiModelProperty("被拆分的房间id")
    private Integer id;
    @ApiModelProperty("拆分后的房间列表")
    private List<Room> list;

}
