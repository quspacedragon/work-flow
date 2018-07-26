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
@TableName("sms_record")
public class SmsRecord extends BaseEntity<SmsRecord> {

    private static final long serialVersionUID = 1L;

	private String phone;
	private String code;
    /**
     * 是否失效
     */
	@TableField("is_expired")
	private Integer isExpired;


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(Integer isExpired) {
		this.isExpired = isExpired;
	}



	public static final String PHONE = "phone";

	public static final String CODE = "code";

	public static final String IS_EXPIRED = "is_expired";


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SmsRecord{" +
			"phone=" + phone +
			", code=" + code +
			", isExpired=" + isExpired +
			", createTime=" + createTime +
			"}";
	}
}
