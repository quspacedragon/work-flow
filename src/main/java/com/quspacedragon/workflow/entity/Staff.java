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
public class Staff extends BaseEntity<Staff> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
	@TableField("customer_no")
	private String customerNo;
    /**
     * 客户名称
     */
	@TableField("customer_name")
	private String customerName;
    /**
     * 拼音简码
     */
	@TableField("short_name")
	private String shortName;
    /**
     * 登录密码
     */
	@TableField("login_pwd")
	private String loginPwd;
    /**
     * 所属部门
     */
	private String department;
    /**
     * 手机
     */
	private String phone;
	@TableField("enterprise_id")
	private Integer enterpriseId;


	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public static final String CUSTOMER_NO = "customer_no";

	public static final String CUSTOMER_NAME = "customer_name";

	public static final String SHORT_NAME = "short_name";

	public static final String LOGIN_PWD = "login_pwd";

	public static final String DEPARTMENT = "department";

	public static final String PHONE = "phone";

	public static final String ENTERPRISE_ID = "enterprise_id";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Staff{" +
			"customerNo=" + customerNo +
			", customerName=" + customerName +
			", shortName=" + shortName +
			", loginPwd=" + loginPwd +
			", department=" + department +
			", phone=" + phone +
			", enterpriseId=" + enterpriseId +
			"}";
	}
}
