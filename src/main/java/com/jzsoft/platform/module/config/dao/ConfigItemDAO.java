package com.jzsoft.platform.module.config.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jzsoft.platform.core.dao.BaseDAO;
import com.jzsoft.platform.module.config.model.ConfigItem;

@Repository
public class ConfigItemDAO extends BaseDAO<ConfigItem, String>{
	public void deleteWithConfigId(String configId){
		this.delete("deleteWithConfigId", configId);
	}
	
	public List<ConfigItem> getListWithConfigId(String configId){
		return this.getList("getListWithConfigId", configId);
	}
	
	public List<ConfigItem> getListWithConfigKind(String configKind){
		return this.getList("getListWithConfigKind", configKind);
	}
	
	public List<ConfigItem> getListWithConfigCodeAndValues(String configCode,String[] values){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("configCode", configCode);
		params.put("valueArray", values);
		return this.getList("getListWithConfigCodeAndValues", params);
	}
}
