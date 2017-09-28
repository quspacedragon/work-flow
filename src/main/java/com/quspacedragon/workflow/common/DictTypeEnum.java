package com.quspacedragon.workflow.common;

/**
 * Title: DIctType
 * <p>
 * Description: 字段表类型
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/26
 */
public enum DictTypeEnum {
    VARIETIES(1, "品种"),
    COLOR(2, "颜色");
    private int type;
    private String name;

    DictTypeEnum(int type, String name) {
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

    public static boolean contains(int type) {
        DictTypeEnum[] values = values();
        for (DictTypeEnum value : values) {
            if (value.getType() == type) {
                return true;
            }
        }
        return false;
    }
}
