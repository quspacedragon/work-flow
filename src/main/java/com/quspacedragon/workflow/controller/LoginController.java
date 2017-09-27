package com.quspacedragon.workflow.controller;

import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.vo.DictVo;
import com.quspacedragon.workflow.vo.EnterpriseVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Title: LoginController
 * <p>
 * Description: 登录
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/27
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping("/login")
    @ResponseBody
    @ApiOperation(value = "登录", httpMethod = "GET", response = EnterpriseVo.class, notes = "登录")
    public Result<DictVo> login(@ApiParam(name = "loginName", value = "登录名") String loginName,
                                @ApiParam(name = "pwd", value = "密码") String pwd) {
        EnterpriseVo dict = new EnterpriseVo();

        return ApiResultUtils.successResult(dict);
    }

    @GetMapping("/loginOut")
    @ResponseBody
    @ApiOperation(value = "退出登录", httpMethod = "GET", response = Boolean.class, notes = "退出登录")
    public Result<Boolean> loginOut() {
        EnterpriseVo dict = new EnterpriseVo();

        return ApiResultUtils.successResult(dict);
    }
}
