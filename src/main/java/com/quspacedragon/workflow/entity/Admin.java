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
public class Admin extends BaseEntity<Admin> {

    private static final long serialVersionUID = 1L;

    /**
     * 手机号 唯一登录主键
     */
	private String phone;
    /**
     * 用户名
     */
	@TableField("user_name")
	private String userName;
    /**
     * 登录密码
     */
	@TableField("pass_word")
	private String passWord;
	private Long createTime;


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public static final String PHONE = "phone";

	public static final String USER_NAME = "user_name";

	public static final String PASS_WORD = "pass_word";

	public static final String CREATETIME = "createTime";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Admin{" +
			"phone=" + phone +
			", userName=" + userName +
			", passWord=" + passWord +
			", createTime=" + createTime +
			"}";
	}
}
