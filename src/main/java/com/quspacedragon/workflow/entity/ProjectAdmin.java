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
 * @since 2018-07-24
 */
@TableName("project_admin")
public class ProjectAdmin extends BaseEntity<ProjectAdmin> {

    private static final long serialVersionUID = 1L;

	@TableField("project_id")
	private Integer projectId;
	@TableField("admin_id")
	private Integer adminId;
	private Long createTime;


	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public static final String PROJECT_ID = "project_id";

	public static final String ADMIN_ID = "admin_id";

	public static final String CREATETIME = "createTime";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ProjectAdmin{" +
			"projectId=" + projectId +
			", adminId=" + adminId +
			", createTime=" + createTime +
			"}";
	}
}
