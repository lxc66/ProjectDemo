package com.jzsoft.platform.module.userauth.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jzsoft.platform.core.dao.BaseDAO;
import com.jzsoft.platform.module.userauth.model.Privilege;

@Repository
public class PrivilegeDAO extends BaseDAO<Privilege, String>{
	
	public int getMaxOrderNumWithModule(String moduleId){
		return (Integer) this.get("getMaxOrderNumWithModule",moduleId);
	}
	
	public List<Privilege> getListWithExcludeId(String excludeId){
		return this.getList("getListWithExcludeId", excludeId);
	}
	
	public List<Privilege> getListWithModuleIdAndExcludeId(String moduleId , String excludeId){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("moduleId", moduleId);
		params.put("excludeId", excludeId);
		return this.getList("getListWithModuleIdAndExcludeId", params);
	}
	
	public List<Privilege> getUserAllPrivileges(String userId){
		return this.getList("getUserAllPrivileges", userId);
	}
	
	public List<Privilege> getUserRolePrivileges(String userId){
		return this.getList("getUserAllPrivileges", userId);
	}
	
	public Privilege getWithCodeAndExclude(String code , String excludeCode) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("code", code);
		if(StringUtils.isNotBlank(excludeCode)){
			params.put("excludeCode", excludeCode);
		}
		return this.get("getWithCodeAndExclude", params);
	}
	
	public Privilege getWithNameAndExclude(String name , String excludeName) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("name", name);
		if(StringUtils.isNotBlank(excludeName)){
			params.put("excludeName", excludeName);
		}
		return this.get("getWithNameAndExclude", params);
	}
}
