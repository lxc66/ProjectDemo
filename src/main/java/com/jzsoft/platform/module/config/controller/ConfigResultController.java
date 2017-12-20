package com.jzsoft.platform.module.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzsoft.platform.core.web.argument.annotation.FormModel;
import com.jzsoft.platform.core.web.controller.SpringController;
import com.jzsoft.platform.module.config.service.ConfigResultService;
import com.jzsoft.platform.module.config.vo.ConfigResultSaveVO;

@Controller
@RequestMapping("/sys/configresult")
public class ConfigResultController extends SpringController {

	@Autowired
	private ConfigResultService configResultService;

	@RequestMapping("/list")
	public String list() {
		return "/platform/module/config/configresult/configresult-list";
	}
	
	@RequestMapping("/toGlobalConfigResult")
	public String toGlobalConfigResult() {
		setAttribute("globalConfigResultVO", configResultService.getGlobalConfigResultVO());
		return "/platform/module/config/configresult/configresult-global";
	}
	
	@RequestMapping("/saveGlobalConfigResult")
	@ResponseBody
	public void saveGlobalConfigResult(@FormModel("vos") List<ConfigResultSaveVO> vos){
		configResultService.saveGlobalConfigResult(vos);
	}
	
	@RequestMapping("/toModuleConfigResult")
	public String toModuleConfigResult() {
		setAttribute("moduleConfigResultVO", configResultService.getModuleConfigResultVO());
		return "/platform/module/config/configresult/configresult-module";
	}
	
	@RequestMapping("/saveModuleConfigResult")
	@ResponseBody
	public void saveModuleConfigResult(String moduleId , @FormModel("vos") List<ConfigResultSaveVO> vos){
		configResultService.saveModuleConfigResult(moduleId, vos);
	}
	
	@RequestMapping("/toUserConfigResult")
	public String toUserConfigResult() {
		setAttribute("configResultListVO", configResultService.getUserConfigResultVO());
		return "/platform/module/config/configresult/configresult-user";
	}
	
	@RequestMapping("/saveUserConfigResult")
	@ResponseBody
	public void saveUserConfigResult(@FormModel("vos") List<ConfigResultSaveVO> vos){
		configResultService.saveUserConfigResult(vos);
	}
}
