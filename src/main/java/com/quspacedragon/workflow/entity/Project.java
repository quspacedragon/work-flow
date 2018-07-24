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
public class Project extends BaseEntity<Project> {

    private static final long serialVersionUID = 1L;

    /**
     * 项目名字
     */
	@TableField("project_name")
	private String projectName;
	private Long createTime;
	@TableField("pinyin_name")
	private String pinyinName;


	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getPinyinName() {
		return pinyinName;
	}

	public void setPinyinName(String pinyinName) {
		this.pinyinName = pinyinName;
	}

	public static final String PROJECT_NAME = "project_name";

	public static final String CREATETIME = "createTime";

	public static final String PINYIN_NAME = "pinyin_name";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Project{" +
			"projectName=" + projectName +
			", createTime=" + createTime +
			", pinyinName=" + pinyinName +
			"}";
	}
}
