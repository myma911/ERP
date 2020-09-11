package com.haoyongsys.erp.api.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.plumelog.trace.aspect.AbstractAspect;

/**
 * 链路追踪全局打点
 * @ClassName: AspectConfig
 * @author Aaron
 * @date 2020年8月28日 上午10:31:46
 */
@Aspect
@Component
public class AopTrace extends AbstractAspect {
	

	@Around("within(com.haoyongsys.erp.api.controller.*)")
	public Object around(JoinPoint joinPoint) throws Throwable {
		return aroundExecute(joinPoint);
	}
	
	
	@Around("within(com.haoyongsys.erp.common.service.*.impl.*)")
	public Object around2(JoinPoint joinPoint) throws Throwable {
		return aroundExecute(joinPoint);
	}
	
	
	
//	@Around("within(com.haoyongsys.erp.common.*)) || within(com.haoyongsys.erp.api.*)) && execution(com.haoyongsys.erp.api.websocket.*))")
//	public Object around(JoinPoint joinPoint) throws Throwable {
//		return aroundExecute(joinPoint);
//	}
}