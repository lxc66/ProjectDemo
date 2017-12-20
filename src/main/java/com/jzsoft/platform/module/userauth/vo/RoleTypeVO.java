package com.jzsoft.platform.module.userauth.vo;

import java.util.ArrayList;
import java.util.List;

import com.jzsoft.platform.module.dictionary.model.DictionaryValue;


public class RoleTypeVO {
	private String name;
	private String code;
	
	public String getName() {
		return name;
	}
	public String getCode() {
		return code;
	}
	
	public static List<RoleTypeVO> convert(List<DictionaryValue> dictValues){
		List<RoleTypeVO> roleTypes = new ArrayList<RoleTypeVO>(0);
		for(DictionaryValue dictionaryValue: dictValues){
			RoleTypeVO vo = new RoleTypeVO();
			vo.code = dictionaryValue.getCode();
			vo.name = dictionaryValue.getValue();
			roleTypes.add(vo);
		}
		return roleTypes;
	}
}
