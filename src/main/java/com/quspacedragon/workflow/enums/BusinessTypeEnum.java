package com.quspacedragon.workflow.enums;

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
}
