package com.quspacedragon.workflow.entity;

import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-29
 */
public class Customer extends BaseEntity<Customer> {

    private static final long serialVersionUID = 1L;

    /**
     * ????
     */
    @TableField("customer_no")
    private String customerNo;
    /**
     * ????
     */
    @TableField("customer_name")
    private String customerName;
    /**
     * ????
     */
    private String code;
    private String cantat;
    private String phone;
    @TableField("enterprise_id")
    private Long enterpriseId;
    @TableField("login_name")
    private String loginName;
    @TableField("login_pwd")
    private String loginPwd;
    @TableField("device_token")
    private String deviceToken;
    @TableField("can_push")
    private Boolean canPush;


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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCantat() {
        return cantat;
    }

    public void setCantat(String cantat) {
        this.cantat = cantat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static final String CUSTOMER_NO = "customer_no";

    public static final String CUSTOMER_NAME = "customer_name";

    public static final String CODE = "code";

    public static final String CANTAT = "cantat";

    public static final String PHONE = "phone";

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }


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

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Boolean getCanPush() {
        return canPush;
    }

    public void setCanPush(Boolean canPush) {
        this.canPush = canPush;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerNo=" + customerNo +
                ", customerName=" + customerName +
                ", code=" + code +
                ", cantat=" + cantat +
                ", phone=" + phone +
                "}";
    }
}
