package com.jzsoft.platform.module.userauth.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.jzsoft.platform.module.module.model.Module;
import com.jzsoft.platform.module.module.service.ModuleService;
import com.jzsoft.platform.module.userauth.dao.PrivilegeDAO;
import com.jzsoft.platform.module.userauth.model.Privilege;
import com.jzsoft.platform.module.userauth.vo.PrivilegeTableVO;
import com.jzsoft.platform.util.tree.TreeNode;
import com.jzsoft.platform.util.tree.TreeUtil;


/**
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class PrivilegeService {
	
	public Privilege get(String id){
		return privilegeDAO.get(id);
	}
	
	public List<Privilege> getAll(){
		return privilegeDAO.getAll();
	}
	
	public void save(Privilege model) {
		int num = privilegeDAO.getMaxOrderNumWithModule(model.getModuleId());
		model.setNum(num+1);
		if(StringUtils.isBlank(model.getParentId())){
			model.setParentId(null);
		}
		privilegeDAO.save(model);
	}
	
	public void update(Privilege model) {
		if(StringUtils.isBlank(model.getParentId())){
			model.setParentId(null);
		}
		privilegeDAO.update(model);
	}
	
	/**
	 * 得到操作的JSON串
	 */
	public String getTreeJson() {
		List<Privilege> privileges = getAll();
		return buildTreeJson(privileges);
	}
	
	public String getTreeJsonWithModuleIdAndExcludeId(String moduleId , String excludeId) {
		List<Privilege> privileges = privilegeDAO.getListWithModuleIdAndExcludeId(moduleId, excludeId);
		List<TreeNode> nodes =new ArrayList<TreeNode>();
		for (Privilege privilege : privileges) {
			TreeNode node = new TreeNode();
			node.setId(privilege.getId());
			node.setName(privilege.getName());
			if(privilege.isExistsParent()){
				node.setParentId(privilege.getParentId());
			}
//				node.setIcon("privilegeIcon");
			nodes.add(node);
		}
		return TreeUtil.buildTreeByParent(nodes);
	}

	private String buildTreeJson(List<Privilege> privileges) {
		List<Module> moduleList=moduleService.getListWithDeployed();
		List<TreeNode> nodes =new ArrayList<TreeNode>();
		TreeNode root = new TreeNode();
		root.setId("-1");
		root.setName("系统权限");
		root.setNocheck(true);
//		nodes.add(root);
		for(Module module:moduleList){
			TreeNode moduleNode = new TreeNode();
			moduleNode.setId(module.getId());
			moduleNode.setName(module.getName());
			moduleNode.setNocheck(true);
//			moduleNode.setParentId(root.getId());
//			moduleNode.setIcon("moduleIcon");
			nodes.add(moduleNode);
		}
		for (Privilege privilege : privileges) {
			TreeNode node = new TreeNode();
			node.setId(privilege.getId());
			node.setName(privilege.getName());
			if(privilege.isExistsParent()){
				node.setParentId(privilege.getParentId());
			}else{
				node.setParentId(privilege.getModuleId());
			}
//				node.setIcon("privilegeIcon");
			nodes.add(node);
		}
		return TreeUtil.buildTreeByParent(nodes);
	}
	
	public void delete(String id) {
		privilegeDAO.delete(id);
	}
	
	public boolean isExistsName(String name,final String excludeName) {
		Privilege model = privilegeDAO.getWithNameAndExclude(name, excludeName);
		if (model != null) {
			return true;
		} 
		return false;
	}
	
	public boolean isExistsCode(String code,final String excludeCode) {
		Privilege model = privilegeDAO.getWithCodeAndExclude(code, excludeCode);
		if (model != null) {
			return true;
		} 
		return false;
	}
	
	public PrivilegeTableVO getPrivilegeTableVO(){
		List<Module> modules = moduleService.getListWithDeployed();
		List<Privilege> privileges = privilegeDAO.getAll();
		return new PrivilegeTableVO(modules,privileges);
	}
	
	public String getPrivilegeTableVOJson(){
		return JSONObject.toJSONString(getPrivilegeTableVO());
	}
	
	public List<Privilege> getUserAllPrivileges(String userId){
		return privilegeDAO.getUserAllPrivileges(userId);
	}
	
	public List<Privilege> getUserRolePrivileges(String userId){
		return privilegeDAO.getUserRolePrivileges(userId);
	}
	
	@Autowired
	private PrivilegeDAO privilegeDAO;
	@Autowired
	private ModuleService moduleService;
}


