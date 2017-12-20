package com.jzsoft.demo3.dao;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.jzsoft.platform.core.dao.BaseDAO;
import com.jzsoft.demo3.dao.mapper.DmText2Mapper;
import com.jzsoft.demo3.entity.DmText2;

/**
 * 示例功能2DAO
 *
 * @author 小强
 * @version 2017-02-07
 */
@Repository
public class DmText2DAO extends BaseDAO<DmText2, String> {
	
	/** 示例功能2mapper接口*/
	@Resource
	private DmText2Mapper dmText2Mapper;
	
	@PostConstruct
	public void init() {
		super.setNameSpace(DmText2Mapper.class.getName());
	}
	
}