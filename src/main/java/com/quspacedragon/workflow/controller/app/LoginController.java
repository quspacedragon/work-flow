package com.quspacedragon.workflow.controller.app;

import com.quspacedragon.workflow.common.LoginHelper;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.Token;
import com.quspacedragon.workflow.entity.User;
import com.quspacedragon.workflow.enums.LoginUserTypeEnum;
import com.quspacedragon.workflow.service.ITokenService;
import com.quspacedragon.workflow.service.IUserService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.time.DateUtils;
import org.apache.ibatis.annotations.Results;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
@Api("用户端登录")
@RequestMapping("/user")
public class LoginController {

    @Resource
    IUserService userService;
    @Resource
    ITokenService tokenService;
    @Resource
    LoginHelper loginHelper;

    @ApiOperation("登录")
    @RequestMapping("/login")
    @ResponseBody
    public Result getAuthcode(
            @ApiParam(value = "phone", name = "手机号码", required = true)
            @RequestAttribute String phone,
            @ApiParam(value = "password", name = "密码", required = true)
            @RequestAttribute String password
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
}
