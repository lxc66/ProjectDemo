package com.jzsoft.platform.module.config.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jzsoft.platform.module.config.dao.ConfigResultItemDAO;
import com.jzsoft.platform.module.config.model.ConfigResultItem;


/**
 * @Description : ()
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
@Service
@Transactional
public class ConfigResultItemService {

	public void deleteWithConfigItemIds(List<String> configItemIds){
		if(configItemIds!=null && configItemIds.size()>0){
			configResultItemDAO.deleteWithConfigItemIds(configItemIds);
		}
	}
	
	public void save(String configResultId,String configItemId){
		ConfigResultItem configResultItem = new ConfigResultItem(configResultId, configItemId);
		configResultItemDAO.save(configResultItem);
	}
	
	public void delete(String id){
		configResultItemDAO.delete(id);
	}
	
	public void deleteWithConfigId(String configId){
		configResultItemDAO.deleteWithConfigId(configId);
	}
	
	@Autowired
	private ConfigResultItemDAO configResultItemDAO;
}


