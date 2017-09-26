package com.quspacedragon.workflow.common;

/**
 * Title: ResultCodeEnum
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/26
 */
public enum ResultCodeEnum {
    SUCCESS(200, "成功"),
    ERROR(500, "未知错误");
    private final int code;
    private final String message;

    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

}
