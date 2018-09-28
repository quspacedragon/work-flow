package com.quspacedragon.workflow.interceptor;

import com.alibaba.common.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quspacedragon.workflow.annoation.LoginIntercept;
import com.quspacedragon.workflow.common.LoginHelper;
import com.quspacedragon.workflow.entity.Token;
import com.quspacedragon.workflow.enums.LoginUserTypeEnum;
import com.quspacedragon.workflow.enums.ResultCodeEnum;
import com.quspacedragon.workflow.service.IAdminService;
import com.quspacedragon.workflow.service.ITokenService;
import com.quspacedragon.workflow.service.IUserService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

@Component(value = "loginInterceptor")
@Slf4j
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
        if (!requestURI.startsWith("/common") && !requestURI.startsWith("/manager") && !requestURI.startsWith("/back") && !requestURI.startsWith("/app")) {
            return true;
        }


        //方法级SessionOption 高于class层次
        HandlerMethod hm = (HandlerMethod) handler;
        LoginIntercept loginIntercept = hm.getMethodAnnotation(LoginIntercept.class);

        if (null == loginIntercept) {
            loginIntercept = hm.getBean().getClass().getAnnotation(LoginIntercept.class);
        }

        if (null == loginIntercept || !loginIntercept.value()) {
            return true;
        }


        ApplicationContext ac1 = WebApplicationContextUtils
                .getRequiredWebApplicationContext(request.getSession()
                        .getServletContext());

        String userToken = null;
        Integer type;
        try {
            userToken = request.getParameter("token");
            if (requestURI.startsWith("/manager")) {
                type = LoginUserTypeEnum.STAFF.getType();
            } else if (requestURI.startsWith("/back")) {
                type = LoginUserTypeEnum.ADMIN.getType();
            } else if (requestURI.startsWith("/app")) {
                type = LoginUserTypeEnum.USER.getType();
            } else {
                return true;
            }
            request.setAttribute(LoginHelper.LOGIN_TYPE, type);
            request.setAttribute(LoginHelper.TOKEN, userToken);

            if (userToken == null) {
                print(response,
                        ApiResultUtils.failResult(HttpStatus.UNAUTHORIZED.ordinal(), "userToken参数缺失"));
                return false;
            }

            Token token = tokenService.findToken(userToken, type);
            if (token == null) {
                print(response,
                        ApiResultUtils.failResult(ResultCodeEnum.NOT_LOGIN.getCode(), ResultCodeEnum.NOT_LOGIN.getName()));
            }

            boolean islogin = loginHelper.isTokenlogin(token.getUserId(), userToken, type);
            if (!islogin) {
                print(response,
                        ApiResultUtils.failResult(ResultCodeEnum.NOT_LOGIN.getCode(), ResultCodeEnum.NOT_LOGIN.getName()));
            }


            return islogin;
        } catch (Exception e) {
            log.error("错误", e);
            print(response,
                    ApiResultUtils.failResult(HttpStatus.BAD_REQUEST.ordinal(), "参数错误:" + e.getMessage()));
            return false;
        }
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