package com.quspacedragon.workflow.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("scan_record")
public class ScanRecord extends BaseEntity<ScanRecord> {

    private static final long serialVersionUID = 1L;

	@TableField("bill_id")
	private Integer billId;
	@TableField("enterprise_id")
	private Integer enterpriseId;
	@TableField("op_id")
	private Integer opId;


	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
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
