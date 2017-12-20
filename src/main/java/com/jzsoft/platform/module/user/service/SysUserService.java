package com.jzsoft.platform.module.user.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jzsoft.platform.core.dao.Page;
import com.jzsoft.platform.core.shiro.helper.PasswordHelper;
import com.jzsoft.platform.module.user.dao.SysUserDAO;
import com.jzsoft.platform.module.user.model.SysUser;


/**
 * @Description : ()
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class SysUserService {

	public void save(SysUser model) {
		model.setEnableFlag(SysUser.ENABLE);
		
		model.setPassword(PasswordHelper.encode(SysUser.DEFAULT_PASSWORD));
		userDAO.save(model);
	}
	
	public void update(SysUser model) {
		SysUser user=this.get(model.getId());
		model.setPassword(user.getPassword());
		model.setHeadPortraitBigId(user.getHeadPortraitBigId());
		model.setHeadPortraitSmallId(user.getHeadPortraitSmallId());
		model.setHeadPortraitBigUrl(user.getHeadPortraitBigUrl());
		model.setHeadPortraitSmallUrl(user.getHeadPortraitSmallUrl());
		userDAO.update(model);
	}
	
	public SysUser get(String id){
		return userDAO.get(id);
	}
	
	public void delete(String id) {
		userDAO.delete(id);
	}
	
	public SysUser selectOne(String id){
		return userDAO.get(id);
	}

	public Page<SysUser> getPage(SysUser model, Page<SysUser> page, String sord,String sidx) {
		page.addParams("type", model.getType());
		page.addParams("sord", sord);
		page.addParams("sidx", sidx);
		return userDAO.getPage(page);
	}
	
	/**
	 * 用户登陆名是否存在
	 */
	public boolean isExistsLoginName(String loginName,final String excludeLoginName) {
		SysUser user = userDAO.getWithLoginNameAndExclude(loginName, excludeLoginName);
		if (user != null) {
			return true;
		} 
		return false;
	}
	
	public void forbidden(String id) {
		userDAO.updateEnableFlag(id, SysUser.DISABLED);
	}

	public void enabled(String id) {
		userDAO.updateEnableFlag(id, SysUser.ENABLE);
	}
	
	public void resetPassword(SysUser model){
		String password = PasswordHelper.encode(model.getPassword());
		userDAO.updatePassword(model.getId(), password);
	}
	
	public SysUser getUserWithLoginName(String loginName) {
		if (StringUtils.isBlank(loginName)) {
			return null;
		}
		return userDAO.getWithLoginNameAndExclude(loginName, null);
	}
	
	public boolean checkOriginalPassword(String userId , String password){
		if(StringUtils.isBlank(password)){
			return false;
		}
		SysUser sysUser = this.get(userId);
		return PasswordHelper.encode(password).equals(sysUser.getPassword());
	}
	@Autowired
	private SysUserDAO userDAO;
}


