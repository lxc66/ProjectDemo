package com.jzsoft.platform.module.userauth.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.jzsoft.platform.module.user.model.SysUser;
import com.jzsoft.platform.module.userauth.dao.UserRoleDAO;
import com.jzsoft.platform.module.userauth.model.Role;
import com.jzsoft.platform.module.userauth.model.UserRole;
import com.jzsoft.platform.module.userauth.vo.RoleTypeVO;


/**
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class UserRoleService {

	public List<UserRole> getListWithUserId(String userId){
		return userRoleDAO.getListWithUserId(userId);
	} 
	
	public List<UserRole> getListWithRoleId(String roleId){
		return userRoleDAO.getListWithRoleId(roleId);
	} 
	
	public List<Role> getRolesWithUserId(String userId){
		return userRoleDAO.getRolesWithUserId(userId);
	} 
	
	public List<SysUser> getUsersWithRoleId(String roleId){
		return userRoleDAO.getUsersWithRoleId(roleId);
	} 
	
	public void updateUserRole(String userId , List<String> roleIdList){
		List<UserRole> userRoles = getListWithUserId(userId);
		Map<String,String> userRoleMap = new HashMap<String, String>(0);
		for(UserRole userRole : userRoles){
			userRoleMap.put(userRole.getRoleId(), userRole.getId());
		}
		List<String> oldRoleIds = buildRoleIds(userRoles);
		List<String> roleWithAdd = new ArrayList<String>();
		List<String> userRoleWithDel = new ArrayList<String>();
		for(String roleId : roleIdList){
			if(!oldRoleIds.contains(roleId)){
				roleWithAdd.add(roleId);
			}
		}
		
		for(String roleId : oldRoleIds){
			if(!roleIdList.contains(roleId)){
				userRoleWithDel.add(userRoleMap.get(roleId));
			}
		}
		userRoleDAO.saveUserRole(userId, roleWithAdd);
		userRoleDAO.deleteUserRole(userRoleWithDel);
	}
	
	public List<String> getRoleIdsWithUserId(String userId){
		List<UserRole> userRoles = getListWithUserId(userId);
		return buildRoleIds(userRoles);
	}
	
	
	public void deleteWithUserId(String userId){
		userRoleDAO.deleteWithUserId(userId);
	}
	
	public void deleteWithRoleId(String roleId){
		userRoleDAO.deleteWithRoleId(roleId);
	}
	
	private List<String> buildRoleIds(List<UserRole> userRoles) {
		List<String> ids = new ArrayList<String>();
		for(UserRole userRole : userRoles){
			ids.add(userRole.getRoleId());
		}
		return ids;
	}
	
	public Map<String,List<Role>> getRoleListTypeMap(){
		List<Role> roles = roleService.getAll();
		List<RoleTypeVO> roleTypes = roleService.getRoleTypes();
		Map<String,List<Role>> roleTypeMap = new LinkedHashMap<String, List<Role>>();
		Map<String,String> roleTypeNameMap = new HashMap<String, String>();
		for(RoleTypeVO roleType : roleTypes){
			roleTypeMap.put(roleType.getName(), new ArrayList<Role>());
			roleTypeNameMap.put(roleType.getCode(), roleType.getName());
		}
		for(Role role : roles){
			String roleTypeName = roleTypeNameMap.get(role.getType());
			roleTypeMap.get(roleTypeName).add(role);
		}
		return roleTypeMap;
	}
	
	public void saveUserRoles(SysUser model){
		this.updateUserRole(model.getId(), model.getRoleIdList());
	}
	
	public String getRolesJsonWithUserId(String userId){
		final List<String> includeFilter = Arrays.asList(new String[]{"id","name"});
		PropertyFilter propertyFilter = new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				return includeFilter.contains(name);
			}
		};
		List<Role> roles = this.getRolesWithUserId(userId);
		return JSONArray.toJSONString(roles,propertyFilter);
	}
	
	@Autowired
	private UserRoleDAO userRoleDAO;
	@Autowired
	private RoleService roleService;
}


