package com.quspacedragon.workflow.interceptor;

import com.quspacedragon.workflow.common.UserHelper;
import com.quspacedragon.workflow.entity.Enterprise;
import com.quspacedragon.workflow.entity.Token;
import com.quspacedragon.workflow.service.IEnterpriseService;
import com.quspacedragon.workflow.service.impl.TokenService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

@Component(value = "loginInterceptor")
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static Logger log = Logger.getLogger(LoginInterceptor.class);
    private static Set<String> filterSet = new HashSet<String>();
    @Resource
    private TokenService tokenService;
    @Resource
    private IEnterpriseService enterpriseService;

    static {
        filterSet.add("/login");
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler
    ) throws Exception {


        Integer userId = null;
        String userToken = null;
        PrintWriter writer = response.getWriter();
        try {
            String userIdParam = request.getParameter("userId");
            userToken = request.getParameter("token");
            userId = Integer.parseInt(userIdParam);

            Token validToken = tokenService.findValidToken(userId, userToken);
            if (validToken == null) {
                writer.print(
                        ApiResultUtils.failResult(HttpStatus.UNAUTHORIZED.ordinal(), "请重新登录"));
                return false;
            }
        } catch (Exception e) {
            log.error("", e);
            writer.print(
                    ApiResultUtils.failResult(HttpStatus.BAD_REQUEST.ordinal(), "参数错误"));
            return false;
        } finally {
            writer.close();
        }
        Enterprise enterprise = enterpriseService.selectById(userId);
        request.setAttribute(UserHelper.USER_CONSTANT, enterprise);
        return true;
    }
}