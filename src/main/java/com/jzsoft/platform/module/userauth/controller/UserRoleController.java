package com.jzsoft.platform.module.userauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzsoft.platform.core.web.argument.annotation.FormModel;
import com.jzsoft.platform.core.web.controller.SpringController;
import com.jzsoft.platform.module.user.model.SysUser;
import com.jzsoft.platform.module.user.service.SysUserService;
import com.jzsoft.platform.module.userauth.service.RoleService;
import com.jzsoft.platform.module.userauth.service.UserRoleService;

@Controller
@RequestMapping("/sys/userrole")
public class UserRoleController extends SpringController {

	@Autowired
	private SysUserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;
	
	@RequestMapping("/toUserBindRole")
	public String toUserBindRole(String userId) {
		setAttribute("roleListTypeMap", userRoleService.getRoleListTypeMap());
		setAttribute("userId", userId);
		setAttribute("roleIds", userService.get(userId).getRoleIds());
		return "/platform/module/userauth/userrole/userBindRole";
	}
	
	@RequestMapping("/saveUserRoles")
	@ResponseBody
	public void saveUserRoles(@FormModel("model") SysUser model) {
		userRoleService.saveUserRoles(model);
	}
	
	@RequestMapping("/getRolesJson")
	@ResponseBody
	public String getRolesJson(String userId) {
		return userRoleService.getRolesJsonWithUserId(userId);
	}
	
	
}
