package com.jzsoft.platform.module.config.controller;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzsoft.platform.component.jqGrid.controller.JqGridController;
import com.jzsoft.platform.core.dao.Page;
import com.jzsoft.platform.core.helper.UserHelper;
import com.jzsoft.platform.core.web.argument.annotation.FormModel;
import com.jzsoft.platform.module.config.model.Config;
import com.jzsoft.platform.module.config.model.ConfigItem;
import com.jzsoft.platform.module.config.service.ConfigItemService;
import com.jzsoft.platform.module.config.service.ConfigService;
import com.jzsoft.platform.module.module.service.ModuleService;

@Controller
@RequestMapping("/sys/config")
public class ConfigController extends JqGridController<Config> {

	@RequestMapping("/index")
	public String index() {
		setAttribute("isLogin", UserHelper.getCurrUser()!=null);
		return "/platform/module/config/config-index";
	}
	
	@RequestMapping("/list")
	public String list() {
		return "/platform/module/config/config/config-list";
	}
	
	@RequestMapping("/listData")
	@ResponseBody
	public String listData(@FormModel("page") Page<Config> page,@FormModel("model") Config model,String sord,String sidx){
		return this.refreshDataTable(configService.getPage(model, page, sord, sidx));
	}
	
	@RequestMapping("/input")
	public String input(String id) {
		Config model = null;
		ArrayList<ConfigItem> configItems = new ArrayList<ConfigItem>();
		if (StringUtils.isNotBlank(id)) {
			model = configService.get(id);
			configItems.addAll(configItemService.getListWithConfigId(id));
		}else{
			model = new Config();
			model.setKind(Config.KIND_GLOBAL);
			model.setMode(Config.MODE_RADIO);
			model.setVisibleFlag(Config.VISIBLE_FLAG_VISIBLE);
			ConfigItem configItemDefault = new ConfigItem();
			configItemDefault.setDefaultFlag(ConfigItem.DEFAULT_YES);
			configItems.add(configItemDefault);
		}
		setAttribute("model",model);
		setAttribute("modulelist",moduleService.getModuleWithHaveDeployed());
		setAttribute("configItems",configItems);
		setAttribute("kindMap",Config.getKindMap());
		setAttribute("modeMap",Config.getModeMap());
		setAttribute("visibleFlagMap",Config.getVisibleFlagMap());
		return "/platform/module/config/config/config-input";
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(@FormModel("model") Config model) {
		if (StringUtils.isBlank(model.getId())) {
			configService.save(model);
		} else {
			configService.update(model);
		}
		return model.getId();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String id) {
		configService.delete(id);
	}
	
	@RequestMapping("/ajaxCheckCode")
	@ResponseBody
	public String ajaxCheckCode(String code , String excludeCode) {
		if (this.configService.isExistsCode(code, excludeCode)) {
			return "false";
		}else{
			return "true";
		}
	}
	
	@Autowired
	private ConfigService configService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private ConfigItemService configItemService;
}
