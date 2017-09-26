package com.quspacedragon.workflow.common;

/**
 * Title: Result
 * <p>
 * Description: 基础返回结果
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/7
 */
public class Result<T> {
    private int code;
    private String message;
    private Boolean success;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
