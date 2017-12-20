package com.jzsoft.platform.module.userauth.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jzsoft.platform.core.dao.BaseDAO;
import com.jzsoft.platform.core.dao.ModelHelper;
import com.jzsoft.platform.module.user.model.SysUser;
import com.jzsoft.platform.module.userauth.model.Role;
import com.jzsoft.platform.module.userauth.model.UserRole;

@Repository
public class UserRoleDAO extends BaseDAO<UserRole, String>{
	
	public List<UserRole> getListWithUserId(String userId){
		return this.getList("getListWithUserId", userId);
	} 
	
	public List<UserRole> getListWithRoleId(String roleId){
		return this.getList("getListWithRoleId", roleId);
	} 
	
	public List<Role> getRolesWithUserId(String userId){
		return this.getList("getRolesWithUserId", userId);
	} 
	
	public List<SysUser> getUsersWithRoleId(String roleId){
		return this.getList("getUsersWithRoleId", roleId);
	} 
	
	public void deleteWithUserId(String userId){
		this.delete("deleteWithUserId", userId);
	}
	
	public void deleteWithRoleId(String roleId){
		this.delete("deleteWithRoleId", roleId);
	}
	
	public void saveUserRole(String userId , List<String> roleIds){
		List<UserRole> paramsList = new ArrayList<UserRole>();
		for(String roleId : roleIds){
			UserRole userRole = new UserRole(userId, roleId);
			ModelHelper.setPK(userRole);
			paramsList.add(userRole);
		}
		this.batchInsert("saveBatch", paramsList);
	}
	
	public void deleteUserRole(List<String> ids){
		this.batchDelete(SQL_ID_DELETE, ids);
	}
}
