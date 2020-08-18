package com.haoyongsys.erp.common.util.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.haoyongsys.erp.common.pojo.entity.user.ARightUser;
import com.haoyongsys.erp.common.service.user.IARightUserService;
import com.haoyongsys.erp.common.util.annotation.LoginUser;
import com.haoyongsys.erp.common.util.interceptor.AuthorizationInterceptor;



/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 *
 */
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
    private IARightUserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(ARightUser.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        //获取用户ID
        Object object = request.getAttribute(AuthorizationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if(object == null){
            return null;
        }

        //获取用户信息
        ARightUser user = userService.getById((Long)object);

        return user;
    }
}
