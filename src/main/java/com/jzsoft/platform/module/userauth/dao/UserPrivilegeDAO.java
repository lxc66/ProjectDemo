package com.jzsoft.platform.module.userauth.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jzsoft.platform.core.dao.BaseDAO;
import com.jzsoft.platform.core.dao.ModelHelper;
import com.jzsoft.platform.module.user.model.SysUser;
import com.jzsoft.platform.module.userauth.model.Privilege;
import com.jzsoft.platform.module.userauth.model.UserPrivilege;

@Repository
public class UserPrivilegeDAO extends BaseDAO<UserPrivilege, String>{
	
	public List<UserPrivilege> getListWithUserId(String userId){
		return this.getList("getListWithUserId", userId);
	} 
	
	public List<UserPrivilege> getListWithPrivilegeId(String privilegeId){
		return this.getList("getListWithPrivilegeId", privilegeId);
	} 
	
	public List<Privilege> getPrivilegesWithUserId(String userId){
		return this.getList("getPrivilegesWithUserId", userId);
	} 
	
	public List<SysUser> getUsersWithPrivilegeId(String privilegeId){
		return this.getList("getUsersWithPrivilegeId", privilegeId);
	} 
	
	public void deleteWithUserId(String userId){
		this.delete("deleteWithUserId", userId);
	}
	
	public void deleteWithPrivilegeId(String privilegeId){
		this.delete("deleteWithPrivilegeId", privilegeId);
	}
	
	public void saveUserPrivilege(String userId , List<String> privilegeIds){
		List<UserPrivilege> paramsList = new ArrayList<UserPrivilege>();
		for(String privilegeId : privilegeIds){
			UserPrivilege userPrivilege = new UserPrivilege(userId, privilegeId);
			ModelHelper.setPK(userPrivilege);
			paramsList.add(userPrivilege);
		}
		this.batchInsert("saveBatch", paramsList);
	}
	
	public void deleteUserPrivilege(List<String> ids){
		this.batchDelete(SQL_ID_DELETE, ids);
	}
}
