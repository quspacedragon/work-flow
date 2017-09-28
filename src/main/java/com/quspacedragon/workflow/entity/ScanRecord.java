package com.quspacedragon.workflow.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-27
 */
@TableName("scan_record")
public class ScanRecord extends BaseEntity<ScanRecord> {

    private static final long serialVersionUID = 1L;

    @TableField("bill_id")
    private Long billId;
    @TableField("enterprise_id")
    private Long enterpriseId;
    @TableField("op_id")
    private Long opId;


    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
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

    public static final String BILL_ID = "bill_id";

    public static final String ENTERPRISE_ID = "enterprise_id";

    public static final String OP_ID = "op_id";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ScanRecord{" +
                "billId=" + billId +
                ", enterpriseId=" + enterpriseId +
                ", opId=" + opId +
                "}";
    }
}
