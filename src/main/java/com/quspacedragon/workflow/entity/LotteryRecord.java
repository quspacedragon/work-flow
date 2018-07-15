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
@TableName("lottery_record")
public class LotteryRecord extends BaseEntity<LotteryRecord> {

    private static final long serialVersionUID = 1L;

	@TableField("lottery_id")
	private String lotteryId;
	private String unionid;
	private String openid;
	private String phone;
	private Long createTime;


	public String getLotteryId() {
		return lotteryId;
	}

	public void setLotteryId(String lotteryId) {
		this.lotteryId = lotteryId;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public static final String LOTTERY_ID = "lottery_id";

	public static final String UNIONID = "unionid";

	public static final String OPENID = "openid";

	public static final String PHONE = "phone";

	public static final String CREATETIME = "createTime";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "LotteryRecord{" +
			"lotteryId=" + lotteryId +
			", unionid=" + unionid +
			", openid=" + openid +
			", phone=" + phone +
			", createTime=" + createTime +
			"}";
	}
}
