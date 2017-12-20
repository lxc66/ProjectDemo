package com.jzsoft.platform.module.userauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jzsoft.platform.module.userauth.dao.PrivilegeOperationDAO;


/**
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class PrivilegeOperationService {

	@Autowired
	private PrivilegeOperationDAO privilegeOperationDAO;
}


