package com.baomidou.test.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import com.quspacedragon.workflow.entity.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-12
 */
public class User extends BaseEntity<User> {

    private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	@TableField("is_valid")
    @TableLogic
	private Integer isValid;
	@TableField("op_time")
	private Long opTime;
	@TableField("last_ver")
	@Version
	private Integer lastVer;
	private Integer status;
	private Integer type;
	private String attribute;
	@TableField("create_time")
	private Long createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Long getOpTime() {
		return opTime;
	}

	public void setOpTime(Long opTime) {
		this.opTime = opTime;
	}

	public Integer getLastVer() {
		return lastVer;
	}

	public void setLastVer(Integer lastVer) {
		this.lastVer = lastVer;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", name=" + name +
			", isValid=" + isValid +
			", opTime=" + opTime +
			", lastVer=" + lastVer +
			", status=" + status +
			", type=" + type +
			", attribute=" + attribute +
			", createTime=" + createTime +
			"}";
	}
}
