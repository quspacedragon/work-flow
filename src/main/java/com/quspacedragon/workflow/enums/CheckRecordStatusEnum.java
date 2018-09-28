package com.quspacedragon.workflow.enums;

/**
 * Title: CheckRecordStatusEnum
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/9/2
 */
public enum CheckRecordStatusEnum {
    TODO(0, "待处理"),
    TO_WORK(1, "已开工"),
    CHECKED(2, "已完工"),
    FAIL(-1, "失败"),;

    private Integer status;
    private String name;

    CheckRecordStatusEnum(int status, String name) {
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
