package com.quspacedragon.workflow.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.Version;
import com.quspacedragon.workflow.entity.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
public class Job extends BaseEntity<Job> {

    private static final long serialVersionUID = 1L;

	private String name;
	@TableField("parent_id")
	private Integer parentId;
	private String memo;
	private Long createTime;


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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public static final String NAME = "name";

	public static final String PARENT_ID = "parent_id";

	public static final String MEMO = "memo";

	public static final String CREATETIME = "createTime";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Job{" +
			"name=" + name +
			", parentId=" + parentId +
			", memo=" + memo +
			", createTime=" + createTime +
			"}";
	}
}
