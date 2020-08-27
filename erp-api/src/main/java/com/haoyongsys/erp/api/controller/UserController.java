package com.haoyongsys.erp.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haoyongsys.erp.common.pojo.R;
import com.haoyongsys.erp.common.pojo.entity.user.ARightUser;
import com.haoyongsys.erp.common.service.user.IARightUserService;

import cn.shuibo.annotation.Encrypt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user")
@Api(tags = "用户")
public class UserController {

	@Autowired
	private IARightUserService userService;
	

	@Encrypt
	@GetMapping(value = "/all")
	@ApiOperation("获取所有用户")
	public R<?> getAllUser() {
		List<ARightUser> list = userService.list();
		list = list.subList(0, 20);
		return R.OK(list);
	}
	
	
	@GetMapping(value = "/get/{userid}")
	@ApiOperation("获取某用户信息")
	public R<?> getUserById(@PathVariable String userid) {
		
		ARightUser user = userService.getById(userid);
		return R.OK(user);
	}
	

}
