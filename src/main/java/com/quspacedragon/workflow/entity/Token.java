package com.quspacedragon.workflow.entity;

import java.io.Serializable;

import java.util.Date;
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
 * @since 2018-07-24
 */
public class Token extends BaseEntity<Token> {

    private static final long serialVersionUID = 1L;

	private String token;
	@TableField("user_id")
	private Integer userId;
    /**
     * 请求时间
     */
	@TableField("request_time")
	private Date requestTime;
    /**
     * 过期时间
     */
	@TableField("expired_time")
	private Date expiredTime;
	private Long createTime;


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public static final String TOKEN = "token";

	public static final String USER_ID = "user_id";

	public static final String REQUEST_TIME = "request_time";

	public static final String EXPIRED_TIME = "expired_time";

	public static final String CREATETIME = "createTime";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Token{" +
			"token=" + token +
			", userId=" + userId +
			", requestTime=" + requestTime +
			", expiredTime=" + expiredTime +
			", createTime=" + createTime +
			"}";
	}
}
