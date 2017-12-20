package com.jzsoft.demo3.service;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jzsoft.platform.core.dao.Page;
import com.jzsoft.demo3.dao.DmText2DAO;
import com.jzsoft.demo3.entity.DmText2;
import com.jzsoft.demo3.service.DmText2Service;

/**
 * 示例功能2Service
 *
 * @author 小强
 * @version 2017-02-07
 */
@Service
@Transactional
public class DmText2Service {

	/** 示例功能2DAO接口*/
	@Resource
	private DmText2DAO dmText2DAO;
	
	/**
	 * 查询分页示例功能2数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	public Page<DmText2> getPage(Page<DmText2> page){
		return dmText2DAO.getPage(page);
	}

	/**
	 * 通过id获取单条示例功能2数据
	 * 
	 * @param id
	 * @return
	 */
	public DmText2 get(String id){
		return dmText2DAO.get(id);
	}

	/**
	 * 通过id删除示例功能2数据
	 * 
	 * @param id
	 */
	public void delete(String id){
		dmText2DAO.delete(id);
	}

	/**
	 * 修改示例功能2数据
	 * 
	 * @param dmText2
	 */
	public void update(DmText2 dmText2){
		dmText2DAO.update(dmText2);
	}
	/**
	 * 保存示例功能2数据
	 * 
	 * @param dmText2
	 */
	public void save(DmText2 dmText2){
		dmText2DAO.save(dmText2);
	}
	/**
	 * 获取所有示例功能2数据
	 * 
	 * @return
	 */
	public List<DmText2> getAll(){
		return dmText2DAO.getAll();
	}
	
	/**
	 * 获取示例功能2数据列表
	 * 
	 * @return
	 */
	public List<DmText2> getList(DmText2 entity){
		return dmText2DAO.getList(entity);
	}
}