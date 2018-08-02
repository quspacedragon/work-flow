package com.quspacedragon.workflow.enums;

import com.quspacedragon.workflow.exception.BizException;

import java.util.Arrays;

/**
 * Title: SmsTypeEnum
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/7/25
 */
public enum SmsTypeEnum {
    update_login_phone(1, "修改登录账户");

    private int type;
    private String name;

    SmsTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static SmsTypeEnum findByType(Integer type) {
        if (type == null) {
            throw new BizException("不支持的短信类型");
        }
        SmsTypeEnum smsTypeEnum = Arrays.stream(SmsTypeEnum.values())
                .filter(r -> r.getType() == type)
                .findFirst()
                .orElseThrow(() -> new BizException("不支持的短信类型"));
        return smsTypeEnum;
    }
}
