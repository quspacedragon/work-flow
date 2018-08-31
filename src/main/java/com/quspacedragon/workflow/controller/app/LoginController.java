package com.quspacedragon.workflow.controller.app;

import com.google.common.base.Objects;
import com.quspacedragon.workflow.annoation.LoginIntercept;
import com.quspacedragon.workflow.common.LoginHelper;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.Token;
import com.quspacedragon.workflow.entity.User;
import com.quspacedragon.workflow.enums.LoginUserTypeEnum;
import com.quspacedragon.workflow.enums.SmsTypeEnum;
import com.quspacedragon.workflow.service.ISmsRecordService;
import com.quspacedragon.workflow.service.ITokenService;
import com.quspacedragon.workflow.service.IUserService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.util.MD5Util;
import io.swagger.annotations.*;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.ss.formula.functions.Code;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Title: LoginController
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/7/25
 */
@Api("app-用户端登录")
@Controller("appLoginController")
@RequestMapping("/user")
public class LoginController {

    @Resource
    IUserService userService;
    @Resource
    ITokenService tokenService;
    @Resource
    LoginHelper loginHelper;
    @Resource
    ISmsRecordService smsRecordService;


    @ApiOperation("登录")

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public Result login(
            @ApiParam(name = "phone", value = "手机号码", required = true) @RequestParam String phone,
            @ApiParam(name = "password", value = "密码", required = true) @RequestParam String password
    ) {
        User user = userService.findByPhone(phone);
        if (user == null) {
            return ApiResultUtils.failResult("用户不存在");
        }
        String passWord = user.getPassWord();
        if (!MD5Util.string2MD5(password).equals(passWord)) {
            return ApiResultUtils.failResult("密码错误");
        }
        String userToken = loginHelper.getUUID(user.getId(), LoginUserTypeEnum.USER.getType());
        user.setToken(userToken);
        Token token = tokenService.findValidToken(user.getId(), LoginUserTypeEnum.USER.getType());
        if (token == null) {
            token = new Token();
            token.setUserId(user.getId());
        }
        token.setToken(userToken);
        Date requestTime = new Date();
        token.setRequestTime(requestTime);
        token.setExpiredTime(DateUtils.addDays(requestTime, 7));
        tokenService.insertOrUpdate(token);
        return ApiResultUtils.successResult(user);
    }


    @ApiOperation(value = "找回密码", response = Boolean.class)
    @RequestMapping(value = "/findPwd", method = RequestMethod.GET)
    @ResponseBody
    public Result findPwd(
            @ApiParam(name = "phone", value = "手机号码", required = true) @RequestParam String phone,
            @ApiParam(name = "code", value = "验证码", required = true) @RequestParam String code,
            @ApiParam(name = "password", value = "code", required = true) @RequestParam String password,
            @ApiParam(name = "confirmPassword", value = "确认密码", required = true) @RequestParam String confirmPassword
    ) {
        if (!Objects.equal(password, confirmPassword)) {
            return ApiResultUtils.failResult("两次输入的密码不一致");
        }
        User user = userService.findByPhone(phone);
        if (user == null) {
            return ApiResultUtils.failResult("用户不存在");
        }
        Boolean valiade = smsRecordService.valiade(phone, SmsTypeEnum.app_find_pwd.getType(), code);
        if (!valiade) {
            return ApiResultUtils.failResult("验证码错误");
        }
        user.setPassWord(MD5Util.string2MD5(password));
        boolean flag = userService.updateById(user);
        return ApiResultUtils.successResult(flag);
    }

    @ApiOperation(value = "登出", response = Boolean.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "手机号码", value = "phone", required = true, dataType = "String"),
            @ApiImplicitParam(name = "密码", value = "password", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    @ResponseBody
    @LoginIntercept
    public Result loginOut(@RequestParam String token,
                           HttpServletRequest httpServletRequest
    ) {
        Integer loginUserId = loginHelper.getLoginUserId(token, httpServletRequest);
        Boolean aBoolean = loginHelper.loginOut(loginUserId, LoginUserTypeEnum.USER.getType());

        return ApiResultUtils.successResult(aBoolean);
    }


}
