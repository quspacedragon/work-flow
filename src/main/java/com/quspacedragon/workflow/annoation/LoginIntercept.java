package com.quspacedragon.workflow.annoation;

import java.lang.annotation.*;

/**
 * springMvc拦截器中 根据此注解判定是否要登录
 * <p>
 * method 级别   > class级别
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginIntercept {

    /**
     * @return default true
     */
    boolean value() default true;
}
