package com.jzsoft.platform.module.userauth.util;

import java.util.List;

import com.jzsoft.platform.core.spring.SpringContextHolder;
import com.jzsoft.platform.module.config.external.impl.ConfigServiceImpl;

public class UserAuthConstant {
	public static final String CONFIG_PRIVILEGE_HAVEPARENT = "privilegeHaveParent";//权限是否包含父级
	public static final String RESULT_PRIVILEGE_HAVEPARENT_YES = "1";//权限包含父级
	public static final String RESULT_PRIVILEGE_HAVEPARENT_NO = "0";//权限不含父级
	
	public static final String CONFIG_PRIVILEGE_HAVEURL = "privilegeHaveUrl";//权限是否包含URL
	public static final String RESULT_PRIVILEGE_HAVEURL_YES = "1";//权限包含URL
	public static final String RESULT_PRIVILEGE_HAVEURL_NO = "0";//权限不含URL
	
	
	public static final String CONFIG_PRIVILEGE_COMPLEXITY = "privilegeComplexity";//权限的粒度
	public static final String RESULT_PRIVILEGE_COMPLEXITY_SIMPLE = "privilegeComplexity";//权限的粒度简单
	public static final String RESULT_PRIVILEGE_COMPLEXITY_COMPLEX = "privilegeComplexity";//权限的粒度复杂
	
	public static boolean privilegeHaveParent(){
		List<String> configValues = getConfigValues(CONFIG_PRIVILEGE_HAVEPARENT);
		if(configValues!=null&&configValues.size()>0){
			return configValues.get(0).equals(RESULT_PRIVILEGE_HAVEPARENT_YES);
		}
		return true;
	}
	
	public static boolean privilegeHaveUrl(){
		List<String> configValues = getConfigValues(CONFIG_PRIVILEGE_HAVEURL);
		if(configValues!=null&&configValues.size()>0){
			return configValues.get(0).equals(RESULT_PRIVILEGE_HAVEURL_YES);
		}
		return true;
	}
	
	public static List<String> getConfigValues(String code){
		return SpringContextHolder.getBean(ConfigServiceImpl.class).getModuleConfigResultWithCode(code);
	}
}
