package com.quspacedragon.workflow.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author quspacedragon
 * @since 2018-08-27
 */
@ApiModel("设备分组")
@TableName("equip_group")
public class EquipGroup extends BaseEntity<EquipGroup> {

    private static final long serialVersionUID = 1L;

    @TableField("group_name")

    private String groupName;
    /**
     * 维保人
     */
    @TableField("maintain_id")
    @ApiModelProperty("维保人")
    private Integer maintainId;
    /**
     * 维护人
     */
    @TableField("fix_id")
    @ApiModelProperty("维护人")
    private Integer fixId;
    private String code;
    private String name;


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getMaintainId() {
        return maintainId;
    }

    public void setMaintainId(Integer maintainId) {
        this.maintainId = maintainId;
    }

    public Integer getFixId() {
        return fixId;
    }

    public void setFixId(Integer fixId) {
        this.fixId = fixId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final String GROUP_NAME = "group_name";

    public static final String MAINTAIN_ID = "maintain_id";

    public static final String FIX_ID = "fix_id";

    public static final String CODE = "code";

    public static final String NAME = "name";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "EquipGroup{" +
                "groupName=" + groupName +
                ", maintainId=" + maintainId +
                ", fixId=" + fixId +
                ", code=" + code +
                ", name=" + name +
                "}";
    }
}
