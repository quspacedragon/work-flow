package com.quspacedragon.workflow.enums;

import com.quspacedragon.workflow.exception.BizException;

import java.util.Arrays;

/**
 * Title: LoginUserTypeEnum
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/7/24
 */
public enum LoginUserTypeEnum {

    ADMIN(1, "管理员"),
    STAFF(2, "员工"),
    USER(3, "用户");

    LoginUserTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    private int type;
    private String name;

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

    public static LoginUserTypeEnum findByType(Integer type) {
        if (type == null) {
            throw new BizException("不支持的短信类型");
        }
        LoginUserTypeEnum smsTypeEnum = Arrays.stream(LoginUserTypeEnum.values())
                .filter(r -> r.getType() == type)
                .findFirst()
                .orElseThrow(() -> new BizException("不支持的登录类型"));
        return smsTypeEnum;
    }
}
