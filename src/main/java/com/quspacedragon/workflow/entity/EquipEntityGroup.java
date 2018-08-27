package com.quspacedragon.workflow.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.quspacedragon.workflow.entity.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author quspacedragon
 * @since 2018-08-27
 */
@TableName("equip_entity_group")
public class EquipEntityGroup extends BaseEntity<EquipEntityGroup> {

    private static final long serialVersionUID = 1L;

	@TableField("equip_entity_id")
	private Integer equipEntityId;
	@TableField("equip_group_id")
	private Integer equipGroupId;
	@TableField("check_project_id")
	private Integer checkProjectId;


	public Integer getEquipEntityId() {
		return equipEntityId;
	}

	public void setEquipEntityId(Integer equipEntityId) {
		this.equipEntityId = equipEntityId;
	}

	public Integer getEquipGroupId() {
		return equipGroupId;
	}

	public void setEquipGroupId(Integer equipGroupId) {
		this.equipGroupId = equipGroupId;
	}

	public Integer getCheckProjectId() {
		return checkProjectId;
	}

	public void setCheckProjectId(Integer checkProjectId) {
		this.checkProjectId = checkProjectId;
	}

	public static final String EQUIP_ENTITY_ID = "equip_entity_id";

	public static final String EQUIP_GROUP_ID = "equip_group_id";

	public static final String CHECK_PROJECT_ID = "check_project_id";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "EquipEntityGroup{" +
			"equipEntityId=" + equipEntityId +
			", equipGroupId=" + equipGroupId +
			", checkProjectId=" + checkProjectId +
			"}";
	}
}
