package com.quspacedragon.workflow.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.Version;
import com.quspacedragon.workflow.entity.BaseEntity;
import io.swagger.annotations.ApiModel;

/**
 * <p>
 * 
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
public class Notice extends BaseEntity<Notice> {

    private static final long serialVersionUID = 1L;

	@TableField("create_admind_id")
	private Integer createAdmindId;
	private String title;
    /**
     * 级别
     */
	private Integer level;
    /**
     * 访问数量
     */
	@TableField("view_num")
	private Integer viewNum;
	private String url;
	@TableField("project_id")
	private Integer projectId;
	@TableField("building_id")
	private Integer buildingId;
	@TableField("unit_id")
	private Integer unitId;
	private String content;
	@TableField("activity_id")
	private Integer activityId;
	private String phone;
	private String memo;
	@TableField("send_time")
	private Date sendTime;


	public Integer getCreateAdmindId() {
		return createAdmindId;
	}

	public void setCreateAdmindId(Integer createAdmindId) {
		this.createAdmindId = createAdmindId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getViewNum() {
		return viewNum;
	}

	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}


	public static final String CREATE_ADMIND_ID = "create_admind_id";

	public static final String TITLE = "title";

	public static final String LEVEL = "level";

	public static final String VIEW_NUM = "view_num";

	public static final String URL = "url";

	public static final String PROJECT_ID = "project_id";

	public static final String BUILDING_ID = "building_id";

	public static final String UNIT_ID = "unit_id";

	public static final String CONTENT = "content";

	public static final String ACTIVITY_ID = "activity_id";

	public static final String PHONE = "phone";

	public static final String MEMO = "memo";

	public static final String SEND_TIME = "send_time";


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Notice{" +
			"createAdmindId=" + createAdmindId +
			", title=" + title +
			", level=" + level +
			", viewNum=" + viewNum +
			", url=" + url +
			", projectId=" + projectId +
			", buildingId=" + buildingId +
			", unitId=" + unitId +
			", content=" + content +
			", activityId=" + activityId +
			", phone=" + phone +
			", memo=" + memo +
			", sendTime=" + sendTime +
			", createTime=" + createTime +
			"}";
	}
}
