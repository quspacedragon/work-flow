package com.quspacedragon.workflow.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.Version;
import com.quspacedragon.workflow.entity.BaseEntity;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
public class Role extends BaseEntity<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String name;
    /**
     * 上级id
     */
    @TableField("parent_id")
    private Integer parentId;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }



    public static final String NAME = "name";

    public static final String PARENT_ID = "parent_id";


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name=" + name +
                ", parentId=" + parentId +
                ", createTime=" + createTime +
                "}";
    }
}
