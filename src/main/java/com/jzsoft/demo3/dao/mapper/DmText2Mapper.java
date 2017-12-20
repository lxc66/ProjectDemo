package com.jzsoft.demo3.dao.mapper;
import java.util.List;
import com.jzsoft.platform.core.mybatis.SqlMapper;
import com.jzsoft.platform.core.dao.Page;
import com.jzsoft.demo3.entity.DmText2;

/**
 * 示例功能2mapper接口
 * 
 * @author 小强
 * @version 2017-02-07
 */
@SqlMapper
public interface DmText2Mapper {

	/**
	 * 查询分页示例功能2数据
	 * 
	 * @param page 分页对象
	 * @return
	 */
	Page<DmText2> getPage(Page<DmText2> page);

	/**
	 * 通过id获取单条示例功能2数据
	 * 
	 * @param id
	 * @return
	 */
	DmText2 get(String id);

	/**
	 * 通过id删除示例功能2数据
	 * 
	 * @param id
	 */
	void delete(String id);

	/**
	 * 修改示例功能2数据
	 * 
	 * @param dmText2
	 */
	void update(DmText2 dmText2);

	/**
	 * 保存示例功能2数据
	 * 
	 * @param dmText2
	 */
	void save(DmText2 dmText2);

	/**
	 * 获取所有示例功能2数据
	 * 
	 * @return
	 */
	List<DmText2> getAll();
	
	/**
	 * 获取示例功能2数据列表
	 * 
	 * @return
	 */
	List<DmText2> getList(DmText2 entity);

}