package com.haoyongsys.erp.api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.haoyongsys.erp.api.interceptor.AliPayInterceptor;
import com.haoyongsys.erp.api.interceptor.WxPayInterceptor;
import com.haoyongsys.erp.common.util.interceptor.AuthorizationInterceptor;
import com.haoyongsys.erp.common.util.interceptor.BuronInterceptor;
import com.haoyongsys.erp.common.util.interceptor.TraceIDInterceptor;
import com.haoyongsys.erp.common.util.interceptor.UserAgentInterceptor;
import com.haoyongsys.erp.common.util.resolver.LoginUserHandlerMethodArgumentResolver;

import cn.aaron911.idempotent.interceptor.IdempotentInterceptor;

/**
 * MVC配置
 * 
 * 登录鉴权及用户参数自动注入
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
    /**
     * 日志链路追踪traceID
     */
    @Autowired
    private TraceIDInterceptor traceIDInterceptor;
    
   /**
     * 用户信息解析
     */
    @Autowired
    private UserAgentInterceptor userAgentInterceptor;
    
    /**
     * 安全访问拦截控制(接口流量控制)
     */
    @Autowired
    private BuronInterceptor buronInterceptor;
	
	/**
	 *  身份认证
	 */
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;
    
    /**
     * 幂等性
     */
    @Autowired
    private IdempotentInterceptor idempotentInterceptor;
    
    /**
     * 方法参数用户对象数据自动注入
     */
    @Autowired
    private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;
    
    
    
    //private List<String> patterns = CollUtil.newArrayList("/");
    
    

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	// 日志链路追踪traceID
    	registry.addInterceptor(traceIDInterceptor).addPathPatterns("/**");
    	
    	registry.addInterceptor(userAgentInterceptor).addPathPatterns("/**");
    	
    	registry.addInterceptor(buronInterceptor).addPathPatterns("/**");
    	
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**");
        
        registry.addInterceptor(idempotentInterceptor).addPathPatterns("/**");
        
        // 支付宝
        registry.addInterceptor(new AliPayInterceptor()).addPathPatterns("/aliPay/**");
        // 微信支付
        registry.addInterceptor(new WxPayInterceptor()).addPathPatterns("/wxPay/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
    }
    
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将所有/static/** 访问都映射到classpath:/static/ 目录下
        registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/");
        
        // 解决静态资源无法访问
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		
		// 解决swagger无法访问
		registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		
		// 解决swagger的js文件无法访问
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        
    }
}