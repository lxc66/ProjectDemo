package com.jzsoft.platform.core.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

public class CustomUserFilter extends UserFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		//此方法被调用，证明用户未登录或Session过期，可根据需要做相应处理
		if (isAjaxRequest(request)){
			WebUtils.toHttp(response).setHeader("sessionstatus", "timeout");// 在响应头设置session状态
		}else{
			//跳转到登录页
			saveRequestAndRedirectToLogin(request, response);
		}
        return false;
	}

	private boolean isAjaxRequest(ServletRequest request){
		HttpServletRequest req = WebUtils.toHttp(request);
		// 如果是ajax请求响应头会有，x-requested-with；
		return req.getHeader("x-requested-with") != null && req.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest");
	}
}
