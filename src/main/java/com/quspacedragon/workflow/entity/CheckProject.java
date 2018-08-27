package com.quspacedragon.workflow.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.quspacedragon.workflow.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author quspacedragon
 * @since 2018-08-27
 */
@ApiModel("巡检项目")
@TableName("check_project")
public class CheckProject extends BaseEntity<CheckProject> {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("巡检名称")
    private String name;
    @ApiModelProperty("循环类型")
    @TableField("cycle_type")
    private Integer cycleType;
    @ApiModelProperty("循环间隔时间")
    @TableField("cycle_value")
    private Integer cycleValue;
    @ApiModelProperty("编码")
    private String code;
    @ApiModelProperty("描述")
    private String memo;
    @ApiModelProperty("检查内容")
    @TableField("check_content")
    private String checkContent;
    @ApiModelProperty("检查需要")
    @TableField("check_require")
    private String checkRequire;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCycleType() {
        return cycleType;
    }

    public void setCycleType(Integer cycleType) {
        this.cycleType = cycleType;
    }

    public Integer getCycleValue() {
        return cycleValue;
    }

    public void setCycleValue(Integer cycleValue) {
        this.cycleValue = cycleValue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCheckContent() {
        return checkContent;
    }

    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent;
    }

    public String getCheckRequire() {
        return checkRequire;
    }

    public void setCheckRequire(String checkRequire) {
        this.checkRequire = checkRequire;
    }

    public static final String NAME = "name";

    public static final String CYCLE_TYPE = "cycle_type";

    public static final String CYCLE_VALUE = "cycle_value";

    public static final String CODE = "code";

    public static final String MEMO = "memo";

    public static final String CHECK_CONTENT = "check_content";

    public static final String CHECK_REQUIRE = "check_require";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CheckProject{" +
                "name=" + name +
                ", cycleType=" + cycleType +
                ", cycleValue=" + cycleValue +
                ", code=" + code +
                ", memo=" + memo +
                ", checkContent=" + checkContent +
                ", checkRequire=" + checkRequire +
                "}";
    }
}
