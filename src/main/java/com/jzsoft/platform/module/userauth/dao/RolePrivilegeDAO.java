package com.jzsoft.platform.module.userauth.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jzsoft.platform.core.dao.BaseDAO;
import com.jzsoft.platform.core.dao.ModelHelper;
import com.jzsoft.platform.module.userauth.model.Privilege;
import com.jzsoft.platform.module.userauth.model.Role;
import com.jzsoft.platform.module.userauth.model.RolePrivilege;

@Repository
public class RolePrivilegeDAO extends BaseDAO<RolePrivilege, String>{
	public List<RolePrivilege> getListWithRoleId(String roleId){
		return this.getList("getListWithRoleId", roleId);
	} 
	
	public List<RolePrivilege> getListWithPrivilegeId(String privilegeId){
		return this.getList("getListWithPrivilegeId", privilegeId);
	} 
	
	public List<Privilege> getPrivilegeWithRoleId(String roleId){
		return this.getList("getPrivilegeWithRoleId", roleId);
	} 
	
	public List<Role> getRoleWithPrivilegeId(String privilegeId){
		return this.getList("getRoleWithPrivilegeId", privilegeId);
	} 
	
	public void deleteWithRoleId(String roleId){
		this.delete("deleteWithRoleId", roleId);
	}
	
	public void deleteWithPrivilegeId(String privilegeId){
		this.delete("deleteWithPrivilegeId", privilegeId);
	}
	
	public void saveRolePrivilege(String roleId , List<String> privilegeIds){
		List<RolePrivilege> paramsList = new ArrayList<RolePrivilege>();
		for(String privilegeId : privilegeIds){
			RolePrivilege rolePrivilege = new RolePrivilege(roleId, privilegeId);
			ModelHelper.setPK(rolePrivilege);
			paramsList.add(rolePrivilege);
		}
		this.batchInsert("saveBatch", paramsList);
	}
	
	public void deleteRolePrivilege(List<String> ids){
		this.batchDelete(SQL_ID_DELETE, ids);
	}
}
