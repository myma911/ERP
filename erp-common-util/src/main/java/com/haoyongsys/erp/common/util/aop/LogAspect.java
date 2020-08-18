package com.haoyongsys.erp.common.util.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import com.haoyongsys.erp.common.util.annotation.Log;


/**
 * 拦截注解实现
 * @author Aaron
 * @datetime 2019年9月26日 下午10:08:58
 */
@Aspect
@Component
public class LogAspect {
	
	@Pointcut("@annotation(com.haoyongsys.erp.common.util.annotation.Log)")
	public void pointcut() {
	}

	@Around("pointcut()")
	public void around(ProceedingJoinPoint point) {
		long beginTime = System.currentTimeMillis();
		try {
			// 执行方法
			point.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		// 保存日志
		saveLog(point, time);
	}

	private void saveLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Log logAnnotation = method.getAnnotation(Log.class);
		if (logAnnotation != null) {
			// 注解上的描述
			System.out.println(logAnnotation.value());
		}
		// 请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		System.out.println(className + "." + methodName + "()");
		// 请求的方法参数值
		Object[] args = joinPoint.getArgs();
		// 请求的方法参数名称
		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		String[] paramNames = u.getParameterNames(method);
		if (args != null && paramNames != null) {
			String params = "";
			for (int i = 0; i < args.length; i++) {
				params += "  " + paramNames[i] + ": " + args[i];
			}
		}
	}
}
