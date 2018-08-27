package com.quspacedragon.workflow.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author quspacedragon
 * @since 2018-08-27
 */
@ApiModel("设备台账")
@TableName("equip_entity")
@Data
public class EquipEntity extends BaseEntity<EquipEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;
    /**
     * 编码
     */
    @ApiModelProperty("编码")
    private String code;
    /**
     * 设备类型id
     */
    @ApiModelProperty("设备类型id")
    @TableField("equipment_id")
    private Integer equipmentId;
    /**
     * 等级
     */
    @ApiModelProperty("等级")
    private Integer level;
    /**
     * 供应商
     */
    @ApiModelProperty("供应商")
    @TableField("supply_info")
    private String supplyInfo;
    /**
     * 购置日期
     */
    @ApiModelProperty("购买日期")
    @TableField("buy_time")
    private Long buyTime;
    @ApiModelProperty("描述")
    private String memo;
    @ApiModelProperty("巡检项目id")
    @TableField("check_project_id")
    private Integer checkProjectId;
    @ApiModelProperty("维保人id")
    @TableField("maintain_id")
    private Integer maintainId;
    @ApiModelProperty("巡检人id")
    @TableField("fix_id")
    private Integer fixId;


    public static final String NAME = "name";

    public static final String CODE = "code";

    public static final String EQUIPMENT_ID = "equipment_id";

    public static final String LEVEL = "level";

    public static final String SUPPLY_INFO = "supply_info";

    public static final String BUY_TIME = "buy_time";

    public static final String MEMO = "memo";

    public static final String CHECK_PROJECT_ID = "check_project_id";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "EquipEntity{" +
                "name=" + name +
                ", code=" + code +
                ", equipmentId=" + equipmentId +
                ", level=" + level +
                ", supplyInfo=" + supplyInfo +
                ", buyTime=" + buyTime +
                ", memo=" + memo +
                ", checkProjectId=" + checkProjectId +
                "}";
    }
}
