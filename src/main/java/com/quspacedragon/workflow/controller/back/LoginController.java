package com.quspacedragon.workflow.controller.back;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.quspacedragon.workflow.common.LoginHelper;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.*;
import com.quspacedragon.workflow.enums.LoginUserTypeEnum;
import com.quspacedragon.workflow.service.IMenuService;
import com.quspacedragon.workflow.service.IRoleMenuService;
import com.quspacedragon.workflow.service.ITokenService;
import com.quspacedragon.workflow.service.IUserService;
import com.quspacedragon.workflow.service.impl.RoleService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Title: LoginController
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/7/25
 */
@Api("后台登录")
@Controller("backLoginController")
@RequestMapping("/back")
public class LoginController {

    @Resource
    IUserService userService;
    @Resource
    ITokenService tokenService;
    @Resource
    LoginHelper loginHelper;
    @Resource
    RoleService roleService;
    @Resource
    IRoleMenuService roleMenuService;
    @Resource
    IMenuService menuService;

    @ApiOperation(value = "登录", response = User.class)
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public Result getAuthcode(
            @ApiParam(value = "手机号码", name = "phone", required = true)
            @RequestParam(value = "phone") String phone,
            @ApiParam(value = "密码", name = "password", required = true)
            @RequestParam(value = "password") String password
    ) {

        User user = userService.findByPhone(phone);
        if (user == null) {
            return ApiResultUtils.failResult("用户不存在");
        }
        String passWord = user.getPassWord();
        if (!MD5Util.string2MD5(password).equals(passWord)) {
            return ApiResultUtils.failResult("密码错误");
        }
        String userToken = loginHelper.getUUID(user.getId(), LoginUserTypeEnum.ADMIN.getType());
        user.setToken(userToken);
        Token token = tokenService.findValidToken(user.getId(), LoginUserTypeEnum.ADMIN.getType());
        if (token == null) {
            token = new Token();
            token.setUserId(user.getId());
        }
        token.setToken(userToken);
        Date requestTime = new Date();
        token.setRequestTime(requestTime);
        token.setExpiredTime(DateUtils.addDays(requestTime, 7));
        boolean flag = tokenService.insertOrUpdate(token);
        if (flag) {
            Role role = roleService.selectById(user.getRoleId());
            if (role != null) {
                EntityWrapper<RoleMenu> roleMenuEntityWrapper = new EntityWrapper<>();
                roleMenuEntityWrapper.where(RoleMenu.ROLE_ID, role.getId());
                List<RoleMenu> roleMenus = roleMenuService.selectList(roleMenuEntityWrapper);
                if (CollectionUtils.isNotEmpty(roleMenus)) {
                    List<Integer> menuIdList = roleMenus.stream().map(r -> r.getMenuId()).collect(Collectors.toList());
                    List<Menu> menus = menuService.selectBatchIds(menuIdList);
                    user.setMenus(menus);
                }
            }
        } else {
            return ApiResultUtils.failResult("登录失败");
        }
        return ApiResultUtils.successResult(user);
    }
}
