package com.haoyongsys.erp.mobile.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haoyongsys.erp.common.pojo.R;
import com.haoyongsys.erp.common.pojo.form.LoginForm;
import com.haoyongsys.erp.common.service.token.TokenService;
import com.haoyongsys.erp.common.util.annotation.Login;
import com.haoyongsys.erp.common.util.validator.ValidatorUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 登录接口
 *
 */
@RestController
@RequestMapping("/api")
@Api(tags="登录接口")
public class ApiLoginController {
   
    @Autowired
    private TokenService tokenService;


    @PostMapping("login")
    @ApiOperation("登录")
    public R login(@RequestBody LoginForm form){
        //表单校验
        ValidatorUtils.validateEntity(form);


        return R.OK();
    }

    @Login
    @PostMapping("logout")
    @ApiOperation("退出")
    public R logout(@ApiIgnore @RequestAttribute("userId") String userId){
        tokenService.expireToken(userId);
        return R.OK();
    }

}
