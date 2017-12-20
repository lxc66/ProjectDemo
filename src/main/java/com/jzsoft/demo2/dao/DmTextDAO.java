package com.jzsoft.demo2.dao;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.jzsoft.platform.core.dao.BaseDAO;
import com.jzsoft.demo2.dao.mapper.DmTextMapper;
import com.jzsoft.demo2.entity.DmText;

/**
 * 示例功能DAO
 *
 * @author 小哈
 * @version 2017-01-23
 */
@Repository
public class DmTextDAO extends BaseDAO<DmText, String> {
	
	/** 示例功能mapper接口*/
	@Resource
	private DmTextMapper dmTextMapper;
	
	@PostConstruct
	public void init() {
		super.setNameSpace(DmTextMapper.class.getName());
	}
	
}