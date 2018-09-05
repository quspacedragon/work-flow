package com.quspacedragon.workflow.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Title: AppEquipVo
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/9/2
 */
@ApiModel
@Data
public class AppEquipVo {
    @ApiModelProperty("待维保设备数量")
    private Integer todoNum;
}
