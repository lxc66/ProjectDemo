package com.jzsoft.platform.module.userauth.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jzsoft.platform.core.dao.BaseDAO;
import com.jzsoft.platform.module.userauth.model.Role;

@Repository
public class RoleDAO extends BaseDAO<Role, String>{
	
	public int getMaxOrderNumWithType(String type){
		return (Integer) this.get("getMaxOrderNumWithType",type);
	}
	
	public List<Role> getListWithType(String type){
		return  this.getList("getListWithType",type);
	}
	
	public void updateEnableFlag(String id,String enableFlag){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("enableFlag", enableFlag);
		this.update("updateEnableFlag", params);
	}
	
	public Role getWithCodeAndExclude(String code , String excludeCode) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("code", code);
		if(StringUtils.isNotBlank(excludeCode)){
			params.put("excludeCode", excludeCode);
		}
		return this.get("getWithCodeAndExclude", params);
	}
	
	public Role getWithNameAndExclude(String name , String excludeName) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("name", name);
		if(StringUtils.isNotBlank(excludeName)){
			params.put("excludeName", excludeName);
		}
		return this.get("getWithNameAndExclude", params);
	}
	
	public void sort(String[] ids){
		for(int i=0 ; i<ids.length ; i++){
//			this.batchExecute("update Position p set p.num=? where p.id=?",(i+1),ids[i]);
		}
	}
	
	public List<Role> getUserRolesWithUserIdAndDistinct(String userId){
//		String hql = "select distinct up.position from UserPosition up where up.user.id=?";
		return null;
	}
	
	
}
