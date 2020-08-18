package com.haoyongsys.erp.api.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.haoyongsys.erp.common.pojo.R;
import com.haoyongsys.erp.common.pojo.StateCodeEnum;
import com.haoyongsys.erp.common.pojo.exception.AException;


/**
 * 异常处理器
 *
 */
@RestControllerAdvice
public class AExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(AException.class)
	public R<Object> handleAException(AException e){
		return new R<>(e.getCode(), e.getMsg());
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public R<Object> handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return new R<>(StateCodeEnum.ERROR.getCode(), "数据库中已存在该记录");
	}

	@ExceptionHandler(Exception.class)
	public R<Object> handleException(Exception e){
		logger.error(e.getMessage(), e);
		return new R<>(StateCodeEnum.ERROR.getCode(), e.getMessage());
	}
	
}
