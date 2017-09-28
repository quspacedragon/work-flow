package com.quspacedragon.workflow.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

/**
 * Title: ConverUtils
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/28
 */
public class ConverUtils {

    public static Object conver(Object value, Class clazz) {
        try {
            Object o = clazz.newInstance();
            BeanUtils.copyProperties(value, o);
            return o;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (BeansException e) {
            e.printStackTrace();
        }
        return null;
    }
}
