package com.quspacedragon.workflow.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quspacedragon.workflow.common.LoginUserTypeEnum;
import com.quspacedragon.workflow.common.UserHelper;
import com.quspacedragon.workflow.entity.Customer;
import com.quspacedragon.workflow.entity.Enterprise;
import com.quspacedragon.workflow.entity.Token;
import com.quspacedragon.workflow.service.ICustomerService;
import com.quspacedragon.workflow.service.IEnterpriseService;
import com.quspacedragon.workflow.service.ITokenService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.util.ConverUtils;
import com.quspacedragon.workflow.vo.CustomerVo;
import com.quspacedragon.workflow.vo.EnterpriseVo;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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


    static {
        filterSet.add("/login");
        filterSet.add("/swagger-ui.html");
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
        ITokenService tokenService = (ITokenService) ac1
                .getBean("tokenService");
        IEnterpriseService enterpriseService = (IEnterpriseService) ac1
                .getBean("enterpriseService");
        ICustomerService customerService = (ICustomerService) ac1
                .getBean("customerService");

        Long userId = null;
        String userToken = null;
        Integer type;
        try {
            String userIdParam = request.getParameter("userId");
            userToken = request.getParameter("token");
            if (requestURI.startsWith("/app")) {
                type = LoginUserTypeEnum.CUSTOMER.getType();
            } else {
                String typeParam = request.getParameter("type");
                type = Integer.parseInt(typeParam);
            }
            userId = Long.parseLong(userIdParam);
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
            if (type == null) {
                print(response,
                        ApiResultUtils.failResult(HttpStatus.UNAUTHORIZED.ordinal(), "type参数缺失"));
                return false;
            }

            Token validToken = tokenService.findValidToken(userId, userToken, type);
            if (validToken == null) {
                print(response,
                        ApiResultUtils.failResult(HttpStatus.UNAUTHORIZED.ordinal(), "请重新登录"));
                return false;
            }
        } catch (Exception e) {
            log.error("error", e);
            print(response,
                    ApiResultUtils.failResult(HttpStatus.BAD_REQUEST.ordinal(), "参数错误"));
            return false;
        }
        if (type == LoginUserTypeEnum.CUSTOMER.getType()) {
            Customer customer = customerService.selectById(userId);
            CustomerVo conver = (CustomerVo) ConverUtils.conver(customer, CustomerVo.class);
            conver.setToken(userToken);
            UserHelper.appLogin(conver);
        } else {
            Enterprise enterprise = enterpriseService.selectById(userId);
            EnterpriseVo conver = (EnterpriseVo) ConverUtils.conver(enterprise, EnterpriseVo.class);
            conver.setToken(userToken);
            UserHelper.login(conver);
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