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
public class Apply extends BaseEntity<Apply> {

    private static final long serialVersionUID = 1L;

	@TableField("room_id")
	private Integer roomId;
	@TableField("user_id")
	private Integer userId;
	private String reason;
    /**
     * 短信验证码
     */
	private String code;
    /**
     * 联系人
     */
	@TableField("contact_name")
	private String contactName;
    /**
     * 联系号码
     */
	private String phone;
	private String content;
	private String url;


	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static final String ROOM_ID = "room_id";

	public static final String USER_ID = "user_id";

	public static final String REASON = "reason";

	public static final String CODE = "code";

	public static final String CONTACT_NAME = "contact_name";

	public static final String PHONE = "phone";

	public static final String CONTENT = "content";

	public static final String URL = "url";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Apply{" +
			"roomId=" + roomId +
			", userId=" + userId +
			", reason=" + reason +
			", code=" + code +
			", contactName=" + contactName +
			", phone=" + phone +
			", content=" + content +
			", url=" + url +
			"}";
	}
}
