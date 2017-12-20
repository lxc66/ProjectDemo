package com.jzsoft.platform.module.config.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jzsoft.platform.module.config.dao.ConfigItemDAO;
import com.jzsoft.platform.module.config.model.Config;
import com.jzsoft.platform.module.config.model.ConfigItem;


/**
 * @Description : ()
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
@Service
@Transactional
public class ConfigItemService {
	
	public List<ConfigItem> getListWithConfigId(String configId){
		return configItemDAO.getListWithConfigId(configId);
	}
	
	public void save(String configId,List<ConfigItem> configItemList){
		if (configItemList == null || configItemList.size() == 0) {
			return;
		}
		for(ConfigItem configItem: configItemList){
			configItem.setConfigId(configId);
			configItemDAO.save(configItem);
		}
	}
	
	public void update(String configId,List<ConfigItem> configItemList){
		List<ConfigItem> configItemsOld = this.getListWithConfigId(configId);
		// 删除配置项的值
		deleteWithUpdate(configItemsOld, configItemList);
		if (configItemList == null || configItemList.size() == 0) {
			return;
		}
		for(ConfigItem configItem: configItemList){
			configItem.setConfigId(configId);
			configItemDAO.saveOrUpdate(configItem);
		}
	}
	
	/**
	 * 过滤删除的配置项
	 */
	private void deleteWithUpdate(List<ConfigItem> configItemsOld,List<ConfigItem> configItemsNew) {
			List<String> idsWithNew = buildIds(configItemsNew);
			List<String> idsWithDel = new ArrayList<String>();
			List<String> idsWithDelOrDisable = new ArrayList<String>();
			for(ConfigItem configItem : configItemsOld){
				if(!idsWithNew.contains(configItem.getId())){
					idsWithDel.add(configItem.getId());
				}
			}
			
			idsWithDelOrDisable.addAll(idsWithDel);
			for(ConfigItem configItem : configItemsNew){
				if (StringUtils.isNotEmpty(configItem.getId()) && ConfigItem.ENABLE_NO.equals(configItem.getEnableFlag())) {
					idsWithDelOrDisable.add(configItem.getId());
				}
			}
			
			configResultItemService.deleteWithConfigItemIds(idsWithDelOrDisable);
			for(String id : idsWithDel){
				configItemDAO.delete(id);
			}
			
	}

	public void deleteWithConfigId(String configId){
		configResultItemService.deleteWithConfigId(configId);
		configItemDAO.deleteWithConfigId(configId);
	}
	
	private List<String> buildIds(List<ConfigItem> list) {
		List<String> ids = new ArrayList<String>();
		for(ConfigItem model : list){
			ids.add(model.getId());
		}
		return ids;
	}
	
	public List<ConfigItem> getListWithGlobalConfig(){
		return configItemDAO.getListWithConfigKind(Config.KIND_GLOBAL);
	}
	
	public List<ConfigItem> getListWithModuleConfig(){
		return configItemDAO.getListWithConfigKind(Config.KIND_MODULE);
	}
	
	public List<ConfigItem> getListWithUserConfig(){
		return configItemDAO.getListWithConfigKind(Config.KIND_USER);
	}
	
	@Autowired
	private ConfigItemDAO configItemDAO;
	@Autowired
	private ConfigResultItemService configResultItemService;
}


