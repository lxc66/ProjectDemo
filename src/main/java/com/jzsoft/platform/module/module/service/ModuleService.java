package com.jzsoft.platform.module.module.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.jzsoft.platform.core.dao.Page;
import com.jzsoft.platform.module.module.dao.ModuleDAO;
import com.jzsoft.platform.module.module.model.Module;


/**
 * @Description : ()
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class ModuleService {

	
	public void save(Module model) {
		moduleDAO.save(model);
	}
	
	public void update(Module model) {
		moduleDAO.update(model);
	}
	
	
	/**
	 * 获取系统模块列表JSON串
	 */
	public String getListJson(){
		List<Module> modules = moduleDAO.getAll();
		return buildListJson(modules);
	}
	
	
	public String buildListJson(List<Module> modules){
		if(modules==null)return"";
		List<Map<String, String>> list= new ArrayList<Map<String,String>>(0);
		for (Module model : modules) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", model.getId());
			map.put("name", model.getName());
			list.add(map);
		}
		return JSONArray.toJSONString(list);
	}
	
	/**
	 * 排序
	 */
	public void sort(String[] ids){
		moduleDAO.sort(ids);
	}
	
	public Module get(String id){
		return moduleDAO.get(id);
	}
	
	public Module getWithCode(String code){
		return moduleDAO.getWithCode(code);
	}
	
	public List<Module> getAll() {
		return moduleDAO.getAll();
	}
	
	public List<Module> getListWithDeployed() {
		return moduleDAO.getListWithDeployed();
	}
	
	public List<Module> getListWithUserPrivalege(String userId) {
		return moduleDAO.getListWithUserPrivalege(userId);
	}
	
	public List<Module> getListWithIds(List<String>ids){
		return moduleDAO.getListWithIds(ids);
	}
	
	public Page<Module> getPage(Module model, Page<Module> page) {
		return moduleDAO.getPage(page);
	}
	
	public Map<String, String> getModuleNameMapWithDeployed() {
		List<Module> modules = this.getListWithDeployed();
		Map<String,String> moduleNameMap=new HashMap<String, String>(0);
		for(Module module : modules){
			moduleNameMap.put(module.getId(), module.getName());
		}
		return moduleNameMap;
	}
	
	public void delete(String id) {
		moduleDAO.delete(id);
	}
	
	
	/**
	 * 编码是否存在
	 */
	public boolean isExistsCode(String code,final String excludeCode) {
		Module module = moduleDAO.getWithCodeAndExclude(code, excludeCode);
		if (module != null) {
			return true;
		} 
		return false;
	}
	
	/**
	 * 模块是否部署
	 */
	public boolean isDeployWithCode(String code) {
		Module module = getWithCode( code);
		if(module==null){
			return false;
		}
		return module.isDeployed();
	}
	
	public List<Module> getModuleWithHaveDeployed() {
		return moduleDAO.getListWithDeployed();
	}
	
	@Autowired
	private ModuleDAO moduleDAO;
}


