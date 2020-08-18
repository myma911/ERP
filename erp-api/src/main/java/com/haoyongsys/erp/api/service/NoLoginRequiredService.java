package com.haoyongsys.erp.api.service;

import org.springframework.stereotype.Service;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.ICaptcha;

@Service
public class NoLoginRequiredService {

	public ICaptcha verifyCode() {
		//定义图形验证码的长、宽、验证码字符数、干扰元素个数
		CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
		return captcha;
	}
	

}
