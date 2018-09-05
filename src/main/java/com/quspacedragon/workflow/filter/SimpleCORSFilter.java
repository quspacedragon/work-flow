package com.quspacedragon.workflow.filter;

import org.apache.http.HttpStatus;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*", filterName = "simpleCORSFilter")
public class SimpleCORSFilter implements Filter {

    public void destroy() {

    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) arg1;
        HttpServletRequest request = (HttpServletRequest) arg0;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Max-Age", "3600");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token");
        response.setHeader("Access-Control-Expose-Headers", "*");


        if (request.getMethod().equals("OPTIONS")) {

            response.setStatus(HttpStatus.SC_OK);

            // hresp.setContentLength(0);

            response.getWriter().write("OPTIONS returns OK");

            return;
        }

        arg2.doFilter(arg0, arg1);

    }

    public void init(FilterConfig arg0) throws ServletException {
    }


}
