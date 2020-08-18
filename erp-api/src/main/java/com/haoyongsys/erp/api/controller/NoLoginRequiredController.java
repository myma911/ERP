package com.haoyongsys.erp.api.controller;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haoyongsys.erp.api.service.NoLoginRequiredService;

import cn.hutool.captcha.ICaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/NoLoginRequired")
@Api(tags="登录接口")
public class NoLoginRequiredController {
	
	
	@Autowired
	private NoLoginRequiredService noLoginRequiredService;
	
	
	
	
	@GetMapping(value="/verifyCode") 
    @ApiOperation("四位验证码")
    public void verifyCode(HttpServletResponse response) throws IOException {
		ICaptcha captcha = noLoginRequiredService.verifyCode();
		try(ServletOutputStream outputStream = response.getOutputStream()){
			captcha.write(outputStream);	
		}
	}
    		
	

}
