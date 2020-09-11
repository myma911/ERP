package com.haoyongsys.erp.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haoyongsys.erp.api.service.UserService;
import com.haoyongsys.erp.common.pojo.R2;
import com.haoyongsys.erp.common.pojo.entity.user.ARightUser;
import com.haoyongsys.erp.common.service.user.IARightUserService;

import cn.aaron911.encrypt.api.annotation.Encrypt;
import cn.aaron911.idempotent.annotation.ApiIdempotent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user")
@Api(tags = "用户")
public class UserController {

	@Autowired
	private IARightUserService userService;
	
	@Autowired
	private UserService userService2;
	

	@Encrypt
	@GetMapping(value = "/all")
	@ApiOperation("获取所有用户")
	public R2<List<ARightUser>> getAllUser() {
		List<ARightUser> list = userService.list();
		list = list.subList(0, 20);
		return R2.OK(list);
	}
	
	
	@GetMapping(value = "/get/{userid}")
	@ApiOperation("获取某用户信息")
	public R2<ARightUser> getUserById(@PathVariable String userid) {
		
		ARightUser user = userService.getById(userid);
		return R2.OK(user);
	}
	
	
	
	
	@GetMapping(value = "/get/test")
	@ApiOperation("获取某用户信息")
	@ApiIdempotent
	public R2<String> getUserById() {
		
		String a =  userService2.xx();
		return R2.OK(a);
	}
	

}
