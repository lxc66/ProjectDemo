package com.jzsoft.platform.module.module.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzsoft.platform.core.dao.Page;
import com.jzsoft.platform.core.web.argument.annotation.FormModel;
import com.jzsoft.platform.core.web.controller.SpringController;
import com.jzsoft.platform.module.module.model.Module;
import com.jzsoft.platform.module.module.service.ModuleService;

@Controller
@RequestMapping("/sys/module")
public class ModuleController extends SpringController {

	@Autowired
	private ModuleService moduleService;

	@RequestMapping("/index")
	public String index() {
		return "/platform/module/module/module-index";
	}

	@RequestMapping("/list")
	public String list(@FormModel("page") Page<Module> page,@FormModel("model") Module model) {
		this.getRequest().setAttribute("pageObj", moduleService.getPage(model, page));
		return "/platform/module/module/module-list";
	}
	
	@RequestMapping("/getDataJson")
	@ResponseBody
	public String getDataJson(){
		return moduleService.getListJson();
	}
	
	@RequestMapping("/input")
	public String input(String id) {
		this.getRequest().setAttribute("deployFlagMap", Module.getDeployFlagMap());
		Module model = null;
		if (StringUtils.isNotBlank(id)) {
			model = moduleService.get(id);
		}else{
			model = new Module();
			model.setDeployFlag(Module.DEPLOY_YES);
		}
		setAttribute("model", model);
		return "/platform/module/module/module-input";
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(@FormModel("model") Module model) {
		if (StringUtils.isBlank(model.getId())) {
			moduleService.save(model);
		} else {
			moduleService.update(model);
		}
		return model.getId();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String id) {
		moduleService.delete(id);
	}

	@RequestMapping("/sort")
	@ResponseBody
	public void sort(String[] ids){
		moduleService.sort(ids);
	}
	
	/**
	 * Ajax字典编码检查
	 * @return 0 通过验证 其它返回结果代表没有通过验证，并且结果为错误信息
	 */
	@RequestMapping("/ajaxCheckCode")
	@ResponseBody
	public String ajaxCheckCode(String code , String excludeCode) {
		if (this.moduleService.isExistsCode(code, excludeCode)) {
			return "false";
		}else{
			return "true";
		}
	}
}
