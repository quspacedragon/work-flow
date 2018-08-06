package com.quspacedragon.workflow.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.FieldFill;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Title: BaseEntity
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/12
 */
@ApiModel(value = "BaseEntity", discriminator = "baseEntity", subTypes = {Building.class})
public abstract class BaseEntity<T extends Model> extends Model<T> {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("主键id")
    protected Integer id;
    @TableField(value = "is_valid", fill = FieldFill.INSERT)
    @TableLogic
    protected Integer isValid;
    @TableField(value = "op_time", fill = FieldFill.INSERT_UPDATE)
    protected Long opTime;
    @TableField(value = "last_ver", fill = FieldFill.INSERT)
    @Version
    protected Integer lastVer;
    @TableField(fill = FieldFill.INSERT)
    protected Integer status;
    protected Integer type;
    protected String attribute;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected Long createTime;

    public static final String TYPE = "type";
    public static final String CREATE_TIME = "create_time";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Long getOpTime() {
        return opTime;
    }

    public void setOpTime(Long opTime) {
        this.opTime = opTime;
    }

    public Integer getLastVer() {
        return lastVer;
    }

    public void setLastVer(Integer lastVer) {
        this.lastVer = lastVer;
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
