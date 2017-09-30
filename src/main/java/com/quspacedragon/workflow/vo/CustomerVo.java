package com.quspacedragon.workflow.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Title: CustomerVo
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/30
 */
public class CustomerVo extends BaseVo {
    @ApiModelProperty(value = "商户号")
    private String customerNo;
    @ApiModelProperty(value = "商户名称")
    private String customerName;
    @ApiModelProperty(value = "拼音简码")
    private String code;
    @ApiModelProperty(value = "联系人")
    private String cantat;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "企业id")
    private Long enterpriseId;

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

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}
