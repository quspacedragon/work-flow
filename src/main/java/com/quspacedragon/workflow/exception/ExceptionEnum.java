package com.quspacedragon.workflow.exception;

/**
 * Title: ExceptionEnum
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/7
 */
public enum ExceptionEnum {
    UNKNOW_ERROR(-1, "未知错误"),
    USER_NOT_FIND(-101, "用户不存在"),
    TOKEN_EXPIRE(1, "token过期"),
    PHONE_NOT_NULL(401, "手机号不能为空"),;

    private Integer code;

    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
