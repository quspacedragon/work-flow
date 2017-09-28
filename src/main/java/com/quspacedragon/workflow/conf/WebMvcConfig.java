package com.quspacedragon.workflow.conf;


import com.quspacedragon.workflow.interceptor.LoginInterceptor;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.EventListener;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                //添加需要验证登录用户操作权限的请求
                .addPathPatterns("/*")
        //排除不需要验证登录用户操作权限的请求
                .excludePathPatterns("/swagger*");
    }

    /**
     * spring 监听器
     *
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean<EventListener> getDemoListener() {
        ServletListenerRegistrationBean<EventListener> registrationBean
                = new ServletListenerRegistrationBean<>();
        registrationBean.setListener(new RequestContextListener());
        registrationBean.setOrder(1);
        return registrationBean;
    }
/**
 * 过滤器
 */
//    @Bean
//    public FilterRegistrationBean getDemoFilter(){
//        DemoFilter demoFilter=new DemoFilter();
//        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
//        registrationBean.setFilter(demoFilter);
//        List<String> urlPatterns=new ArrayList<String>();
//        urlPatterns.add("/*");//拦截路径，可以添加多个
//        registrationBean.setUrlPatterns(urlPatterns);
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }
//    @Bean
//    public ServletRegistrationBean getDemoServlet(){
//        DemoServlet demoServlet=new DemoServlet();
//        ServletRegistrationBean registrationBean=new ServletRegistrationBean();
//        registrationBean.setServlet(demoServlet);
//        List<String> urlMappings=new ArrayList<String>();
//        urlMappings.add("/demoservlet");////访问，可以添加多个
//        registrationBean.setUrlMappings(urlMappings);
//        registrationBean.setLoadOnStartup(1);
//        return registrationBean;
//    }
}
