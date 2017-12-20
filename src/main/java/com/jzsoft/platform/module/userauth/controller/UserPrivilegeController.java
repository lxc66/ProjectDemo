package com.jzsoft.platform.module.userauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzsoft.platform.core.web.argument.annotation.FormModel;
import com.jzsoft.platform.core.web.controller.SpringController;
import com.jzsoft.platform.module.user.model.SysUser;
import com.jzsoft.platform.module.user.service.SysUserService;
import com.jzsoft.platform.module.userauth.service.PrivilegeService;
import com.jzsoft.platform.module.userauth.service.UserPrivilegeService;

@Controller
@RequestMapping("/sys/userprivilege")
public class UserPrivilegeController extends SpringController {

	@Autowired
	private SysUserService userService;
	@Autowired
	private UserPrivilegeService userPrivilegeService;
	@Autowired
	private PrivilegeService privilegeService;
	
	@RequestMapping("/toUserBindPrivilege")
	public String toUserBindPrivilege(String userId) {
		setAttribute("privilegeTreeJson", privilegeService.getTreeJson());
		setAttribute("privilegeTableVOJson", privilegeService.getPrivilegeTableVOJson());
		setAttribute("userId", userId);
		setAttribute("privilegeIds", userService.get(userId).getPrivilegeIds());
		return "/platform/module/userauth/userprivilege/userBindPrivilege";
	}
	
	@RequestMapping("/saveUserPrivileges")
	@ResponseBody
	public void saveUserPrivileges(@FormModel("model") SysUser model) {
		userPrivilegeService.saveUserPriveleges(model);
	}
	
	@RequestMapping("/getPrivilegesTreeJson")
	@ResponseBody
	public String getPrivilegesTreeJson(String userId) {
		return userPrivilegeService.getPrivelegesTreeJsonWithUserId(userId);
	}
}
