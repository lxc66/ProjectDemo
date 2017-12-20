package com.jzsoft.platform.core.shiro.helper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jzsoft.platform.core.helper.UserHelper;
import com.jzsoft.platform.core.spring.SpringContextHolder;
import com.jzsoft.platform.module.userauth.model.Privilege;
import com.jzsoft.platform.module.userauth.service.PrivilegeService;

public class UserAuthHelper {
	
	public static final Map<String,String[]> userRoleMap = new HashMap<String, String[]>(0);
	public static final Map<String,String[]> rolePermissionMap = new HashMap<String, String[]>(0);
	public static final Map<String,String> permissionResourceMap = new HashMap<String, String>(0);
	
	
	
	public static Set<String> getRoles(){
		Set<String> roles = new HashSet<String>(0);
//		List<Role> roleList=null;
//		UserRoleService userRoleService = SpringContextHolder.getBean(UserRoleService.class);
//		if(UserHelper.isSuperAdmin()){
//			
//		}else{
//			roleList = userRoleService.getRolesWithUserId(UserHelper.getCurrUserID());
//		}
//		userRoleService.getRolesJsonWithUserId(UserHelper.getCurrUserID());
//		for(Role role:roleList){
//			roles.add(role.getCode());
//		}
		return roles;
	}
	
	public static Set<String> getPermissions(){
		PrivilegeService privilegeService = SpringContextHolder.getBean(PrivilegeService.class);
		Set<String> permissions = new HashSet<String>(0);
		List<Privilege> privileges=null;
		if(UserHelper.isSuperAdmin()){
			privileges = privilegeService.getAll();
		}else{
			privileges = privilegeService.getUserAllPrivileges(UserHelper.getCurrUserID());
		}
		for(Privilege privilege : privileges){
			permissions.add(privilege.getCode());
		}
		return permissions;
	}
}
