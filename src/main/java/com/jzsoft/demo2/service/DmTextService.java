package com.jzsoft.demo2.service;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jzsoft.platform.core.dao.Page;
import com.jzsoft.demo2.dao.DmTextDAO;
import com.jzsoft.demo2.entity.DmText;
import com.jzsoft.demo2.service.DmTextService;

/**
 * 示例功能Service
 *
 * @author 小哈
 * @version 2017-01-23
 */
@Service
@Transactional
public class DmTextService {

	/** 示例功能DAO接口*/
	@Resource
	private DmTextDAO dmTextDAO;
	
	/**
	 * 查询分页示例功能数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	public Page<DmText> getPage(Page<DmText> page){
		return dmTextDAO.getPage(page);
	}

	/**
	 * 通过id获取单条示例功能数据
	 * 
	 * @param id
	 * @return
	 */
	public DmText get(String id){
		return dmTextDAO.get(id);
	}

	/**
	 * 通过id删除示例功能数据
	 * 
	 * @param id
	 */
	public void delete(String id){
		dmTextDAO.delete(id);
	}

	/**
	 * 修改示例功能数据
	 * 
	 * @param dmText
	 */
	public void update(DmText dmText){
		dmTextDAO.update(dmText);
	}
	/**
	 * 保存示例功能数据
	 * 
	 * @param dmText
	 */
	public void save(DmText dmText){
		dmTextDAO.save(dmText);
	}
	/**
	 * 获取所有示例功能数据
	 * 
	 * @return
	 */
	public List<DmText> getAll(){
		return dmTextDAO.getAll();
	}
	
	/**
	 * 获取示例功能数据列表
	 * 
	 * @return
	 */
	public List<DmText> getList(DmText entity){
		return dmTextDAO.getList(entity);
	}
}