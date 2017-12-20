package com.jzsoft.platform.module.userauth.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jzsoft.platform.module.module.model.Module;
import com.jzsoft.platform.module.userauth.model.Privilege;

public class PrivilegeTableVO {
	private List<PrivilegeTableDataVO> tops;
	private Map<String, List<PrivilegeTableDataVO>> childMap;
	private Map<String, Integer> lastNodeNumMap = new HashMap<String, Integer>();
	private Map<String, Integer> lastDepthMap;
	private Map<String, Integer> depthMap;
	private Integer maxDepth=1;

	private Map<String, Privilege> privilegeMap = new HashMap<String, Privilege>();
	
	public PrivilegeTableVO(List<Module> modules , List<Privilege>privileges) {
		super();
		for(Privilege privilege : privileges){
			privilegeMap.put(privilege.getId(), privilege);
		}
		tops = PrivilegeTableDataVO.convertWithModules(modules);
		childMap = buildChildMap(privileges);
		
		for (PrivilegeTableDataVO top : tops) {
			buildLastNodeNumMap(top.getId());
		}
		
		lastDepthMap = buildLastDepthMap(privileges);
		depthMap = buildDepthMap(privileges);
	}

	private int buildLastNodeNumMap(String parentId) {
		int num = 0;
		if (childMap.containsKey(parentId)) {
			List<PrivilegeTableDataVO> children = childMap.get(parentId);
			for (PrivilegeTableDataVO child : children) {
				num += buildLastNodeNumMap(child.getId());
			}
		} else {
			num = 1;
		}
		lastNodeNumMap.put(parentId, num);
		return num;
	}
	
	private Map<String, List<PrivilegeTableDataVO>> buildChildMap(List<Privilege>privileges) {
		HashMap<String, List<PrivilegeTableDataVO>> map = new HashMap<String, List<PrivilegeTableDataVO>>();
		for (Privilege p : privileges) {
			String parentId = p.isExistsParent()?p.getParentId():p.getModuleId();
			if (!map.containsKey(parentId)) {
				map.put(parentId, new ArrayList<PrivilegeTableDataVO>(0));
			}
			map.get(parentId).add(PrivilegeTableDataVO.convertWithPrivilege(p));
		}
		return map;
	}
	
	
	private Map<String, Integer> buildLastDepthMap(List<Privilege> privileges) {
		HashMap<String, Integer> map = new HashMap<String, Integer>(0);
		for (Privilege p : privileges) {
			if(childMap.containsKey(p.getId())){
				continue;
			}
			Privilege privilege = p;
			int depth=2;
			while(privilege.isExistsParent()){
				depth++;
				privilege=privilegeMap.get(privilege.getParentId());
			}
			map.put(p.getId(), depth);
		}
		return map;
	}
	
	private Map<String, Integer> buildDepthMap(List<Privilege> privileges) {
		HashMap<String, Integer> map = new HashMap<String, Integer>(0);
		for (Privilege p : privileges) {
			Privilege privilege = p;
			int depth=2;
			while(privilege.isExistsParent()){
				depth++;
				privilege=privilegeMap.get(privilege.getParentId());
			}
			map.put(p.getId(), depth);
		}
		return map;
	}
	
	public int getMaxDepth(){
		for(Integer depth :lastDepthMap.values()){
			if(depth>maxDepth){
				maxDepth=depth;
			}
		}
		return maxDepth;
	}
	
	
	
	public List<PrivilegeTableDataVO> getTops() {
		return tops;
	}
	
	public Map<String, List<PrivilegeTableDataVO>> getChildMap() {
		return childMap;
	}
	public Map<String, Integer> getLastNodeNumMap() {
		return lastNodeNumMap;
	}
	public Map<String, Integer> getLastDepthMap() {
		return lastDepthMap;
	}
	public Map<String, Integer> getDepthMap() {
		return depthMap;
	}
}
