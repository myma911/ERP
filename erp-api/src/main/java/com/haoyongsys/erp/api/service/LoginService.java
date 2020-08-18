package com.haoyongsys.erp.api.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haoyongsys.erp.api.constant.ProjectGlobalConfig;
import com.haoyongsys.erp.common.pojo.R;
import com.haoyongsys.erp.common.pojo.StateCodeEnum;
import com.haoyongsys.erp.common.pojo.entity.token.TokenEntity;
import com.haoyongsys.erp.common.pojo.entity.user.ARightUser;
import com.haoyongsys.erp.common.pojo.form.LoginForm;
import com.haoyongsys.erp.common.service.token.TokenService;
import com.haoyongsys.erp.common.service.user.IARightUserService;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginService {

	@Autowired
	private IARightUserService rightUserService;

	@Autowired
	private TokenService tokenService;

	public R login(LoginForm form) {
		 return login20200814(form);
	}

	private R login20200814(LoginForm form) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession(false);
		if (null == session) {
			return new R<>(StateCodeEnum.VERIFICATION_CODE_ERROR);
		}

		String validateCode = (String) session.getAttribute(ProjectGlobalConfig.validateCode);

		if (StrUtil.isBlank(validateCode)) {
			return new R<>(StateCodeEnum.VERIFICATION_CODE_ERROR);
		}

		if (!validateCode.toLowerCase().equals(form.getVercode().toLowerCase())) {
			return new R<>(StateCodeEnum.VERIFICATION_CODE_ERROR);
		}
		QueryWrapper<ARightUser> queryWrapper = new QueryWrapper<ARightUser>().eq("user_iid", form.getHxcrm_name())
				.eq("user_password", form.getMd5Pwd()).eq("using_flag", "Y");
		ARightUser user = rightUserService.getOne(queryWrapper);
		if (user == null) {
			return new R<>(StateCodeEnum.LOGIN_ERROR);
		}

		// 获取登录token
		TokenEntity tokenEntity = tokenService.createToken(user.getUserId());

		return R.OK().put("token", tokenEntity.getToken());
	}

	public boolean blackToken(String userId) {
		return tokenService.expireToken(userId);
	}

}
