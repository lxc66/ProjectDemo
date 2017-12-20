package com.jzsoft.platform.module.userauth.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzsoft.platform.core.web.argument.annotation.FormModel;
import com.jzsoft.platform.core.web.controller.SpringController;
import com.jzsoft.platform.module.config.external.IConfigService;
import com.jzsoft.platform.module.module.service.ModuleService;
import com.jzsoft.platform.module.userauth.model.Privilege;
import com.jzsoft.platform.module.userauth.service.PrivilegeService;
import com.jzsoft.platform.module.userauth.service.RolePrivilegeService;
import com.jzsoft.platform.module.userauth.service.UserPrivilegeService;
import com.jzsoft.platform.module.userauth.util.UserAuthConstant;

@Controller
@RequestMapping("/sys/privilege")
public class PrivilegeController extends SpringController {

	@RequestMapping("/index")
	public String index() {
		return "/platform/module/userauth/privilege/privilege-index";
	}
	
	@RequestMapping("/listTreeData")
	@ResponseBody
	public String listTreeData(){
		return privilegeService.getTreeJson();
	}
	
	@RequestMapping("/input")
	public String input(String id) {
		setAttribute("moduleList", moduleService.getListWithDeployed());
		Privilege model = null;
		if (StringUtils.isNotBlank(id)) {
			model = privilegeService.get(id);
		}else{
			model = new Privilege();
		}
		setAttribute("model", model);
		setAttribute("isUseParent", UserAuthConstant.privilegeHaveParent());
		setAttribute("isUseUrl", UserAuthConstant.privilegeHaveUrl());
		return "/platform/module/userauth/privilege/privilege-input";
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(@FormModel("model") Privilege model) {
		if (StringUtils.isBlank(model.getId())) {
			privilegeService.save(model);
		} else {
			privilegeService.update(model);
		}
		return model.getId();
	}
	
	@RequestMapping("/info")
	public String info(String id) {
		Privilege model = privilegeService.get(id);
		setAttribute("model", model);
		setAttribute("roles", rolePrivilegeService.getRoleWithPrivilegeId(id));
		setAttribute("users", userPrivilegeService.getUsersWithPrivilegeId(id));
		setAttribute("isUseUrl", UserAuthConstant.privilegeHaveUrl());
//		this.getRequest().setAttribute("operationsIds", model.getOperationsIds());
		return "/platform/module/userauth/privilege/privilege-info";
	}
	
	@RequestMapping("/getParentTreeJson")
	@ResponseBody
	public String getParentTreeJson(String moduleId , String excludeId){
		return privilegeService.getTreeJsonWithModuleIdAndExcludeId(moduleId, excludeId);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String id) {
		privilegeService.delete(id);
	}
	
	@RequestMapping("/ajaxCheckCode")
	@ResponseBody
	public String ajaxCheckCode(String code , String excludeCode) {
		if (privilegeService.isExistsCode(code, excludeCode)) {
			return "false";
		}else{
			return "true";
		}
	}
	
	@RequestMapping("/ajaxCheckName")
	@ResponseBody
	public String ajaxCheckName(String name , String excludeName) {
		if (privilegeService.isExistsName(name, excludeName)) {
			return "false";
		}else{
			return "true";
		}
	}
	
	@Autowired
	private PrivilegeService privilegeService;
	@Autowired
	private RolePrivilegeService rolePrivilegeService;
	@Autowired
	private UserPrivilegeService userPrivilegeService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private IConfigService configService;
}
