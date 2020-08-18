package com.haoyongsys.erp.api.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.DispatcherType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.haoyongsys.erp.common.util.xss.XssFilter;

import cn.hutool.core.util.StrUtil;



/**
 * Filter配置
 * 
 * xss跨站脚本攻击
 *
 */
@Configuration
public class FilterConfig {
    @Value("${xss.enabled:true}")
    private String enabled;

    @Value("${xss.excludes:null}")
    private String excludes;

    @Value("${xss.urlPatterns:/*}")
    private String urlPatterns;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        //添加过滤路径
        registration.addUrlPatterns(StrUtil.split(urlPatterns, ","));
        registration.setName("xssFilter");
        registration.setOrder(Integer.MAX_VALUE);
        //设置初始化参数
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("excludes", excludes);
        initParameters.put("enabled", enabled);
        registration.setInitParameters(initParameters);
        return registration;
    }
}









//@Configuration
//public class FilterConfig {
//
//    @Bean
//    public FilterRegistrationBean<XssFilter> xssFilterRegistration() {
//        FilterRegistrationBean<XssFilter> registration = new FilterRegistrationBean<XssFilter>();
//        registration.setDispatcherTypes(DispatcherType.REQUEST);
//        registration.setFilter(new XssFilter());
//        registration.addUrlPatterns("/*");
//        registration.setName("xssFilter");
//        registration.setOrder(Integer.MAX_VALUE);
//        return registration;
//    }
//}
