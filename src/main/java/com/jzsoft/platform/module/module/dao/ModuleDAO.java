package com.jzsoft.platform.module.module.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jzsoft.platform.core.dao.BaseDAO;
import com.jzsoft.platform.module.module.model.Module;

@Repository
public class ModuleDAO extends BaseDAO<Module, String>{
	/**
	 * 获取系统模块的最大排序号
	 */
	public int getMaxOrderNum(){
		return (Integer) this.get("getMaxOrderNum",null);
	}
	
	/**
	 * 获取系统模块列表（排序）
	 */
	public List<Module> getListWithDeployed(){
		return this.getList("getListWithDeployed");
	}
	
	public List<Module> getListWithIds(List<String>ids){
		return this.getList("getListWithIds",ids);
	}
	
	/**
	 * 获取系统模块列表（根据用户权限）
	 */
	public List<Module> getListWithUserPrivalege(String userId){
//		String hql="from Module m where exists(from Privilege pri , UserPosition up where pri in elements(up.position.privileges) and pri.module.id = m.id and up.user.id = ?) order by num,code";
		return null;
	}
	
	/**
	 * 排序
	 */
	public void sort(String[] ids){
		for(int i=0 ; i<ids.length ; i++){
//			this.batchExecute("update Module m set m.num=? where m.id=?",(i+1),ids[i]);
		}
	}
	
	public Module getWithCode(String code){
		return this.get("getWithCode",code);
	}
	
	public Module getWithCodeAndExclude(String code , String excludeCode) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("code", code);
		if(StringUtils.isNotBlank(excludeCode)){
			params.put("excludeCode", excludeCode);
		}
		return this.get("getWithCodeAndExclude", params);
	}
}
