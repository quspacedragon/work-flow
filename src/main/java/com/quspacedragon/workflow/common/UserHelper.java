package com.quspacedragon.workflow.common;

import com.quspacedragon.workflow.vo.CustomerVo;
import com.quspacedragon.workflow.vo.EnterpriseVo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Title: UserHelper
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/9/27
 */
public class UserHelper {
    public static final String USER_CONSTANT = "session_user";
    public static final String APP_USER_CONSTANT = "app_session_user";
    public static final Long EXPIRE_TIME = 7 * 24 * 60 * 60 * 60L;

    public static EnterpriseVo getUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        EnterpriseVo user = (EnterpriseVo) session.getAttribute(USER_CONSTANT);
        return user;
    }

    public static void login(EnterpriseVo enterpriseVo) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute(USER_CONSTANT, enterpriseVo);
    }

    public static void loginOut() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute(USER_CONSTANT);
    }

    public static void appLogin(CustomerVo enterpriseVo) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute(APP_USER_CONSTANT, enterpriseVo);
    }

    public static CustomerVo getAppUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        CustomerVo user = (CustomerVo) session.getAttribute(APP_USER_CONSTANT);
        return user;
    }
}
