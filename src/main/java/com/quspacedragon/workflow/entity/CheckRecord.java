package com.quspacedragon.workflow.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author quspacedragon
 * @since 2018-08-27
 */
@TableName("check_record")
public class CheckRecord extends BaseEntity<CheckRecord> {

    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    private Integer userId;
    /**
     * 设备实体id
     */
    @TableField("equip_entity_id")
    private Integer equipEntityId;
    /**
     * 巡检id
     */
    @TableField("check_project_id")
    private Integer checkProjectId;
    /**
     * 巡检时间
     */
    @TableField("check_time")
    private Long checkTime;
    /**
     * 巡检有效期开始时间
     */
    @TableField("valid_start_time")
    private Long validStartTime;
    /**
     * 巡检有效期结束时间
     */
    @TableField("valid_end_time")
    private Long validEndTime;

    @TableField("imgs")
    private String imgs;
    @TableField("memo")
    private String memo;

    @TableField(exist = false)
    @ApiModelProperty("图片列表")
    private List<String> imgList;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEquipEntityId() {
        return equipEntityId;
    }

    public void setEquipEntityId(Integer equipEntityId) {
        this.equipEntityId = equipEntityId;
    }

    public Integer getCheckProjectId() {
        return checkProjectId;
    }

    public void setCheckProjectId(Integer checkProjectId) {
        this.checkProjectId = checkProjectId;
    }

    public Long getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Long checkTime) {
        this.checkTime = checkTime;
    }

    public Long getValidStartTime() {
        return validStartTime;
    }

    public void setValidStartTime(Long validStartTime) {
        this.validStartTime = validStartTime;
    }

    public Long getValidEndTime() {
        return validEndTime;
    }

    public void setValidEndTime(Long validEndTime) {
        this.validEndTime = validEndTime;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<String> getImgList() {
        if (StringUtils.isNotEmpty(imgs)) {
            imgList = Lists.newArrayList(imgs.split(","));

        }
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public static final String USER_ID = "user_id";

    public static final String EQUIP_ENTITY_ID = "equip_entity_id";

    public static final String CHECK_PROJECT_ID = "check_project_id";

    public static final String CHECK_TIME = "check_time";

    public static final String VALID_START_TIME = "valid_start_time";

    public static final String VALID_END_TIME = "valid_end_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CheckRecord{" +
                "userId=" + userId +
                ", equipEntityId=" + equipEntityId +
                ", checkProjectId=" + checkProjectId +
                ", checkTime=" + checkTime +
                ", validStartTime=" + validStartTime +
                ", validEndTime=" + validEndTime +
                "}";
    }
}
