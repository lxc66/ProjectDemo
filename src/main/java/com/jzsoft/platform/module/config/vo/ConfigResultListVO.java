package com.jzsoft.platform.module.config.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jzsoft.platform.module.config.model.Config;
import com.jzsoft.platform.module.config.model.ConfigItem;

public class ConfigResultListVO {
	private List<Config> configs;
	private Map<String,List<ConfigItem>> configItemMap = new HashMap<String, List<ConfigItem>>();
	private Map<String,String> configResultValueMap = new HashMap<String, String>();
	
	public ConfigResultListVO(List<Config> configs, List<ConfigItem> configItems, List<ConfigResultValueVO> configResultVOs) {
		super();
		this.configs = configs;
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
	
	
	public List<Config> getConfigs() {
		return configs;
	}
	public Map<String, List<ConfigItem>> getConfigItemMap() {
		return configItemMap;
	}
	public Map<String, String> getConfigResultValueMap() {
		return configResultValueMap;
	}
	
			
}
