package com.jzsoft.platform.core.shiro;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserRolePermissionHelper {
	
	public static final Map<String,String[]> userRoleMap = new HashMap<String, String[]>(0);
	public static final Map<String,String[]> rolePermissionMap = new HashMap<String, String[]>(0);
	public static final Map<String,String> permissionResourceMap = new HashMap<String, String>(0);
	
	
	
	public static Set<String> getRoles(String username){
		String[] roleArray = userRoleMap.get(username);
		Set<String> roles = new HashSet<String>(0);
		for(String role:roleArray){
			roles.add(role);
		}
		return roles;
	}
	
	public static Set<String> getPermissions(String username){
		String[] roleArray = userRoleMap.get(username);
		Set<String> permissions = new HashSet<String>(0);
		for(String role:roleArray){
			String[] permissionArray = rolePermissionMap.get(role);
			for(String permission:permissionArray){
				permissions.add(permission);
			}
		}
		return permissions;
	}
}
