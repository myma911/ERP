package com.haoyongsys.erp.common.util.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.haoyongsys.erp.common.base.holder.RequestHolder;
import com.haoyongsys.erp.common.pojo.R;
import com.haoyongsys.erp.common.pojo.StateCodeEnum;

import cn.aaron911.buron.BuronProcessor;
import cn.aaron911.buron.BuronResponse;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 安全访问拦截控制
 *
 */
@Slf4j
@Component
public class BuronInterceptor extends HandlerInterceptorAdapter {
	
	private static final int SUCCESS = 1;
	
	@Autowired
	private BuronProcessor buronProcessor;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		BuronResponse buronResponse = buronProcessor.process(request);
        if(buronResponse.getCode() == SUCCESS) {
            return true;
        }
        String errorMsg = String.format("第%s次被限制！", buronResponse.getLimitCount());
        log.warn(errorMsg);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONUtil.toJsonStr(new R(StateCodeEnum.LIMIT_ACCESS).put("errorMsg", errorMsg).put("buronResponse", JSONUtil.toJsonStr(buronResponse))));
        writer.flush();
        writer.close();
        return false;
	}
	
	
	private boolean isAjax(HttpServletRequest request) {
        if (null == request) {
            request = RequestHolder.getRequest();
        }
        if (null == request) {
            return false;
        }
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))
                || request.getParameter("ajax") != null;

    }
}
