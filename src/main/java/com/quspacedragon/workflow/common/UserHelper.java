package com.quspacedragon.workflow.common;

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
    public static final String USER_CONSTANT ="session_user";

    public static EnterpriseVo getUser(Integer userId) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        EnterpriseVo user = (EnterpriseVo) session.getAttribute(Integer.toString(userId));
        return user;
    }
}
