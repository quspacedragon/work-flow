package com.quspacedragon.workflow.enums;

/**
 * Title: SmsRecordStatus
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/7/25
 */
public enum SmsRecordStatus {
    not_use(0, "未使用"),
    userd(1, "已使用");
    private Integer sattus;
    private String name;

    private SmsRecordStatus(Integer sattus, String name) {
        this.sattus = sattus;
        this.name = name;
    }

    public Integer getSattus() {
        return sattus;
    }

    public void setSattus(Integer sattus) {
        this.sattus = sattus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
