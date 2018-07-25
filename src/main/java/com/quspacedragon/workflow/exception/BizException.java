package com.quspacedragon.workflow.exception;

/**
 * Title: BizException
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/7
 */
public class BizException extends RuntimeException {
    private Integer code;

    /**
     * 继承exception，加入错误状态值
     *
     * @param exceptionEnum
     */
    public BizException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }

    /**
     * 自定义错误信息
     *
     * @param message
     * @param code
     */
    public BizException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * 自定义错误信息
     *
     * @param message
     * @param code
     */
    public BizException(String message) {
        super(message);
        this.code = ExceptionEnum.UNKNOW_ERROR.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
