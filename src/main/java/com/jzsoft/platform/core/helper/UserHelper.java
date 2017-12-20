package com.jzsoft.platform.core.helper;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jzsoft.platform.core.shiro.helper.UserLoginCacheHelper;
import com.jzsoft.platform.core.spring.SpringContextHolder;
import com.jzsoft.platform.module.user.model.SysUser;
import com.jzsoft.platform.module.user.service.SysUserService;

/**
 * @Description : ()
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class UserHelper{
	public static final String SUPERADMIN_NAME = "administrator";
	public static final String ADMIN_NAME = "admin";
	public static String getCurrUserID() {
		SysUser user = getCurrUser();
		if(user == null){
			return null;
		}
		return user.getId();
	}
	
	/**
	 * 得到当前系统用户
	 */
	public static SysUser getCurrUser() {
//		String id = SpringMVCUtil.getRequest().getSession().getId();
//		Serializable id2 = SecurityUtils.getSubject().getSession().getId();
//		User user = (User) SpringMVCUtil.getRequest().getSession().getAttribute("user");
		try {
			SysUser user = UserLoginCacheHelper.get();
			if(user != null){
				return user;
			}
			Object principal = SecurityUtils.getSubject().getPrincipal();
			if (principal == null) {
				return null;
			}
			String username = (String)principal;
			SysUserService userService = SpringContextHolder.getBean(SysUserService.class);
			user = userService.getUserWithLoginName(username);
			UserLoginCacheHelper.cache(user);
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	
	
	/**
	 * 判断是否是超级管理员
	 */
	public static boolean isSuperAdmin() {
		//以前是用Authorize.ifAllGranted判断的，没写authorize，先用这个
		SysUser currUser = getCurrUser();
		if (currUser != null && SUPERADMIN_NAME.equals(currUser.getLoginName())) {
			return true;
		}
		return false;
	}

}


