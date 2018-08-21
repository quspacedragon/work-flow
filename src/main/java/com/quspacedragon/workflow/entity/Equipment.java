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
 * @since 2018-08-21
 */
@ApiModel("设备")
public class Equipment extends BaseEntity<Equipment> {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("编码")
    private String code;
    @ApiModelProperty("名称")
    private String name;
    /**
     * 列表等级
     */
    @ApiModelProperty("列表等级")
    private Integer level;
    @ApiModelProperty("上级id")
    @TableField("parent_id")
    private Integer parentId;
    /**
     * 此等级下的编号
     */
    @ApiModelProperty("等级编号")
    @TableField("level_no")
    private Integer levelNo;


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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public static final String CODE = "code";

    public static final String NAME = "name";

    public static final String LEVEL = "level";

    public static final String PARENT_ID = "parent_id";

    public static final String LEVEL_NO = "level_no";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "code=" + code +
                ", name=" + name +
                ", level=" + level +
                ", parentId=" + parentId +
                ", levelNo=" + levelNo +
                "}";
    }
}
