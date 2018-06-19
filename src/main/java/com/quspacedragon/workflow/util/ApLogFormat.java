package com.quspacedragon.workflow.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Title: ApLogFormat
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/5/28
 */
public class ApLogFormat {
    private String title;
    private Map<String, Object> map = new HashMap();

    public ApLogFormat(String title) {
        this.title = title;
    }

    public static ApLogFormat init(String title) {
        return new ApLogFormat(title);
    }

    public ApLogFormat add(String key, Object v) {
        this.map.put(key, v);
        return this;
    }


    public String buildLogMsg() {
        return this.format();
    }

    private String format() {
        StringBuilder sb = new StringBuilder(128);
        if (StringUtils.isNotBlank(this.title)) {
            sb.append("[").append(this.title).append("]。");
        }
        sb.append("json:").append((map == null ? "{}" : JSON.toJSONString(map)));
        return sb.toString();
    }
}
