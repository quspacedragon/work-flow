package com.quspacedragon.workflow.util;

import java.util.UUID;

/**
 * Title: UUIDUtils
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/28
 */
public class UUIDUtils {
    public static String uuid() {
        String s = UUID.randomUUID().toString();
        return s.replaceAll("-", "");
    }
}
