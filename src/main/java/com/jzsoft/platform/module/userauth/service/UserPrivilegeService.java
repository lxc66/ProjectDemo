package com.jzsoft.platform.module.userauth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jzsoft.platform.module.module.model.Module;
import com.jzsoft.platform.module.user.model.SysUser;
import com.jzsoft.platform.module.userauth.dao.UserPrivilegeDAO;
import com.jzsoft.platform.module.userauth.model.Privilege;
import com.jzsoft.platform.module.userauth.model.UserPrivilege;
import com.jzsoft.platform.util.tree.TreeNode;
import com.jzsoft.platform.util.tree.TreeUtil;


/**
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class UserPrivilegeService {


	public List<UserPrivilege> getListWithUserId(String userId){
		return userPrivilegeDAO.getListWithUserId(userId);
	} 
	
	public List<UserPrivilege> getListWithPrivilegeId(String privilegeId){
		return userPrivilegeDAO.getListWithPrivilegeId(privilegeId);
	} 
	
	
	public List<Privilege> getPrivilegesWithUserId(String userId){
		return userPrivilegeDAO.getPrivilegesWithUserId(userId);
	} 
	
	public List<SysUser> getUsersWithPrivilegeId(String privilegeId){
		return userPrivilegeDAO.getUsersWithPrivilegeId(privilegeId);
	} 
	
	public void updateUserRole(String userId , List<String> privilegeIdList){
		List<UserPrivilege> userPrivileges = getListWithUserId(userId);
		Map<String,String> userPrivilegeMap = new HashMap<String, String>(0);
		for(UserPrivilege userPrivilege : userPrivileges){
			userPrivilegeMap.put(userPrivilege.getPrivilegeId(), userPrivilege.getId());
		}
		List<String> oldPrivilegeIds = buildPrivilegeIds(userPrivileges);
		List<String> privilegeWithAdd = new ArrayList<String>();
		List<String> userPrivilegeWithDel = new ArrayList<String>();
		for(String roleId : privilegeIdList){
			if(!oldPrivilegeIds.contains(roleId)){
				privilegeWithAdd.add(roleId);
			}
		}
		
		for(String roleId : oldPrivilegeIds){
			if(!privilegeIdList.contains(roleId)){
				userPrivilegeWithDel.add(userPrivilegeMap.get(roleId));
			}
		}
		userPrivilegeDAO.saveUserPrivilege(userId, privilegeWithAdd);
		userPrivilegeDAO.deleteUserPrivilege(userPrivilegeWithDel);
	}
	
	public List<String> getPrivilegeIdsWithUserId(String userId){
		List<UserPrivilege> userPrivileges = getListWithUserId(userId);
		return buildPrivilegeIds(userPrivileges);
	}
	
	public void deleteWithUserId(String userId){
		userPrivilegeDAO.deleteWithUserId(userId);
	}
	
	public void deleteWithPrivilegeId(String privilegeId){
		userPrivilegeDAO.deleteWithPrivilegeId(privilegeId);
	}
	
	private List<String> buildPrivilegeIds(List<UserPrivilege> userPrivileges) {
		List<String> ids = new ArrayList<String>();
		for(UserPrivilege userPrivilege : userPrivileges){
			ids.add(userPrivilege.getPrivilegeId());
		}
		return ids;
	}
	
	public void saveUserPriveleges(SysUser model){
		this.updateUserRole(model.getId(), model.getPrivilegeIdList());
	}
	
	public String getPrivelegesTreeJsonWithUserId(String userId){
		List<Privilege> privileges = this.getPrivilegesWithUserId(userId);
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
	
	@Autowired
	private UserPrivilegeDAO userPrivilegeDAO;
}


