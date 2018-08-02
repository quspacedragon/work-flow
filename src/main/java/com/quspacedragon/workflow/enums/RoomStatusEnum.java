package com.quspacedragon.workflow.enums;

/**
 * Title: RoomStatusEnum
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/7/27
 */
public enum RoomStatusEnum {
    normal(0, "正常状态"),
    invalid(-1, "作废"),
    living(1, "已入住"),
    empty(2, "空置");

    private Integer status;
    private String name;

    RoomStatusEnum(Integer status, String name) {
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
