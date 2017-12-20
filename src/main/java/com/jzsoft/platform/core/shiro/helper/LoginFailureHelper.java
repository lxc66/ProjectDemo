package com.jzsoft.platform.core.shiro.helper;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;

import com.jzsoft.platform.core.shiro.exception.IncompleteCredentialsException;

public class LoginFailureHelper {
	public static String getLoginError(AuthenticationException ae){
		if(ae instanceof IncompleteCredentialsException || ae.getCause() instanceof NullPointerException){
			return "用户名和密码不能为空！";
		}
		if(ae instanceof UnknownAccountException || ae instanceof IncorrectCredentialsException){
			return "密码错误！";
		}
		
		if(ae instanceof ExcessiveAttemptsException){
			return "用户登录过度尝试！";
		}
		return ae.getMessage();
	}
}
