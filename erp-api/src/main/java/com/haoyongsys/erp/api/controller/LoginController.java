package com.haoyongsys.erp.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haoyongsys.erp.api.service.LoginService;
import com.haoyongsys.erp.common.pojo.R;
import com.haoyongsys.erp.common.pojo.StateCodeEnum;
import com.haoyongsys.erp.common.pojo.entity.user.ARightUser;
import com.haoyongsys.erp.common.pojo.form.LoginForm;
import com.haoyongsys.erp.common.util.annotation.Login;
import com.haoyongsys.erp.common.util.annotation.LoginUser;
import com.haoyongsys.erp.common.util.validator.ValidatorUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 登录接口
 *
 */
@RestController
@RequestMapping("/api")
@Api(tags="登录接口")
public class LoginController {
	
	
	@Autowired
	private LoginService loginService;

    
    /**
     * 用户名密码登录入口
     * @MethodName: login
     * @Param: [request, map, response]
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: admin
     * @Date:
     **/
    @PostMapping(value="/crmLoginByUserNameAndPassWord") 
    @ApiOperation("登录")
    public R<?> login(
    		@RequestBody LoginForm form  		
    ){
    	ValidatorUtils.validateEntity(form);
    	
    	return loginService.login(form);
    }

    @Login
    @GetMapping("logout")
    @ApiOperation("退出")
    public R<Object> logout(@LoginUser ARightUser user){
    	boolean blackToken = loginService.blackToken(user.getUserId());
    	if (blackToken) {
    		return R.OK();
    	}
        return new R<>(StateCodeEnum.ERROR.getCode(), "失败");
    }

}
