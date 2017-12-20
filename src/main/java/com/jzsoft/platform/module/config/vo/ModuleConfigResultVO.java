package com.jzsoft.platform.module.config.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jzsoft.platform.core.spring.SpringContextHolder;
import com.jzsoft.platform.module.config.model.Config;
import com.jzsoft.platform.module.config.model.ConfigItem;
import com.jzsoft.platform.module.module.model.Module;
import com.jzsoft.platform.module.module.service.ModuleService;

public class ModuleConfigResultVO {
	private List<Module> modules;
	private Map<String,List<Config>> moduleConfigMap = new HashMap<String, List<Config>>();
	private Map<String,List<ConfigItem>> configItemMap = new HashMap<String, List<ConfigItem>>();
	private Map<String,String> configResultValueMap = new HashMap<String, String>();
	
	public ModuleConfigResultVO(List<Config> configs, List<ConfigItem> configItems, List<ConfigResultValueVO> configResultVOs) {
		super();
		List<String> moduleIds = buildModuleIds(configs);
		modules = SpringContextHolder.getBean(ModuleService.class).getListWithIds(moduleIds);
		for(Config config : configs){
			if(!moduleConfigMap.containsKey(config.getModuleId())){
				moduleConfigMap.put(config.getModuleId(), new ArrayList<Config>());
			}
			moduleConfigMap.get(config.getModuleId()).add(config);
		}
		for(ConfigItem configItem : configItems){
			if(!configItemMap.containsKey(configItem.getConfigId())){
				configItemMap.put(configItem.getConfigId(), new ArrayList<ConfigItem>());
			}
			configItemMap.get(configItem.getConfigId()).add(configItem);
		}
		for(ConfigResultValueVO vo:configResultVOs){
			if(!configResultValueMap.containsKey(vo.getConfigId())){
				configResultValueMap.put(vo.getConfigId(), "");
			}
			String value = configResultValueMap.get(vo.getConfigId());
			value += value.length()==0?vo.getValue():","+vo.getValue();
			configResultValueMap.put(vo.getConfigId(), value);
		}
	}


	private List<String> buildModuleIds(List<Config> configs) {
		Set<String> ids = new HashSet<String>();
		for(Config config :configs){
			ids.add(config.getModuleId());
		}
		List<String> idList = new ArrayList<String>();
		idList.addAll(ids);
		return idList;
	}


	public List<Module> getModules() {
		return modules;
	}

	public Map<String, List<Config>> getModuleConfigMap() {
		return moduleConfigMap;
	}

	public Map<String, List<ConfigItem>> getConfigItemMap() {
		return configItemMap;
	}

	public Map<String, String> getConfigResultValueMap() {
		return configResultValueMap;
	}
			
}
