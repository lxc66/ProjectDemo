package com.jzsoft.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jzsoft.demo.model.TBUser;
import com.jzsoft.platform.core.dao.BaseDAO;
import com.jzsoft.platform.core.dao.Page;

@Repository
public class TBUserDAO extends BaseDAO<TBUser, String>{
	public Page<TBUser> getPageWithDemo(List<TBUser> list,Page<TBUser> page){
		return this.getPage(list, page);
	}
}
