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
@ApiModel("单元信息")
public class Unit extends BaseEntity<Unit> {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("单元名字")
    private String name;
    @ApiModelProperty("排序")
    private Integer order;
    @TableField("building_id")
    @ApiModelProperty("楼栋id")
    private Integer buildingId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }


    public static final String NAME = "name";

    public static final String ORDER = "order";

    public static final String BUILDING_ID = "building_id";


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "name=" + name +
                ", order=" + order +
                ", buildingId=" + buildingId +
                ", createTime=" + createTime +
                "}";
    }
}
