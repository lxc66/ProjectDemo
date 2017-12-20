package com.jzsoft.platform.module.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jzsoft.platform.core.dao.BaseDAO;
import com.jzsoft.platform.module.user.model.SysUser;

@Repository
public class SysUserDAO extends BaseDAO<SysUser, String>{
	public List<SysUser> getListWithUserType(String userType){
		String hql="from User where userDict=? and enableFlag=?";
		return null;
	}
	
	public SysUser getWithLoginNameAndExclude(String loginName , String excludeLoginName) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("loginName", loginName);
		if(StringUtils.isNotBlank(excludeLoginName)){
			params.put("excludeLoginName", excludeLoginName);
		}
		return this.get("getWithLoginNameAndExclude", params);
	}
	
	public void updateEnableFlag(String id,String enableFlag){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("enableFlag", enableFlag);
		this.update("updateEnableFlag", params);
	}
	
	public void updatePassword(String id,String password){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("password", password);
		this.update("updatePassword", params);
	}
}
