package com.quspacedragon.workflow.enums;

/**
 * Title: WorkOrderStatus
 * <p>
 * Description: 工单状态
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/9/4
 */
public enum WorkOrderStatusEnum {
    TODO(0, "待分派"),
    DISPATCHED(1, "已分派"),
    PROCESSING(2, "处理中"),
    PROCESSED(3,"已处理"),
    ;

    private Integer status;
    private String name;

    WorkOrderStatusEnum(Integer status, String name) {
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
