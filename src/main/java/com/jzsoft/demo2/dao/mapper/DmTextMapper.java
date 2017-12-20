package com.jzsoft.demo2.dao.mapper;
import java.util.List;
import com.jzsoft.platform.core.mybatis.SqlMapper;
import com.jzsoft.platform.core.dao.Page;
import com.jzsoft.demo2.entity.DmText;

/**
 * 示例功能mapper接口
 * 
 * @author 小哈
 * @version 2017-01-23
 */
@SqlMapper
public interface DmTextMapper {

	/**
	 * 查询分页示例功能数据
	 * 
	 * @param page 分页对象
	 * @return
	 */
	Page<DmText> getPage(Page<DmText> page);

	/**
	 * 通过id获取单条示例功能数据
	 * 
	 * @param id
	 * @return
	 */
	DmText get(String id);

	/**
	 * 通过id删除示例功能数据
	 * 
	 * @param id
	 */
	void delete(String id);

	/**
	 * 修改示例功能数据
	 * 
	 * @param dmText
	 */
	void update(DmText dmText);

	/**
	 * 保存示例功能数据
	 * 
	 * @param dmText
	 */
	void save(DmText dmText);

	/**
	 * 获取所有示例功能数据
	 * 
	 * @return
	 */
	List<DmText> getAll();
	
	/**
	 * 获取示例功能数据列表
	 * 
	 * @return
	 */
	List<DmText> getList(DmText entity);

}