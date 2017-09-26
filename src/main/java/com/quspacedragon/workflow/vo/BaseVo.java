package com.quspacedragon.workflow.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.quspacedragon.workflow.common.CustomDateSerializer;
import io.swagger.annotations.ApiModelProperty;

/**
 * Title: BaseVo
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/26
 */
public class BaseVo {
    @ApiModelProperty(value = "主键id")
    protected Long id;
    @ApiModelProperty(value = "上次更新时间")
    @JsonSerialize(using = CustomDateSerializer.class)
    protected Long opTime;
    @ApiModelProperty(value = "状态")
    protected Integer status;
    @ApiModelProperty(value = "类型")
    protected Integer type;
    @ApiModelProperty(value = "额外字段")
    protected String attribute;
    @ApiModelProperty(value = "创建时间")
    @JsonSerialize(using = CustomDateSerializer.class)
    protected Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOpTime() {
        return opTime;
    }

    public void setOpTime(Long opTime) {
        this.opTime = opTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
