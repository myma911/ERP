package com.haoyongsys.erp.common.util.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.haoyongsys.erp.common.base.jwt.JwtUtil;
import com.haoyongsys.erp.common.pojo.StateCodeEnum;
import com.haoyongsys.erp.common.pojo.entity.token.TokenEntity;
import com.haoyongsys.erp.common.pojo.exception.AException;
import com.haoyongsys.erp.common.service.token.TokenService;
import com.haoyongsys.erp.common.util.annotation.Login;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * Token权限验证
 *
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
	
    @Autowired
    private TokenService tokenService;
    
    @Autowired
	private JwtUtil jwtUtil; 

    public static final String USER_KEY = "userId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Login annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        }else{
            return true;
        }

        if(annotation == null){
            return true;
        }

        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if(StrUtil.isBlank(token)){
            token = request.getParameter("token");
        }

        //token为空
        if(StrUtil.isBlank(token)){
            throw new AException(StateCodeEnum.TOKEN_CANNOT_EMPTY);
        }
        

        //硬核检查，查询token信息
        TokenEntity tokenEntity = tokenService.queryByToken(token);
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
            throw new AException(StateCodeEnum.LOGIN_ERROR);
        }
        
        // 校验token
        try {
        	jwtUtil.parseJWT(token);
		} catch (UnsupportedJwtException e) {
			throw new AException(e.getMessage());
		} catch (MalformedJwtException e) {
			throw new AException(e.getMessage());
		} catch (SignatureException e) {
			throw new AException(e.getMessage());
		} catch (ExpiredJwtException e) {
			throw new AException(StateCodeEnum.ExpiredJwtException);
		} catch (IllegalArgumentException e) {
			throw new AException(e.getMessage());
		} catch (Exception e) {
			throw new AException(e.getMessage());
		}
        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(USER_KEY, token);
        return true;
    }
}
