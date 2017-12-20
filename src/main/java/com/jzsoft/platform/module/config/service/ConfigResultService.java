package com.jzsoft.platform.module.config.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jzsoft.platform.core.helper.UserHelper;
import com.jzsoft.platform.module.config.dao.ConfigResultDAO;
import com.jzsoft.platform.module.config.model.Config;
import com.jzsoft.platform.module.config.model.ConfigItem;
import com.jzsoft.platform.module.config.model.ConfigResult;
import com.jzsoft.platform.module.config.model.ConfigResultItem;
import com.jzsoft.platform.module.config.vo.ConfigResultListVO;
import com.jzsoft.platform.module.config.vo.ConfigResultSaveVO;
import com.jzsoft.platform.module.config.vo.ConfigResultValueVO;
import com.jzsoft.platform.module.config.vo.ModuleConfigResultVO;


/**
 * @Description : ()
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
@Service
@Transactional
public class ConfigResultService {
	public void delete(String id){
		configResultDAO.delete(id);
	}
	public void deleteWithConfigId(String configId){
		configResultDAO.deleteWithConfigId(configId);
	}
	
	public List<ConfigResult> getListWithGlobal(){
		return configResultDAO.getListWithConfigKind(Config.KIND_GLOBAL);
	}
	
	public List<ConfigResult> getListWithModule(){
		return configResultDAO.getListWithConfigKind(Config.KIND_MODULE);
	}
	
	public List<ConfigResult> getListWithModuleId(String moduleId){
		return configResultDAO.getListWithModuleId(moduleId);
	}
	
	public List<ConfigResult> getListWithUserId(String userId){
		return configResultDAO.getListWithUserId(userId);
	}
	
	public List<ConfigResultValueVO> getResultValueVOsWithGlobal(){
		return configResultDAO.getResultValueVOsWithConfigKind(Config.KIND_GLOBAL);
	}
	
	public List<ConfigResultValueVO> getResultValueVOsWithModule(){
		return configResultDAO.getResultValueVOsWithConfigKind(Config.KIND_MODULE);
	}
	
	public List<String> getResultValueListWithConfigCode(String code){
		return configResultDAO.getResultValueListWithConfigCode(code);
	}
	
	public List<String> getResultValueListWithConfigCodeAndUser(String code,String userId){
		return configResultDAO.getResultValueListWithConfigCodeAndUser(code, userId);
	}
	
	public ConfigResultListVO getGlobalConfigResultVO(){
		List<Config> configs = configService.getListWithGlobal();
		List<ConfigItem> configItems = configItemService.getListWithGlobalConfig();
		List<ConfigResultValueVO> configResultVOs = this.getResultValueVOsWithGlobal();
		return new ConfigResultListVO(configs, configItems, configResultVOs);
	}
	
	public ModuleConfigResultVO getModuleConfigResultVO(){
		List<Config> configs = configService.getListWithModule();
		List<ConfigItem> configItems = configItemService.getListWithModuleConfig();
		List<ConfigResultValueVO> configResultVOs = this.getResultValueVOsWithModule();
		return new ModuleConfigResultVO(configs, configItems, configResultVOs);
	}
	
	public ConfigResultListVO getUserConfigResultVO(){
		List<Config> configs = configService.getListWithUser();
		List<ConfigItem> configItems = configItemService.getListWithUserConfig();
		List<ConfigResultValueVO> configResultVOs = configResultDAO.getResultValueVOsWithUserId(UserHelper.getCurrUserID());
		return new ConfigResultListVO(configs, configItems, configResultVOs);
	}
	
	public void saveGlobalConfigResult(List<ConfigResultSaveVO> saveVos){
		List<ConfigResult> configResults = this.getListWithGlobal();
		saveConfigResult(saveVos, configResults,false);
	}
	
	public void saveModuleConfigResult(String moduleId , List<ConfigResultSaveVO> saveVos){
		List<ConfigResult> configResults = this.getListWithModuleId(moduleId);
		saveConfigResult(saveVos, configResults,false);
	}
	
	public void saveUserConfigResult(List<ConfigResultSaveVO> saveVos){
		List<ConfigResult> configResults = this.getListWithUserId(UserHelper.getCurrUserID());
		saveConfigResult(saveVos, configResults,true);
	}
	
	private void saveConfigResult(List<ConfigResultSaveVO> saveVos, List<ConfigResult> configResults,boolean isUserConfig) {
		String userId=null;
		if(isUserConfig){
			userId = UserHelper.getCurrUserID();
		}
		List<String> voKeys = new ArrayList<String>();
		String separator = "|";
		for(ConfigResultSaveVO vo : saveVos){
			for(String value:vo.getValues()){
				voKeys.add(vo.getConfigId()+separator+value+separator+vo.getMode());
			}
		}
		Map<String,Object> oldResultKeyMap = new HashMap<String, Object>(0);
		Map<String,ConfigResult> configResultMap = new HashMap<String, ConfigResult>(0);
		for(ConfigResult configResult : configResults){
			configResultMap.put(configResult.getConfigId(), configResult);
			if(configResult.getConfig().isModeInput()){
				String key = configResult.getConfig().getId()+separator+configResult.getCustomValue()+separator+configResult.getConfig().getMode();
				oldResultKeyMap.put(key, configResult);
				continue;
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
			
			ConfigResult configResult = configResultMap.get(configId);
			if(configResult==null){
				configResult = new ConfigResult();
				configResult.setConfigId(configId);
				configResult.setUserId(userId);
			}
			if(Config.MODE_INPUT.equals(mode)){
				configResult.setCustomValue(value);
				configResultDAO.saveOrUpdate(configResult);
			}else if(!configResultMap.containsKey(configId)){
				configResultDAO.save(configResult);
			}
			configResultMap.put(configId, configResult);
			if(!Config.MODE_INPUT.equals(mode)){
				configResultItemService.save(configResult.getId(), value);
			}
		}
		for(String key:delKeys){
			String[]configValue=key.split("["+separator+"]");
			String mode = configValue[2];
			if(Config.MODE_INPUT.equals(mode)){
				continue;
//				ConfigResult configResult = (ConfigResult)oldResultKeyMap.get(key);
//				configResultDAO.delete(configResult.getId());
			}
			if(!Config.MODE_INPUT.equals(mode)){
				ConfigResultItem configResultItem = (ConfigResultItem)oldResultKeyMap.get(key);
				configResultItemService.delete(configResultItem.getId());
			}
		}
	}
	
	@Autowired
	private ConfigResultDAO configResultDAO;
	@Autowired
	private ConfigService configService;
	@Autowired
	private ConfigItemService configItemService;
	@Autowired
	private ConfigResultItemService configResultItemService;
}


