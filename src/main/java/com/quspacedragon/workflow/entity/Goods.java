package com.quspacedragon.workflow.entity;

import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-27
 */
public class Goods extends BaseEntity<Goods> {

    private static final long serialVersionUID = 1L;

	@TableField("goods_no")
	private String goodsNo;
    /**
     * 企业id
     */
	@TableField("enterprise_id")
	private Long enterpriseId;


	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public static final String GOODS_NO = "goods_no";

	public static final String ENTERPRISE_ID = "enterprise_id";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Goods{" +
			"goodsNo=" + goodsNo +
			", enterpriseId=" + enterpriseId +
			"}";
	}
}
