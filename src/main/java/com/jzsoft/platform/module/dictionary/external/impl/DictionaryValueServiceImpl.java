package com.jzsoft.platform.module.dictionary.external.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jzsoft.platform.module.dictionary.external.IDictionaryValueService;
import com.jzsoft.platform.module.dictionary.model.DictionaryValue;
import com.jzsoft.platform.module.dictionary.service.DictionaryValueService;


/**
 * @author : wdw
 * 字典值接口实现
 */
@Service
@Transactional
public class DictionaryValueServiceImpl implements IDictionaryValueService {

	@Override
	public List<DictionaryValue> getDictValuesWithDictCode(String dictCode) {
		return dictionaryValueService.getListWithDictCode(dictCode);
	}
	
	@Override
	public String getDictValueCodeWithDictCodeAndDictValue(String dictCode, String dictvalue) {
		return dictionaryValueService.getValueCodeWithDictCodeAndValue(dictCode,dictvalue);
	}

	@Override
	public Map<String, String> getDictValueMapWithDictCode(String dictCode) {
		return dictionaryValueService.getDictValueMapWithDictCode(dictCode);
	}
	@Override
	public Map<String, String> getDictValueCodeMapWithDictCode(String dictCode) {
		return dictionaryValueService.getDictValueCodeMapWithDictCode(dictCode);
	}
	@Autowired
	private DictionaryValueService dictionaryValueService;

}


