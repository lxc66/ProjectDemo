package com.jzsoft.platform.module.dictionary.external;

import java.util.List;
import java.util.Map;

import com.jzsoft.platform.module.dictionary.model.DictionaryValue;


/**
 * @author : wdw
 * 字典值接口
 */

public interface IDictionaryValueService {
	/**
	 * 获取字典值列表（根据字典编码）
	 * @param dictCode 字典编码
	 */
	public List<DictionaryValue> getDictValuesWithDictCode(String dictCode);
	
	/**
	 * 获取字典值编码Map（根据字典编码获取）
	 * @param dictCode 字典编码
	 * @return Map<字典值编码,字典值>
	 */
	public Map<String,String> getDictValueCodeMapWithDictCode(String dictCode);
	
	/**
	 * 获取字典值Map（根据字典编码获取）
	 * @param dictCode 字典编码
	 * @return Map<字典值,字典值编码>
	 */
	public Map<String,String> getDictValueMapWithDictCode(String dictCode);
	
	/**
	 * 获取字典值编码（根据字典编码 与 字典值）
	 * @param dictCode 字典编码
	 * @param dictvalue 字典值
	 * @return 字典值编码
	 */
	public String getDictValueCodeWithDictCodeAndDictValue(String dictCode, String dictvalue);
}


