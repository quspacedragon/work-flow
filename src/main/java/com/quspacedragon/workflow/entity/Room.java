package com.quspacedragon.workflow.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.Version;
import com.quspacedragon.workflow.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
@ApiModel("房间")
public class Room extends BaseEntity<Room> {

    private static final long serialVersionUID = 1L;

    /**
     * 楼栋id
     */
    @TableField("building_id")
    @ApiModelProperty("楼栋id")
    private Integer buildingId;
    /**
     * 单元id
     */
    @TableField("unit_id")
    @ApiModelProperty("单元id")
    private Integer unitId;
    /**
     * 房号
     */
    @TableField("room_no")
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
    @ApiModelProperty("建筑面积 平米厘米")
    private Integer area;
    /**
     * 套内面积
     */
    @ApiModelProperty("套内面积")
    @TableField("inside_area")
    private Integer insideArea;
    @ApiModelProperty("产品类型id")
    @TableField("product_type_Id")
    private Integer productTypeId;
    @ApiModelProperty("业务类型id")
    @TableField("business_type")
    private Integer businessType;
    private Integer memo;


    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getInsideArea() {
        return insideArea;
    }

    public void setInsideArea(Integer insideArea) {
        this.insideArea = insideArea;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getMemo() {
        return memo;
    }

    public void setMemo(Integer memo) {
        this.memo = memo;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public static final String BUILDING_ID = "building_id";

    public static final String UNIT_ID = "unit_id";

    public static final String ROOM_NO = "room_no";

    public static final String FLOOR = "floor";

    public static final String CODE = "code";

    public static final String AREA = "area";

    public static final String INSIDE_AREA = "inside_area";

    public static final String PRODUCT_TYPE_ID = "product_type_Id";

    public static final String MEMO = "memo";


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Room{" +
                "buildingId=" + buildingId +
                ", unitId=" + unitId +
                ", roomNo=" + roomNo +
                ", floor=" + floor +
                ", code=" + code +
                ", area=" + area +
                ", insideArea=" + insideArea +
                ", productTypeId=" + productTypeId +
                ", memo=" + memo +
                ", createTime=" + createTime +
                "}";
    }
}
