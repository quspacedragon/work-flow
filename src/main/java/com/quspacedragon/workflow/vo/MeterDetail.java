package com.quspacedragon.workflow.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Title: MeterDetail
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/9/9
 */
@ApiModel("水电表详情")
@Data
public class MeterDetail implements Serializable {
    @ApiModelProperty("房间名字")
    private String roomName;
    @ApiModelProperty("上次读数")
    private Integer lastNum;
    private String desc;
    @ApiModelProperty("本次读数")
    private Integer nowNum;

}
