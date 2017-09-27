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
	private Integer enterpriseId;


	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
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
