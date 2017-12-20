package com.jzsoft.platform.module.dictionary.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jzsoft.platform.core.dao.BaseDAO;
import com.jzsoft.platform.module.dictionary.model.Dictionary;

@Repository
public class DictionaryDAO extends BaseDAO<Dictionary, String>{
	/**
	 * 根据字典类型查找字典条目
	 */
	public List<Dictionary> getDictWithKind(String kind){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("kind", kind);
		return this.getList("getDictWithKind", params);
	}
	
	public List<Dictionary> getDictWithKindAndCanedit(String kind){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("kind", kind);
		params.put("editableFlag", Dictionary.DICT_CANEDIT);
		return this.getList("getDictWithKind", params);
	}
	
	/**
	 * 根据字典条目编码得到到字典条目
	 */
	public Dictionary getDictWithCode(String code){
		return this.get("getDictWithCode", code);
	}
	
	public boolean isExistsDictCode(String code , String excludeCode){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("code", code);
		if(StringUtils.isNotBlank(excludeCode)){
			params.put("excludeCode", excludeCode);
		}
		Dictionary dict = this.get("getDictWithCodeAndExclude", params);
		return dict!=null;
	}
}
