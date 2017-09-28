package com.quspacedragon.workflow.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Title: GoodsVo
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/28
 */
public class GoodsVo extends BaseVo {
    @ApiModelProperty(value = "货物编号")
    private String goodsNo;
    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
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
}
