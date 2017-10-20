package com.quspacedragon.workflow.controller.app;

import com.quspacedragon.workflow.common.LoginUserTypeEnum;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.common.UserHelper;
import com.quspacedragon.workflow.entity.Customer;
import com.quspacedragon.workflow.entity.Token;
import com.quspacedragon.workflow.service.ICustomerService;
import com.quspacedragon.workflow.service.IEnterpriseService;
import com.quspacedragon.workflow.service.ITokenService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.util.ConverUtils;
import com.quspacedragon.workflow.util.UUIDUtils;
import com.quspacedragon.workflow.vo.CustomerVo;
import com.quspacedragon.workflow.vo.DictVo;
import com.quspacedragon.workflow.vo.EnterpriseVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@Controller("appLoginController")
@RequestMapping("/app")
public class LoginController {
    @Resource
    private IEnterpriseService enterpriseService;
    @Resource
    private ITokenService tokenService;
    @Resource
    private ICustomerService customerService;

    @GetMapping("/login")
    @ResponseBody
    @ApiOperation(value = "登录", httpMethod = "GET", response = EnterpriseVo.class, notes = "登录")
    public Result<DictVo> login(@ApiParam(name = "loginName", value = "登录名") String loginName,
                                @ApiParam(name = "pwd", value = "密码") String pwd) {

        Customer enterprise = customerService.findByUsernameAndPwd(loginName, pwd);
        if (enterprise == null) {
            return ApiResultUtils.failResult(HttpStatus.BAD_REQUEST.ordinal(), "密码错误");
        }
        String uuid = UUIDUtils.uuid();
        Token token = tokenService.findByEnterpriseId(enterprise.getId(), LoginUserTypeEnum.CUSTOMER.getType());
        if (token == null) {
            token = new Token();
        }
        long timeMillis = System.currentTimeMillis();
        token.setRequestTime(timeMillis);
        token.setExpiredTime(timeMillis + UserHelper.EXPIRE_TIME);
        token.setToken(uuid);
        token.setType(LoginUserTypeEnum.CUSTOMER.getType());
        token.setEnterpriseId(enterprise.getId());
        tokenService.insertOrUpdate(token);
        CustomerVo conver = (CustomerVo) ConverUtils.conver(enterprise, CustomerVo.class);
        conver.setToken(uuid);
        UserHelper.appLogin(conver);
        return ApiResultUtils.successResult(conver);
    }

    @GetMapping("/loginOut")
    @ResponseBody
    @ApiOperation(value = "退出登录", httpMethod = "GET", response = Boolean.class, notes = "退出登录")
    public Result<Boolean> loginOut() {
        CustomerVo user = UserHelper.getAppUser();
        Token token = tokenService.findByEnterpriseId(user.getId(), LoginUserTypeEnum.CUSTOMER.getType());
        tokenService.deleteById(token.getId());
        UserHelper.loginOut();
        return ApiResultUtils.successResult(true);
    }

    @PutMapping("/deviceBind")
    @ResponseBody
    @ApiOperation(value = "绑定设备", httpMethod = "PUT", response = Boolean.class, notes = "设备绑定")
    public Result<Boolean> deviceBind(@ApiParam(name = "deviceToken", value = "设备token", required = true) @RequestParam(required = true) String deviceToken) {
        CustomerVo user = UserHelper.getAppUser();
        Customer customer = customerService.selectById(user.getId());
        customer.setDeviceToken(deviceToken);
        customerService.updateById(customer);
        return ApiResultUtils.successResult(true);
    }
}
