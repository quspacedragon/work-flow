package com.quspacedragon.workflow.controller;

import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.common.UserHelper;
import com.quspacedragon.workflow.entity.Enterprise;
import com.quspacedragon.workflow.entity.Token;
import com.quspacedragon.workflow.service.IEnterpriseService;
import com.quspacedragon.workflow.service.ITokenService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.util.ConverUtils;
import com.quspacedragon.workflow.util.UUIDUtils;
import com.quspacedragon.workflow.vo.DictVo;
import com.quspacedragon.workflow.vo.EnterpriseVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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
    @Resource
    private IEnterpriseService enterpriseService;
    @Resource
    private ITokenService tokenService;

    @GetMapping("/login")
    @ResponseBody
    @ApiOperation(value = "登录", httpMethod = "GET", response = EnterpriseVo.class, notes = "登录")
    public Result<DictVo> login(@ApiParam(name = "loginName", value = "登录名") String loginName,
                                @ApiParam(name = "pwd", value = "密码") String pwd) {
        Enterprise enterprise = enterpriseService.findByUsernameAndPwd(loginName, pwd);
        if (enterprise == null) {
            return ApiResultUtils.failResult(HttpStatus.BAD_REQUEST.ordinal(), "密码错误");
        }
        String uuid = UUIDUtils.uuid();
        Token token = tokenService.findByEnterpriseId(enterprise.getId());
        if (token == null) {
            token = new Token();
        }
        long timeMillis = System.currentTimeMillis();
        token.setRequestTime(timeMillis);
        token.setExpiredTime(timeMillis + UserHelper.EXPIRE_TIME);
        token.setToken(uuid);
        token.setEnterpriseId(enterprise.getId());
        tokenService.insertOrUpdate(token);
        EnterpriseVo conver = (EnterpriseVo) ConverUtils.conver(enterprise, EnterpriseVo.class);
        conver.setToken(uuid);
        UserHelper.login(conver);
        return ApiResultUtils.successResult(conver);
    }

    @GetMapping("/loginOut")
    @ResponseBody
    @ApiOperation(value = "退出登录", httpMethod = "GET", response = Boolean.class, notes = "退出登录")
    public Result<Boolean> loginOut() {
        EnterpriseVo user = UserHelper.getUser();
        Token token = tokenService.findByEnterpriseId(user.getId());
        tokenService.deleteById(token.getId());
        UserHelper.loginOut();
        return ApiResultUtils.successResult(true);
    }
}
