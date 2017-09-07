package com.quspacedragon.workflow.aop;

import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.exception.BizException;
import com.quspacedragon.workflow.exception.ExceptionEnum;
import com.quspacedragon.workflow.util.ApiResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Title: ExceptionHandle
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/7
 */
@ControllerAdvice
public class ExceptionHandle {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandle.class);

    /**
     * 判断错误是否是已定义的已知错误，不是则由未知错误代替，同时记录在log中
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionGet(Exception e) {
        if (e instanceof BizException) {
            BizException MyException = (BizException) e;
            return ApiResultUtils.failResult(MyException.getCode(), MyException.getMessage());
        }

        LOGGER.error("【系统异常】{}", e);
        return ApiResultUtils.failResult(ExceptionEnum.UNKNOW_ERROR);
    }
}
