package com.jzsoft.platform.core.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * MyBatis的Dao基类
 */
@Repository
public class BaseDAO<T, PK extends Serializable> extends SqlSessionDaoSupport {
	private final int BATCH_DEAL_NUM = 100;
	protected static final String SQL_ID_SAVE = "save";
	protected static final String SQL_ID_UPDATE = "update";
	protected static final String SQL_ID_DELETE = "delete";
	protected static final String SQL_ID_GET = "get";
	protected static final String SQL_ID_GETALL = "getAll";
	protected static final String SQL_ID_GETLIST = "getList";
	protected static final String SQL_ID_GETPAGE = "getPage";
	protected static final String SQL_ID_GET_WITH_PROPERTY = "getWithProperty";
	
	protected static final String SQL_NickName_GET = "selectOne";
	
	private String NAMESPACE;
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@PostConstruct
	public void SqlSessionTemplate() {
		super.setSqlSessionTemplate(sqlSessionTemplate);
//		super.setSqlSessionFactory(sqlSessionTemplate.getSqlSessionFactory());
		init();
	}

	public SqlSession getSqlSession() {
//		return SqlSessionUtils.getSqlSession(sqlSessionTemplate.getSqlSessionFactory());
		return super.getSqlSession();
	}

	private void init() {
		this.NAMESPACE = this.getClass().getName();
	}
	
	public void setNameSpace(String namespace){
		this.NAMESPACE = namespace;
	}
	

	public int saveOrUpdate(T model){
		if (StringUtils.isEmpty((String) ModelHelper.getIdValue(model))) {
			return save(model);
		}else{
			return update(model);
		}
	}
	
	public int save(T model) {
		if (StringUtils.isEmpty((String) ModelHelper.getIdValue(model))) {
			ModelHelper.setPK(model);
		}
		ModelHelper.setDefaultWithSave(model);
		return save(SQL_ID_SAVE, model);
	}

	public int update(T model) {
		ModelHelper.setDefaultWithUpdate(model);
		return update(SQL_ID_UPDATE, model);
	}

	public int delete(PK id) {
		return delete(SQL_ID_DELETE, id);
	}

	@SuppressWarnings("unchecked")
	public T get(PK id) {
		return (T)get(SQL_ID_GET, id);
	}
	
	@SuppressWarnings("unchecked")
	public T selectOne(PK id) {
		return (T)selectOne(SQL_NickName_GET, id);
	}

	public List<T> getAll() {
		return getList(SQL_ID_GETALL);
	}
	
	public List<T> getList(T entity) {
		return getList(SQL_ID_GETLIST,entity);
	}

	public Page<T> getPage(Page<T> page) {
		return getPage(SQL_ID_GETPAGE, page);
	}

	private String buildSqlID(String sqlID) {
		return NAMESPACE + "." + sqlID;
	}

	protected SqlSession getBatchSqlSession() {
		return sqlSessionTemplate;
	}

	protected int save(String sqlID, Object object) {
		return getSqlSession().insert(buildSqlID(sqlID), object);
	}

	protected int update(String sqlID, Object object) {
		return getSqlSession().update(buildSqlID(sqlID), object);
	}
	
	protected int delete(String sqlID, Object object) {
		return getSqlSession().delete(buildSqlID(sqlID), object);
	}

	@SuppressWarnings("unchecked")
	protected <X> X get(String sqlID, Serializable id) {
		return (X) getSqlSession().selectOne(buildSqlID(sqlID), id);
	}
	
	@SuppressWarnings("unchecked")
	protected <X> X selectOne(String sqlID, Serializable id) {
		return (X) getSqlSession().selectOne(buildSqlID(sqlID), id);
	}
	
	@SuppressWarnings("unchecked")
	protected <X> X get(String sqlID, Object obj) {
		return (X) getSqlSession().selectOne(buildSqlID(sqlID), obj);
	}

	protected <X> List<X> getList(String sqlID) {
		return getSqlSession().selectList(buildSqlID(sqlID));
	}

	protected <X> List<X> getList(String sqlID, Object object) {
		return getSqlSession().selectList(buildSqlID(sqlID), object);
	}

	protected <X> Page<X> getPage(String sqlID, Page<X> page) {
		List<X> list = getList(sqlID, page);
		page.setResults(list);
		return page;
	}
	
	protected <X> Page<X> getPage(List<X> list, Page<X> page) {
		page.setTotalRecord(list.size());
		if (list.size() >= (page.getStartOfPage() + page.getPageSize())) {
			page.setResults(list.subList(page.getStartOfPage(), page.getStartOfPage() + page.getPageSize()));
		} else {
			page.setResults(list.subList(page.getStartOfPage(), list.size()));
		}
		return page;
	}
	
	public <X> X getWithProperty(String property, String value, String excludeValue) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("property", property);
		params.put("value", value);
		if(StringUtils.isNotBlank(excludeValue)){
			params.put("excludeValue", excludeValue);
		}
		return this.get(SQL_ID_GET_WITH_PROPERTY, params);
	}
	
	public boolean isExistsProperty(String property, String value, String excludeValue) {
		Object model = this.getWithProperty(property, value,excludeValue);
		if (model != null) {
			return true;
		} 
		return false;
	}
	
	protected int batchInsert(String sqlID, List<?> list) {
		SqlSession batchSession = getBatchSqlSession();
		int i = 0;
		String sqlIdFullName = buildSqlID(sqlID);
		List<Object> batchList = new ArrayList<Object>();
		for (int cnt = list.size(); i < cnt; i++) {
			Object model = list.get(i);
			if (StringUtils.isEmpty((String) ModelHelper.getIdValue(model))) {
				ModelHelper.setPK(model);
			}
			batchList.add(model);
			if ((i + 1) % BATCH_DEAL_NUM == 0) {
				batchSession.insert(sqlIdFullName, batchList);
				batchSession.flushStatements();
				batchList.clear();
			}
		}
		if(batchList.size()>0){
			batchSession.insert(sqlIdFullName, batchList);
		}
		batchSession.flushStatements();
		return i;
	}

	protected int batchUpdate(String sqlID, List<?> list) {
		SqlSession batchSession = getBatchSqlSession();
		int i = 0;
		String sqlIdFullName = buildSqlID(sqlID);
		for (int cnt = list.size(); i < cnt; i++) {
			Object model = list.get(i);
			ModelHelper.setDefaultWithUpdate(model);
			batchSession.update(sqlIdFullName, model);
			if ((i + 1) % BATCH_DEAL_NUM == 0) {
				batchSession.flushStatements();
			}
		}
		batchSession.flushStatements();
		return i;
	}

	protected int batchDelete(String sqlID, List<?> list) {
		SqlSession batchSession = getBatchSqlSession();
		int i = 0;
		String sqlIdFullName = buildSqlID(sqlID);
		for (int cnt = list.size(); i < cnt; i++) {
			batchSession.delete(sqlIdFullName, list.get(i));
			if ((i + 1) % BATCH_DEAL_NUM == 0) {
				batchSession.flushStatements();
			}
		}
		batchSession.flushStatements();
		return i;
	}
}
