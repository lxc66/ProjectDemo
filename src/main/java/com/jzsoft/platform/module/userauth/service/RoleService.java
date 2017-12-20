package com.jzsoft.platform.module.userauth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.jzsoft.platform.module.dictionary.model.DictionaryValue;
import com.jzsoft.platform.module.dictionary.service.DictionaryValueService;
import com.jzsoft.platform.module.module.model.Module;
import com.jzsoft.platform.module.module.service.ModuleService;
import com.jzsoft.platform.module.userauth.dao.RoleDAO;
import com.jzsoft.platform.module.userauth.model.Privilege;
import com.jzsoft.platform.module.userauth.model.Role;
import com.jzsoft.platform.module.userauth.vo.RoleTypeVO;
import com.jzsoft.platform.util.tree.TreeNode;
import com.jzsoft.platform.util.tree.TreeUtil;


/**
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class RoleService {

	public Role get(String id){
		return roleDAO.get(id);
	}
	
	public List<Role> getAll() {
		return roleDAO.getAll();
	}
	
	@SuppressWarnings("unused")
	public String getRolePrivilegesTreeJson(String roleId){
		//提前查询出所有已部署模块，为下面构建数据做Session级缓存
//		List<Module> moduleList=moduleService.getListWithDeployed();
		List<Privilege> privileges = rolePrivilegeService.getPrivilegeWithRoleId(roleId);
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		List<String> temp = new ArrayList<String>();
		for (Privilege privilege : privileges){
			TreeNode node = new TreeNode();
			node.setId(privilege.getId());
			node.setName(privilege.getName());
			Module module = privilege.getModule();
			if (module != null){
				if (!temp.contains(module.getId())){
					temp.add(module.getId());
					TreeNode parent = new TreeNode();
					parent.setId(module.getId());
					parent.setName(module.getName());
					parent.setIsParent(true);
					nodes.add(parent);
				}
				if(privilege.isExistsParent()){
					node.setParentId(privilege.getParentId());
				}else{
					node.setParentId(module.getId());
				}
			}
			nodes.add(node);
		}
		return TreeUtil.buildTreeByParent(nodes);
	}
	
	public void save(Role model) {
		int num = roleDAO.getMaxOrderNumWithType(model.getType());
		model.setNum(num+1);
		model.setSystemFlag(Role.POSITION_SYSTEMFLAG_NO);
		roleDAO.save(model);
		rolePrivilegeService.updateRolePrivilege(model.getId(), model.getPrivilegeIdList());
	}
	
	public void update(Role model) {
		roleDAO.update(model);
		updateRolePrivilege(model);
	}
	
	public void updateRolePrivilege(Role model){
		rolePrivilegeService.updateRolePrivilege(model.getId(), model.getPrivilegeIdList());
	}
	
	/**
	 * 获取系统职务列表JSON串（相同职务类型）
	 */
	public String getListJsonWithType(String type){
		List<Role> roles = roleDAO.getListWithType(type);
		return buildListJson(roles);
	}
	
	public String buildListJson(List<Role> roles){
		if(roles==null)return"";
		List<Map<String, String>> list= new ArrayList<Map<String,String>>(0);
		for (Role role : roles) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", role.getId());
			map.put("name", role.getName());
			list.add(map);
		}
		return JSONArray.toJSONString(list);
	}
	
	public void forbidden(String id) {
		roleDAO.updateEnableFlag(id, Role.DISABLED);
	}

	public void enabled(String id) {
		roleDAO.updateEnableFlag(id, Role.ENABLE);
	}
	
	public void delete(String id) {
		rolePrivilegeService.deleteWithRoleId(id);
		roleDAO.delete(id);
	}
	
	public boolean isExistsName(String name,final String excludeName) {
		Role model = roleDAO.getWithNameAndExclude(name, excludeName);
		if (model != null) {
			return true;
		} 
		return false;
	}
	
	public boolean isExistsCode(String code,final String excludeCode) {
		Role model = roleDAO.getWithCodeAndExclude(code, excludeCode);
		if (model != null) {
			return true;
		} 
		return false;
	}
	
	public List<RoleTypeVO> getRoleTypes(){
		List<DictionaryValue> dictValues = dictionaryValueService.getListWithDictCode(Role.ROLE_TYPE_DICT_CODE);
		return RoleTypeVO.convert(dictValues);
	}
	
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private PrivilegeService privilegeService;
	@Autowired
	private RolePrivilegeService rolePrivilegeService;
	@Autowired
	private DictionaryValueService dictionaryValueService;
}


