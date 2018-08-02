package com.quspacedragon.workflow.enums;

/**
 * Title: NoticeTypeEnum
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/7/29
 */
public enum NoticeTypeEnum {
    notice(1, "通知"),
    activity(2, "活动"),
    advert(3, "广告"),
    convenience(4, "便民电话");


    private Integer type;
    private String name;

    NoticeTypeEnum(Integer type, String name) {
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
}
