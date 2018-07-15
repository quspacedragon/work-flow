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
 * @since 2018-07-15
 */
@TableName("win_record")
public class WinRecord extends BaseEntity<WinRecord> {

    private static final long serialVersionUID = 1L;

	private String openid;
	private String unionid;
	private String phone;
	@TableField("lottery_price_id")
	private String lotteryPriceId;
	@TableField("lottery_price_name")
	private String lotteryPriceName;
    /**
     * 中奖数量
     */
	@TableField("lottery_price_num")
	private Integer lotteryPriceNum;
	private Long createTime;


	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLotteryPriceId() {
		return lotteryPriceId;
	}

	public void setLotteryPriceId(String lotteryPriceId) {
		this.lotteryPriceId = lotteryPriceId;
	}

	public String getLotteryPriceName() {
		return lotteryPriceName;
	}

	public void setLotteryPriceName(String lotteryPriceName) {
		this.lotteryPriceName = lotteryPriceName;
	}

	public Integer getLotteryPriceNum() {
		return lotteryPriceNum;
	}

	public void setLotteryPriceNum(Integer lotteryPriceNum) {
		this.lotteryPriceNum = lotteryPriceNum;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public static final String OPENID = "openid";

	public static final String UNIONID = "unionid";

	public static final String PHONE = "phone";

	public static final String LOTTERY_PRICE_ID = "lottery_price_id";

	public static final String LOTTERY_PRICE_NAME = "lottery_price_name";

	public static final String LOTTERY_PRICE_NUM = "lottery_price_num";

	public static final String CREATETIME = "createTime";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "WinRecord{" +
			"openid=" + openid +
			", unionid=" + unionid +
			", phone=" + phone +
			", lotteryPriceId=" + lotteryPriceId +
			", lotteryPriceName=" + lotteryPriceName +
			", lotteryPriceNum=" + lotteryPriceNum +
			", createTime=" + createTime +
			"}";
	}
}
