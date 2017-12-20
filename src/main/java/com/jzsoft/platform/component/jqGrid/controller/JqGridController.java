package com.jzsoft.platform.component.jqGrid.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jzsoft.platform.core.dao.Page;
import com.jzsoft.platform.core.web.controller.SpringController;
import com.jzsoft.platform.util.JsonUtil;

public abstract class JqGridController<T> extends SpringController {
	private static final long serialVersionUID = 2591256321398765677L;
	private static final String SORD_ASC = "asc";
	private static final String SORD_DESC = "desc";
	/************************************ jqGrid相关属性 ********************************************************************/
	/**
	 * gridModel:每页展现的数据对象
	 */
	private List<T> gridModel = new ArrayList<T>();
	/**
	 * 分页相关属性 rows:每页显示记录数 page:当前页码 total:分页总页数 record:总记录数
	 */
	private Integer rows = 0;
	private Integer page = 0;
	private Integer total = 0;
	private Long record = 0L;
	/**
	 * 表头排序相关属性 sord:升序、降序 sidx:排序字段
	 */
	private String sord;// 升序、降序
	private String sidx;// 属性名
	/**
	 * 查询相关属性 search：是否是查询请求 filters:查询字段+操作符+字段值的组合
	 */
	private boolean search;
	private String filters;
	private String[] delIds;//删除数据的id数组

	/*************************************** 构造jqGrid分页对象 ************************************************************************/
	/**
	 * 构建数据表格 param：分页对象
	 */
	public String refreshDataTable(Page<T> pageObj) {
		try {
			String urlparams = this.getRequest().getParameter("urlparams");
			String jsonString = JsonUtil.collection2Json(pageObj.getList(), processParams(urlparams));
			return "{\"gridModel\":" + jsonString + ",\"page\":" + pageObj.getPageNo() + ",\"record\":" + pageObj.getTotalRecord() + ",\"rows\":" + pageObj.getPageSize() + ",\"total\":"
					+ pageObj.getTotalPage() + "}";

		} catch (Exception e) {
			e.printStackTrace();
			//this.addActionError(e.getMessage());
		}
		return "";
	}

	/**
	 * 构建数据表格 param：数据列表
	 */
	public String refreshDataTable(List<T> dataList) {
		String jsonString = "";
		try {
			String urlparams = this.getRequest().getParameter("urlparams");
			jsonString = JsonUtil.collection2Json(dataList, processParams(urlparams));
		} catch (Exception e) {
			e.printStackTrace();
			//this.addActionError(e.getMessage());
		}
		return jsonString;
	}

	/**
	 * 处理表格传回的属性参数
	 */
	private List<String> processParams(String urlparams) {
		List<String> listParams = new ArrayList<String>();
		if (StringUtils.isNotEmpty(urlparams)) {
			String[] arr = urlparams.split(",");
			for (int i = 0; i < arr.length; i++) {
				if (StringUtils.isNotEmpty(arr[i])) {
					listParams.add(arr[i]);
				}

			}
		}
		return listParams;
	}

	/******************************************** getter和setter ***********************************************************************/

	public String getSord() {
		return sord;
	}

	public List<T> getGridModel() {
		return gridModel;
	}

	public void setGridModel(List<T> gridModel) {
		this.gridModel = gridModel;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Long getRecord() {
		return record;
	}

	public void setRecord(Long record) {
		this.record = record;
	}

	public void setSord(String sord) {
		this.sord = sord;
		if (StringUtils.isNotEmpty(this.sidx)) {
			//this.pageObj.addOrderProperty(sidx, getBooleanSord());
		}
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;

	}

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public boolean getBooleanSord() {
		if (StringUtils.isNotEmpty(this.sord)) {
			if (SORD_ASC.equals(this.sord)) {
				return true;
			} else if (SORD_DESC.equals(this.sord)) {
				return false;
			}
		}
		return false;
	}

	public String[] getDelIds() {
		String ids = this.getRequest().getParameter("ids");
		if (StringUtils.isNotEmpty(ids)) {
			delIds = ids.trim().split(" ");
		}
		return delIds;
	}

	public void setDelIds(String[] delIds) {
		this.delIds = delIds;
	}
}
