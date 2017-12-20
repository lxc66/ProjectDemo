package com.jzsoft.platform.core.shiro;

import javax.servlet.ServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.jzsoft.platform.core.shiro.helper.LoginFailureHelper;

public class CustomFormAuthenticationFilter extends FormAuthenticationFilter{
	public static final String SHIRO_LOGIN_EXCEPTION = "shiroLoginException";
	public static final String LOGIN_ERROR = "loginError";
	@Override
	protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
//		super.setFailureAttribute(request, ae);
//		request.setAttribute(SHIRO_LOGIN_EXCEPTION, ae);
		request.setAttribute(LOGIN_ERROR, LoginFailureHelper.getLoginError(ae));
	}

}
