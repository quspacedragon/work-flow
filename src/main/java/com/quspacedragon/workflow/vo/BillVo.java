package com.quspacedragon.workflow.vo;

import com.quspacedragon.workflow.entity.ScanRecord;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Title: BillVo
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/28
 */
public class BillVo extends BaseVo {
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
    private String varieties;
    /**
     * 颜色
     */
    @ApiModelProperty(value = "颜色")
    private String color;
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
    private Long enterpriseId;
    @ApiModelProperty(value = "操作人id")
    private Long opId;
    /**
     * 处理序列
     */
    @ApiModelProperty(value = "处理序列")
    private String processOrder;
    @ApiModelProperty(value = "企业信息")
    private EnterpriseVo enterpriseVo;
    @ApiModelProperty(value = "客户姓名")
    private String customerName;
    @ApiModelProperty(value = "货物编号")
    private String goodsNo;
    @ApiModelProperty(value = "客户编号")
    private String customerNo;
    @ApiModelProperty(value = "操作记录")
    private List<ScanRecord> scanRecords;
    @ApiModelProperty(value = "备注")
    private String memo;
    @ApiModelProperty(value = "联系人")
    private String cantat;
    @ApiModelProperty(value = "联系电话")
    private String phone;

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

    public String getVarieties() {
        return varieties;
    }

    public void setVarieties(String varieties) {
        this.varieties = varieties;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
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

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getOpId() {
        return opId;
    }

    public void setOpId(Long opId) {
        this.opId = opId;
    }

    public String getProcessOrder() {
        return processOrder;
    }

    public void setProcessOrder(String processOrder) {
        this.processOrder = processOrder;
    }

    public EnterpriseVo getEnterpriseVo() {
        return enterpriseVo;
    }

    public void setEnterpriseVo(EnterpriseVo enterpriseVo) {
        this.enterpriseVo = enterpriseVo;
    }

    public List<ScanRecord> getScanRecords() {
        return scanRecords;
    }

    public void setScanRecords(List<ScanRecord> scanRecords) {
        this.scanRecords = scanRecords;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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
}
