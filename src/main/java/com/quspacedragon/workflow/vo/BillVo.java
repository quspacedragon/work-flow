package com.quspacedragon.workflow.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Title: BillVo
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/28
 */
public class BillVo {
    @ApiModelProperty(value = "订单编号")
    private String billNo;
    @ApiModelProperty(value = "重量")
    private Integer weight;
    @ApiModelProperty(value = "缸号")
    private String vatNo;
    /**
     * 品种
     */
    @ApiModelProperty(value = "品种")
    private Integer varieties;
    /**
     * 颜色
     */
    @ApiModelProperty(value = "颜色")
    private Integer color;
    /**
     * 拆包人
     */
    @ApiModelProperty(value = "拆包人")
    private String unpackName;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer amout;
    @ApiModelProperty(value = "企业id")
    private Integer enterpriseId;
    @ApiModelProperty(value = "操作人id")
    private Integer opId;
    /**
     * 处理序列
     */
    @ApiModelProperty(value = "处理序列")
    private String processOrder;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getVatNo() {
        return vatNo;
    }

    public void setVatNo(String vatNo) {
        this.vatNo = vatNo;
    }

    public Integer getVarieties() {
        return varieties;
    }

    public void setVarieties(Integer varieties) {
        this.varieties = varieties;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public String getUnpackName() {
        return unpackName;
    }

    public void setUnpackName(String unpackName) {
        this.unpackName = unpackName;
    }

    public Integer getAmout() {
        return amout;
    }

    public void setAmout(Integer amout) {
        this.amout = amout;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public String getProcessOrder() {
        return processOrder;
    }

    public void setProcessOrder(String processOrder) {
        this.processOrder = processOrder;
    }
}
