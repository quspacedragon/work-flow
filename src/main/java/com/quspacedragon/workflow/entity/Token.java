package com.quspacedragon.workflow.entity;

import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-27
 */
public class Token extends BaseEntity<Token> {

    private static final long serialVersionUID = 1L;

    private String token;
    @TableField("enterprise_id")
    private Long enterpriseId;
    @TableField("request_time")
    private Long requestTime;
    @TableField("expired_time")
    private Long expiredTime;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }

    public Long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public static final String TOKEN = "token";

    public static final String ENTERPRISE_ID = "enterprise_id";

    public static final String REQUEST_TIME = "request_time";

    public static final String EXPIRED_TIME = "expired_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token=" + token +
                ", enterpriseId=" + enterpriseId +
                ", requestTime=" + requestTime +
                ", expiredTime=" + expiredTime +
                "}";
    }
}
