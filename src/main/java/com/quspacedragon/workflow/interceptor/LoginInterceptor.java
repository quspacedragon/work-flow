package com.quspacedragon.workflow.interceptor;

import com.alibaba.common.convert.Convert;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quspacedragon.workflow.common.LoginHelper;
import com.quspacedragon.workflow.entity.Token;
import com.quspacedragon.workflow.enums.LoginUserTypeEnum;
import com.quspacedragon.workflow.service.IAdminService;
import com.quspacedragon.workflow.service.ITokenService;
import com.quspacedragon.workflow.service.IUserService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
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
    private static ObjectMapper objectMapper = new ObjectMapper();


    @Resource
    private ITokenService tokenService;
    @Resource
    private IUserService userService;
    @Resource
    private IAdminService adminService;
    @Resource
    private LoginHelper loginHelper;

    static {
        filterSet.add("/login");
        filterSet.add("/app/login");
        filterSet.add("/swagger-ui.html");
        filterSet.add("/v2/api-docs");
        filterSet.add("/configuration/ui");

    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler
    ) throws Exception {
        String requestURI = request.getRequestURI();
        if (filterSet.contains(requestURI)) return true;

        ApplicationContext ac1 = WebApplicationContextUtils
                .getRequiredWebApplicationContext(request.getSession()
                        .getServletContext());

        Integer userId = null;
        String userToken = null;
        Integer type;
        try {
            String userIdParam = request.getParameter("userId");
            userToken = request.getParameter("token");
            if (requestURI.startsWith("/manager")) {
                type = LoginUserTypeEnum.STAFF.getType();
            } else if (requestURI.startsWith("/back")) {
                type = LoginUserTypeEnum.ADMIN.getType();
            } else if (requestURI.startsWith("/user")) {
                type = LoginUserTypeEnum.ADMIN.getType();
            } else {
                return true;
            }
            request.setAttribute(LoginHelper.LOGIN_TYPE, type);
            userId = Convert.asInt(userIdParam);
            if (userToken == null) {
                print(response,
                        ApiResultUtils.failResult(HttpStatus.UNAUTHORIZED.ordinal(), "userToken参数缺失"));
                return false;
            }
            if (userId == null) {
                print(response,
                        ApiResultUtils.failResult(HttpStatus.UNAUTHORIZED.ordinal(), "userId参数缺失"));
                return false;
            }
            boolean islogin = loginHelper.islogin(userId, userToken, type);

            if (islogin) {
                return true;
            }
        } catch (Exception e) {
            log.error("错误", e);
            print(response,
                    ApiResultUtils.failResult(HttpStatus.BAD_REQUEST.ordinal(), "参数错误:" + e.getMessage()));
            return false;
        }
        return true;
    }


    public static void print(HttpServletResponse response, Object entity) {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.print(objectMapper.writeValueAsString(entity));
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            pw.close();
        }
    }
}