package com.quspacedragon.workflow.enums;


import java.util.Arrays;

/**
 * Title: BusinessTypeEnum
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/7/27
 */
public enum BusinessTypeEnum {

    residence(1, "住宅"),
    business(2, "商业"),
    industrial(3, "工业"),
    carport(4, "车位");

    private Integer type;
    private String name;

    private BusinessTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static BusinessTypeEnum findByType(Integer type) {
        if (type == null) {
            return null;
        }
        return Arrays.stream(values()).filter(r -> r.getType() == type).findFirst().orElse(null);
    }
}
