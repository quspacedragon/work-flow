package com.quspacedragon.workflow.request;

import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Title: RoomMergeRequest
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/7/27
 */
@Data
public class RoomMergeRequest {
    @ApiModelProperty("需要合并的房间列表")
    private List<Integer> idList;
    /**
     * 楼栋id
     */
    @ApiModelProperty("楼栋id")
    private Integer buildingId;
    /**
     * 单元id
     */
    @ApiModelProperty("单元id")
    private Integer unitId;
    /**RoomMergeRequest
     * 房号
     */
    @ApiModelProperty("房号")
    private String roomNo;
    /**
     * 楼层
     */
    @ApiModelProperty("楼层")
    private Integer floor;
    @ApiModelProperty("编码")
    private String code;
    /**
     * 建筑面积 平方厘米
     */
    @ApiModelProperty("建筑面积")
    private Integer area;
    /**
     * 套内面积
     */
    @ApiModelProperty("套内面积")
    private Integer insideArea;
    @ApiModelProperty("产品类型")
    private Integer productTypeId;
    @ApiModelProperty("业务类型")
    private Integer businessType;
    @ApiModelProperty("备注")
    private Integer memo;

}
