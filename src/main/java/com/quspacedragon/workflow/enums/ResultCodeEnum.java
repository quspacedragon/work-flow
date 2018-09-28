package com.quspacedragon.workflow.enums;

/**
 * Title: ResultCodeEnum
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/9/6
 */
public enum ResultCodeEnum {
    TOKEN_EXPIRE(204001, "token过期"),
    NOT_LOGIN(204002, "未登录"),;

    private int code;
    private String name;

    ResultCodeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
