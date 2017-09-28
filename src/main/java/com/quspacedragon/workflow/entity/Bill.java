package com.quspacedragon.workflow.entity;

import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-27
 */
public class Bill extends BaseEntity<Bill> {

    private static final long serialVersionUID = 1L;

    @TableField("bill_no")
    private String billNo;
    private Integer weight;
    @TableField("vat_no")
    private String vatNo;
    /**
     * 品种
     */
    private Long varieties;
    /**
     * 颜色
     */
    private Long color;
    /**
     * 拆包人
     */
    @TableField("unpack_name")
    private String unpackName;
    /**
     * 数量
     */
    private Integer amout;
    @TableField("enterprise_id")
    private Long enterpriseId;
    @TableField("op_id")
    private Integer opId;
    /**
     * 处理序列
     */
    @TableField("process_order")
    private String processOrder;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public Long getVarieties() {
        return varieties;
    }

    public void setVarieties(Long varieties) {
        this.varieties = varieties;
    }

    public Long getColor() {
        return color;
    }

    public void setColor(Long color) {
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

    public static final String BILL_NO = "bill_no";

    public static final String WEIGHT = "weight";

    public static final String VAT_NO = "vat_no";

    public static final String VARIETIES = "varieties";

    public static final String COLOR = "color";

    public static final String UNPACK_NAME = "unpack_name";

    public static final String AMOUT = "amout";

    public static final String ENTERPRISE_ID = "enterprise_id";

    public static final String OP_ID = "op_id";

    public static final String PROCESS_ORDER = "process_order";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billNo=" + billNo +
                ", weight=" + weight +
                ", vatNo=" + vatNo +
                ", varieties=" + varieties +
                ", color=" + color +
                ", unpackName=" + unpackName +
                ", amout=" + amout +
                ", enterpriseId=" + enterpriseId +
                ", opId=" + opId +
                ", processOrder=" + processOrder +
                "}";
    }
}
