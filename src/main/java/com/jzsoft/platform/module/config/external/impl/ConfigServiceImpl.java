package com.jzsoft.platform.module.config.external.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jzsoft.platform.module.config.dao.ConfigItemDAO;
import com.jzsoft.platform.module.config.dao.ConfigResultDAO;
import com.jzsoft.platform.module.config.external.IConfigService;
import com.jzsoft.platform.module.config.model.Config;
import com.jzsoft.platform.module.config.model.ConfigItem;
import com.jzsoft.platform.module.config.model.ConfigResult;
import com.jzsoft.platform.module.config.model.ConfigResultItem;
import com.jzsoft.platform.module.config.service.ConfigResultItemService;
import com.jzsoft.platform.module.config.service.ConfigResultService;
import com.jzsoft.platform.module.config.service.ConfigService;
import com.jzsoft.platform.module.config.vo.ConfigResultSaveVO;

@Service
@Transactional
public class ConfigServiceImpl implements IConfigService{
	@Autowired
	private ConfigService configService;
	@Autowired
	private ConfigResultService configResultService;
	@Autowired
	private ConfigResultItemService configResultItemService;
	@Autowired
	private ConfigItemDAO configItemDAO;
	@Autowired
	private ConfigResultDAO configResultDAO;

	@Override
	public Config getConfigWithCode(String code) {
		return configService.getWithCode(code);
	}
	
	@Override
	public List<String> getGlobalConfigResultWithCode(String code) {
		return configResultService.getResultValueListWithConfigCode(code);
	}

	@Override
	public List<String> getModuleConfigResultWithCode(String code) {
		return configResultService.getResultValueListWithConfigCode(code);
	}

	@Override
	public List<String> getUserConfigResultWithCode(String code, String userId) {
		return configResultService.getResultValueListWithConfigCodeAndUser(code, userId);
	}

	@Override
	public void updateGlobalConfigResult(String code, String result) {
		if(StringUtils.isBlank(code)||result==null)return;
		Config config = configService.getWithCode(code);
		ConfigResultSaveVO saveVo = buildUpdateConfigResultSaveVO(config,result);
		if(saveVo==null)return;
		ConfigResult configResult = configResultDAO.getWithConfigId(config.getId());
		this.saveConfigResult(saveVo, configResult, null);
	}

	@Override
	public void updateModuleConfigResult(String code, String result) {
		if(StringUtils.isBlank(code)||result==null)return;
		Config config = configService.getWithCode(code);
		ConfigResultSaveVO saveVo = buildUpdateConfigResultSaveVO(config,result);
		if(saveVo==null)return;
		ConfigResult configResult = configResultDAO.getWithConfigId(config.getId());
		this.saveConfigResult(saveVo, configResult, null);
	}

	@Override
	public void updateUserConfigResult(String code, String result, String userId) {
		if(StringUtils.isBlank(code)||StringUtils.isBlank(userId)||result==null)return;
		Config config = configService.getWithCode(code);
		ConfigResultSaveVO saveVo = buildUpdateConfigResultSaveVO(config,result);
		if(saveVo==null)return;
		ConfigResult configResult = configResultDAO.getWithConfigIdAndUserId(config.getId(), userId);
		this.saveConfigResult(saveVo, configResult, userId);
	}

	private ConfigResultSaveVO buildUpdateConfigResultSaveVO(Config config, String result){
		if(config==null)return null;
		ConfigResultSaveVO vo = new ConfigResultSaveVO();
		vo.setConfigId(config.getId());
		vo.setMode(config.getMode());
		
		if(config.isModeInput()){
			vo.setValues(Arrays.asList(result));
		}else{
			List<String>values = new ArrayList<String>();
			List<ConfigItem> configItems = configItemDAO.getListWithConfigCodeAndValues(config.getCode(), result.split(","));
			for(ConfigItem configItem : configItems){
				values.add(configItem.getId());
			}
			vo.setValues(values);
		}
		return vo;
	}
	
	private void saveConfigResult(ConfigResultSaveVO saveVo, ConfigResult configResult,String userId) {
		List<String> voKeys = new ArrayList<String>();
		String separator = "|";
		for(String value:saveVo.getValues()){
			voKeys.add(saveVo.getConfigId()+separator+value+separator+saveVo.getMode());
		}
		Map<String,Object> oldResultKeyMap = new HashMap<String, Object>(0);
		if(configResult!=null){
			if(configResult.getConfig().isModeInput()){
				String key = configResult.getConfig().getId()+separator+configResult.getCustomValue()+separator+configResult.getConfig().getMode();
				oldResultKeyMap.put(key, configResult);
			}
			for(ConfigResultItem configResultItem: configResult.getConfigResultItems()){
				String key = configResult.getConfig().getId()+separator+configResultItem.getConfigItemId()+separator+configResult.getConfig().getMode();
				oldResultKeyMap.put(key, configResultItem);
			}
		}
		
		List<String> delKeys = new ArrayList<String>();
		List<String> addKeys = new ArrayList<String>();
		for(String key:voKeys){
			if(!oldResultKeyMap.containsKey(key)){
				addKeys.add(key);
			}
		}
		for(String key : oldResultKeyMap.keySet()){
			if(!voKeys.contains(key)){
				delKeys.add(key);
			}
		}
		for(String key:addKeys){
			String[]configValue=key.split("["+separator+"]");
			String configId = configValue[0];
			String value = configValue[1];
			String mode = configValue[2];
			
			if(configResult==null){
				configResult = new ConfigResult();
				configResult.setConfigId(configId);
				configResult.setUserId(userId);
			}
			if(Config.MODE_INPUT.equals(mode)){
				configResult.setCustomValue(value);
				configResultDAO.saveOrUpdate(configResult);
			}else if(StringUtils.isBlank(configResult.getId())){
				configResultDAO.save(configResult);
			}
			if(!Config.MODE_INPUT.equals(mode)){
				configResultItemService.save(configResult.getId(), value);
			}
		}
		for(String key:delKeys){
			String[]configValue=key.split("["+separator+"]");
			String mode = configValue[2];
			if(Config.MODE_INPUT.equals(mode)){
				continue;
			}
			if(!Config.MODE_INPUT.equals(mode)){
				ConfigResultItem configResultItem = (ConfigResultItem)oldResultKeyMap.get(key);
				configResultItemService.delete(configResultItem.getId());
			}
		}
	}
}
