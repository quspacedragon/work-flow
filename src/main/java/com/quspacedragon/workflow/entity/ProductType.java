package com.quspacedragon.workflow.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.quspacedragon.workflow.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
@TableName("product_type")
@ApiModel(value = "产品类型对象")
public class ProductType extends BaseEntity<ProductType> {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("名称")
    @NotNull
    private String name;
    /**
     * 1房源2场地3广告位
     */
    @ApiModelProperty("业态分类 1房源2场地3广告位")
    @TableField("business_type")
    @NotNull
    private Integer businessType;
    @ApiModelProperty("备注")
    private String memo;
    @ApiModelProperty("编码")
    @NotNull
    private String code;


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


    public static final String NAME = "name";

    public static final String BUSINESS_TYPE = "business_type";

    public static final String MEMO = "memo";

    public static final String CODE = "code";

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
