package com.jzsoft.platform.module.config.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jzsoft.platform.core.dao.BaseDAO;
import com.jzsoft.platform.module.config.model.ConfigResultItem;

@Repository
public class ConfigResultItemDAO extends BaseDAO<ConfigResultItem, String>{
	public void deleteWithConfigId(String configId){
		this.delete("deleteWithConfigId", configId);
	}
	
	public void deleteWithConfigResultId(String configResultId){
		this.delete("deleteWithConfigResultId", configResultId);
	}
	
	public void deleteWithConfigItemIds(List<String> configItemIds){
		this.delete("deleteWithConfigItemIds", configItemIds);
	}
	
	public List<ConfigResultItem> getListWithConfigResultId(String configResultId){
		return this.getList("getListWithConfigResultId", configResultId);
	}
}
