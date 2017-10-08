package com.quspacedragon.workflow.common;

/**
 * Title: LoginUserTypeEnum
 * <p>
 * Description: 登录用户类型
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/10/8
 */
public enum LoginUserTypeEnum {
    ENTERPRISE(1, "厂家"), STAFF(2, "员工"), CUSTOMER(3, "商家客户");
    private int type;
    private String name;

    LoginUserTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
