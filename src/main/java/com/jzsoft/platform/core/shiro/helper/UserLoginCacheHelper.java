package com.jzsoft.platform.core.shiro.helper;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jzsoft.platform.module.user.model.SysUser;

@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class UserLoginCacheHelper{
	private static final String USER_SESSION_CACHE = "user";

	public static void cache(SysUser user){
		SecurityUtils.getSubject().getSession().setAttribute(USER_SESSION_CACHE, user);
	}

	public static SysUser get(){
		return (SysUser)SecurityUtils.getSubject().getSession().getAttribute(USER_SESSION_CACHE);
	}
	
	public static void clear(){
		SecurityUtils.getSubject().getSession().removeAttribute(USER_SESSION_CACHE);
	}

}


