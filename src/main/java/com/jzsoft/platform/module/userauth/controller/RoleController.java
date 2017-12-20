package com.jzsoft.platform.module.userauth.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzsoft.platform.core.web.argument.annotation.FormModel;
import com.jzsoft.platform.core.web.controller.SpringController;
import com.jzsoft.platform.module.dictionary.service.DictionaryValueService;
import com.jzsoft.platform.module.userauth.model.Role;
import com.jzsoft.platform.module.userauth.service.PrivilegeService;
import com.jzsoft.platform.module.userauth.service.RolePrivilegeService;
import com.jzsoft.platform.module.userauth.service.RoleService;
import com.jzsoft.platform.module.userauth.service.UserRoleService;

@Controller
@RequestMapping("/sys/role")
public class RoleController extends SpringController {

	@RequestMapping("/index")
	public String index() {
		setAttribute("positionTypeList", dictionaryValueService.getListWithDictCode(Role.ROLE_TYPE_DICT_CODE));
		return "/platform/module/userauth/role/role-index";
	}

	
	@RequestMapping("/getDataJsonWithType")
	@ResponseBody
	public String getDataJsonWithType(String type){
		return roleService.getListJsonWithType(type);
	}
	
	@RequestMapping("/input")
	public String input(String id) {
		Role model = null;
		if (StringUtils.isNotBlank(id)) {
			model = roleService.get(id);
		}else{
			model=new Role();
			model.setEnableFlag(Role.ENABLE);
		}
		setAttribute("model", model);
		setAttribute("positionTypeList", dictionaryValueService.getListWithDictCode(Role.ROLE_TYPE_DICT_CODE));
		setAttribute("privilegeTreeJson", privilegeService.getTreeJson());
		setAttribute("rolePrivilegeTreeJson", roleService.getRolePrivilegesTreeJson(id));
		setAttribute("enableFlagMap", Role.getEnableFlagMap());
//		this.getRequest().setAttribute("privilegeMap", privilegeService.getAllWithModuleMap());
		return "/platform/module/userauth/role/role-input";
	}
	
	@RequestMapping("/info")
	public String info(String id) {
		Role model = roleService.get(id);
		setAttribute("model", model);
		setAttribute("privilegeTreeJson", privilegeService.getTreeJson());
		setAttribute("userList", userRoleService.getUsersWithRoleId(id));
//		setAttribute("rolePrivilegeTreeJson", roleService.getRolePrivilegesTreeJson(id));
		return "/platform/module/userauth/role/role-info";
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(@FormModel("model") Role model) {
		if (StringUtils.isBlank(model.getId())) {
			roleService.save(model);
		} else {
			roleService.update(model);
		}
		return model.getId();
	}
	
	@RequestMapping("/updateRolePrivilege")
	@ResponseBody
	public String updateRolePrivilege(@FormModel("model") Role model){
		roleService.updateRolePrivilege(model);
		return model.getPrivilegeIds();
	}
	
	@RequestMapping("/getRolePrivilegesTreeJson")
	@ResponseBody
	public String getRolePrivilegesTreeJson(String id){
		return roleService.getRolePrivilegesTreeJson(id);
	}
	
	@RequestMapping("/forbidden")
	@ResponseBody
	public void forbidden(String id) {
		roleService.forbidden(id);
	}

	@RequestMapping("/enabled")
	@ResponseBody
	public void enabled(String id) {
		roleService.enabled(id);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String id) {
		roleService.delete(id);
	}

	
	@RequestMapping("/ajaxCheckCode")
	@ResponseBody
	public String ajaxCheckCode(String code , String excludeCode) {
		if (roleService.isExistsCode(code, excludeCode)) {
			return "false";
		}else{
			return "true";
		}
	}
	
	@RequestMapping("/ajaxCheckName")
	@ResponseBody
	public String ajaxCheckName(String name , String excludeName) {
		if (roleService.isExistsName(name, excludeName)) {
			return "false";
		}else{
			return "true";
		}
	}
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private PrivilegeService privilegeService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RolePrivilegeService rolePrivilegeService;
	@Autowired
	private DictionaryValueService dictionaryValueService;
}
