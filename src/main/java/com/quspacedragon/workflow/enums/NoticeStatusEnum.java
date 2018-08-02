package com.quspacedragon.workflow.enums;

/**
 * Title: NoticeStatusEnum
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/8/1
 */
public enum NoticeStatusEnum {
    normal(0, "正常"),
    release(1, "已发布"),
    invalid(2, "已作废"),;
    private Integer status;
    private String name;

    NoticeStatusEnum(Integer status, String name) {
        this.status = status;
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
