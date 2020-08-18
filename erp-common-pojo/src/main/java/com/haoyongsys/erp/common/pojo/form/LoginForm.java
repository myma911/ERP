package com.haoyongsys.erp.common.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录表单
 *
 */
@Data
@ApiModel(value = "登录表单")
public class LoginForm {
	
    @ApiModelProperty(value = "手机号")
    @NotBlank(message="手机号不能为空")
    private String hxcrm_name;

    @ApiModelProperty(value = "密码")
    @NotBlank(message="密码不能为空")
    private String hxcrm_password;

    
    @ApiModelProperty(value = "密码")
    @NotBlank(message="密码不能为空")
    private String md5Pwd;
    
    
    @ApiModelProperty(value = "验证码")
    @NotBlank(message="验证码不能为空")
    private String vercode;
}
