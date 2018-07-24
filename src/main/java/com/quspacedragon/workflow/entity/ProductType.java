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
 * @since 2018-07-24
 */
@TableName("product_type")
public class ProductType extends BaseEntity<ProductType> {

    private static final long serialVersionUID = 1L;

	private String name;
    /**
     * 1房源2场地3广告位
     */
	@TableField("business_type")
	private Integer businessType;
	private String memo;
	private String code;
	private Long createTime;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public static final String NAME = "name";

	public static final String BUSINESS_TYPE = "business_type";

	public static final String MEMO = "memo";

	public static final String CODE = "code";

	public static final String CREATETIME = "createTime";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ProductType{" +
			"name=" + name +
			", businessType=" + businessType +
			", memo=" + memo +
			", code=" + code +
			", createTime=" + createTime +
			"}";
	}
}
