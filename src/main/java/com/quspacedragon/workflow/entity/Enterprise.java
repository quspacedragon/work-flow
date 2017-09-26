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
 * @since 2017-09-24
 */
public class Enterprise extends BaseEntity<Enterprise> {

    private static final long serialVersionUID = 1L;

	@TableField("login_name")
	private String loginName;
	@TableField("login_pwd")
	private String loginPwd;
	@TableField("enterprise_no")
	private String enterpriseNo;


	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getEnterpriseNo() {
		return enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	public static final String LOGIN_NAME = "login_name";

	public static final String LOGIN_PWD = "login_pwd";

	public static final String ENTERPRISE_NO = "enterprise_no";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Enterprise{" +
			"loginName=" + loginName +
			", loginPwd=" + loginPwd +
			", enterpriseNo=" + enterpriseNo +
			"}";
	}
}
