package com.jzsoft.platform.module.userauth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jzsoft.platform.module.userauth.dao.RolePrivilegeDAO;
import com.jzsoft.platform.module.userauth.model.Privilege;
import com.jzsoft.platform.module.userauth.model.Role;
import com.jzsoft.platform.module.userauth.model.RolePrivilege;


/**
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class RolePrivilegeService {

	public List<RolePrivilege> getListWithRoleId(String roleId){
		return rolePrivilegeDAO.getListWithRoleId(roleId);
	}
	
	public List<RolePrivilege> getListWithPrivilegeId(String privilegeId){
		return rolePrivilegeDAO.getListWithPrivilegeId(privilegeId);
	} 
	
	public List<Privilege> getPrivilegeWithRoleId(String roleId){
		return rolePrivilegeDAO.getPrivilegeWithRoleId(roleId);
	} 
	public List<Role> getRoleWithPrivilegeId(String privilegeId){
		return rolePrivilegeDAO.getRoleWithPrivilegeId(privilegeId);
	} 
	
	public void updateRolePrivilege(String roleId , List<String> privilegeIdList){
		List<RolePrivilege> rolePrivileges = getListWithRoleId(roleId);
		Map<String,String> rolePrivilegeMap = new HashMap<String, String>(0);
		for(RolePrivilege rolePrivilege : rolePrivileges){
			rolePrivilegeMap.put(rolePrivilege.getPrivilegeId(), rolePrivilege.getId());
		}
		List<String> oldPrivilegeIds = buildPrivilegeIds(rolePrivileges);
		List<String> privilegeWithAdd = new ArrayList<String>();
		List<String> rolePrivilegeWithDel = new ArrayList<String>();
		for(String privilegeId : privilegeIdList){
			if(!oldPrivilegeIds.contains(privilegeId)){
				privilegeWithAdd.add(privilegeId);
			}
		}
		
		for(String privilegeId : oldPrivilegeIds){
			if(!privilegeIdList.contains(privilegeId)){
				rolePrivilegeWithDel.add(rolePrivilegeMap.get(privilegeId));
			}
		}
		rolePrivilegeDAO.saveRolePrivilege(roleId, privilegeWithAdd);
		rolePrivilegeDAO.deleteRolePrivilege(rolePrivilegeWithDel);
	}
	
	public List<String> getPrivilegeIdsWithRoleId(String roleId){
		List<RolePrivilege> rolePrivileges = getListWithRoleId(roleId);
		return buildPrivilegeIds(rolePrivileges);
	}
	
	
	public void deleteWithRoleId(String roleId){
		rolePrivilegeDAO.deleteWithRoleId(roleId);
	}
	
	public void deleteWithPrivilegeId(String privilegeId){
		rolePrivilegeDAO.deleteWithPrivilegeId(privilegeId);
	}
	
	private List<String> buildPrivilegeIds(List<RolePrivilege> rolePrivileges) {
		List<String> privilegeIds = new ArrayList<String>();
		for(RolePrivilege rolePrivilege : rolePrivileges){
			privilegeIds.add(rolePrivilege.getPrivilegeId());
		}
		return privilegeIds;
	}
	
	@Autowired
	private RolePrivilegeDAO rolePrivilegeDAO;
}


