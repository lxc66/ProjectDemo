package com.jzsoft.platform.module.userauth.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jzsoft.platform.module.module.model.Module;
import com.jzsoft.platform.module.userauth.model.Privilege;

public class PrivilegeTableDataVO {
	private String id;
	private String name;
	private String parentId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isExistsParent(){
		return StringUtils.isNotBlank(parentId);
	}
	
	public static PrivilegeTableDataVO convertWithPrivilege(Privilege privilege){
		PrivilegeTableDataVO vo = new PrivilegeTableDataVO();
		vo.setId(privilege.getId());
		vo.setName(privilege.getName());
		if(privilege.isExistsParent()){
			vo.setParentId(privilege.getParentId());
		}
		return vo;
	}
	
	public static PrivilegeTableDataVO convertWithModule(Module module){
		PrivilegeTableDataVO vo = new PrivilegeTableDataVO();
		vo.setId(module.getId());
		vo.setName(module.getName());
		return vo;
	}
	
	public static List<PrivilegeTableDataVO> convertWithPrivileges(List<Privilege> privileges){
		List<PrivilegeTableDataVO> vos = new ArrayList<PrivilegeTableDataVO>(0);
		for(Privilege privilege : privileges){
			vos.add(convertWithPrivilege(privilege));
		}
		return vos;
	}
	
	public static List<PrivilegeTableDataVO> convertWithModules(List<Module> modules){
		List<PrivilegeTableDataVO> vos = new ArrayList<PrivilegeTableDataVO>(0);
		for(Module module : modules){
			vos.add(convertWithModule(module));
		}
		return vos;
	}
}
