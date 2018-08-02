package com.quspacedragon.workflow.request;

import com.quspacedragon.workflow.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Title: UserMoveinRequest
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/7/29
 */
@Data
public class UserMoveinRequest {

    private User user;
    @ApiModelProperty("家庭成员")
    private List<User> familyList;

}
