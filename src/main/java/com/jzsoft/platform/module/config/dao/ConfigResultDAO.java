package com.jzsoft.platform.module.config.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jzsoft.platform.core.dao.BaseDAO;
import com.jzsoft.platform.module.config.model.ConfigResult;
import com.jzsoft.platform.module.config.vo.ConfigResultValueVO;

@Repository
public class ConfigResultDAO extends BaseDAO<ConfigResult, String>{
	public void deleteWithConfigId(String configId){
		this.delete("deleteWithConfigId", configId);
	}
	
	public ConfigResult getWithConfigId(String configId){
		return this.get("getWithConfigId", configId);
	}
	
	public ConfigResult getWithConfigIdAndUserId(String configId,String userId){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("configId", configId);
		params.put("userId", userId);
		return this.get("getWithConfigIdAndUserId", params);
	}
	
	public List<String> getResultValueListWithConfigCode(String code){
		return this.getList("getResultValueListWithConfigCode", code);
	}
	
	public List<String> getResultValueListWithConfigCodeAndUser(String code,String userId){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("code", code);
		params.put("userId", userId);
		return this.getList("getResultValueListWithConfigCodeAndUser", params);
	}
	
	public List<ConfigResult> getListWithConfigKind(String kind){
		return this.getList("getListWithConfigKind", kind);
	}
	
	public List<ConfigResult> getListWithModuleId(String moduleId){
		return this.getList("getListWithModuleId",moduleId);
	}
	
	public List<ConfigResult> getListWithUserId(String userId){
		return this.getList("getListWithUserId",userId);
	}
	
	public List<ConfigResultValueVO> getResultValueVOsWithConfigKind(String kind){
		return this.getList("getResultValueVOsWithConfigKind", kind);
	}
	
	public List<ConfigResultValueVO> getResultValueVOsWithUserId(String userId){
		return this.getList("getResultValueVOsWithUserId", userId);
	}
	
}
