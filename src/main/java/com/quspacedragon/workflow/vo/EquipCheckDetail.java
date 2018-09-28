package com.quspacedragon.workflow.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Title: EquipCheckDetail
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/9/9
 */
@ApiModel("设备巡检详情")
@Data
public class EquipCheckDetail {
    @ApiModelProperty("巡检名称")
    private String projectName;
    @ApiModelProperty("巡检时间")
    private Long checkTime;
    @ApiModelProperty("巡检方法")
    private String checkMethod;
    @ApiModelProperty("巡检要求")
    private String checkRequire;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("状态 TODO(0, \"待处理\"),\n" +
            "    TO_WORK(1, \"已开工\"),\n" +
            "    CHECKED(2, \"已完工\"),\n" +
            "    FAIL(-1, \"失败\"),;")
    private Integer status;
    @ApiModelProperty("详细描述")
    private String desc;
    @ApiModelProperty("图片")
    private List<String> imgList;
    @ApiModelProperty("巡检记录id")
    private Integer checkRecordId;
}
