package com.jzsoft.platform.module.user.external.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jzsoft.platform.core.shiro.helper.PasswordHelper;
import com.jzsoft.platform.module.user.dao.SysUserDAO;
import com.jzsoft.platform.module.user.external.ISysUserServicve;
import com.jzsoft.platform.module.user.model.SysUser;
import com.jzsoft.platform.module.user.service.SysUserService;


@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class SysUserServiceImpl implements ISysUserServicve{

	@Override
	public void save(SysUser model) {
		userService.save(model);
	}

	@Override
	public void update(SysUser model) {
		userService.update(model);
	}

	@Override
	public void delete(String id) {
		userService.delete(id);
	}

	@Override
	public void enabled(String id) {
		userService.enabled(id);
	}

	@Override
	public void forbidden(String id) {
		userService.forbidden(id);
	}
	
	@Override
	public void resetPassword(String id, String newPassword) {
		String password = PasswordHelper.encode(newPassword);
		userDAO.updatePassword(id, password);
	}
	
	@Autowired
	private SysUserDAO userDAO;
	@Autowired
	private SysUserService userService;

}


