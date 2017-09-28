package com.quspacedragon.workflow.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Title: StaffVo
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/27
 */
public class StaffVo extends BaseVo {
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private String customerNo;
    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    private String customerName;
    /**
     * 拼音简码
     */
    @ApiModelProperty(value = "拼音简码")
    private String shortName;
    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码")
    private String loginPwd;
    /**
     * 所属部门
     */
    @ApiModelProperty(value = "所属部门")
    private String department;
    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String phone;
    @ApiModelProperty(value = "企业id")
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
}
