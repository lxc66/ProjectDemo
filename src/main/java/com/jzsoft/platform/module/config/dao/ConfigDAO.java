package com.jzsoft.platform.module.config.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jzsoft.platform.core.dao.BaseDAO;
import com.jzsoft.platform.module.config.model.Config;

@Repository
public class ConfigDAO extends BaseDAO<Config, String>{
	
	public Config getWithCode(String code){
		return this.get("getWithCode", code);
	}
	
	public Config getWithCodeAndExclude(String code , String excludeCode) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("code", code);
		if(StringUtils.isNotBlank(excludeCode)){
			params.put("excludeCode", excludeCode);
		}
		return this.get("getWithCodeAndExclude", params);
	}
	
	public List<Config> getListWithKind(String kind){
		return this.getList("getListWithKind", kind);
	}
}
