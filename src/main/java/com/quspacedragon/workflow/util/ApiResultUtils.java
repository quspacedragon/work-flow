package com.quspacedragon.workflow.util;

import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.exception.ExceptionEnum;
import org.springframework.http.HttpStatus;

/**
 * Title: ApiResultUtils
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/7
 */
public class ApiResultUtils {

    //返回码 0：失败
    public static final int FAIL_CODE = HttpStatus.INTERNAL_SERVER_ERROR.value();

    /**
     * 默认的返回结果
     *
     * @return Result
     */
    public static Result defaultResult() {
        return new Result();
    }

    /**
     * 成功的返回结果
     *
     * @param data 返回内容
     * @return Result
     */
    public static Result successResult(Object data) {
        Result result = defaultResult();
        result.setData(data);
        result.setCode(HttpStatus.OK.value());
        result.setSuccess(true);
        return result;
    }

    /**
     * 失败的返回结果
     *
     * @param errorMessage 错误信息
     * @return Result
     */
    public static Result failResult(String errorMessage) {
        return failResult(FAIL_CODE, errorMessage);
    }

    /**
     * 失败的返回结果
     *
     * @param errorCode    返回码
     * @param errorMessage 错误信息
     * @return Result
     */
    public static Result failResult(int errorCode, String errorMessage) {
        Result result = defaultResult();
        result.setSuccess(false);
        result.setCode(errorCode);
        result.setMessage(errorMessage);
        return result;
    }

    /**
     * 失败的返回结果
     *
     * @return Result
     */
    public static Result failResult(ExceptionEnum exceptionEnum) {
        Result result = defaultResult();
        result.setSuccess(false);
        result.setCode(exceptionEnum.getCode());
        result.setMessage(exceptionEnum.getMsg());
        return result;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
}
