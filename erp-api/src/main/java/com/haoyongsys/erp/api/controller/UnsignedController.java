package com.haoyongsys.erp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haoyongsys.erp.common.pojo.R2;

import cn.aaron911.idempotent.core.IdempotentCoreImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 该方法下均无须授权即可访问
 * @ClassName: UnsignedController
 * @author Aaron
 * @date 2020年9月1日 下午5:08:48
 */
@RestController
@RequestMapping("/api/unsigned")
@Api(tags = "无须授权通用方法")
public class UnsignedController {

	@Autowired
	private IdempotentCoreImpl idempotentCore;
	
	@GetMapping(value = "/unique")
	@ApiOperation("获取幂等性校验码")
	public R2<String> unique() {
		String createToken = idempotentCore.createToken();
		return R2.OK(createToken);
	}
	
}
