package com.jzsoft.platform.component.jqGrid.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jzsoft.platform.component.abstractUC.bean.AbstractUCBean;

public class JqGrid extends AbstractUCBean {
	final public static String TEMPLATE = "jqGrid";
	//	var isToolbar = me.attr("toolbar") == "true";
	//	var isAutowidth = me.attr("autowidth") != "true";
	//	var isShrinkToFit = me.attr("shrinkToFit") == "true";
	//	var pagerId = me.attr("pager");
	//	var scrollNum = me.attr("scroll");
	//	var dataroot = me.attr("dataroot") || "gridModel";
	//	var tableWidth = me.attr("tableWidth");
	//	var tableHeight = me.attr("tableHeight");
	//	var dataEditUrl = me.attr("dataEditUrl");
	//	var isRownumbers = me.attr("rownumbers") != "false";
	//	var userPageNo = me.attr("userPageNo");
	//	var userRowNum = me.attr("userRowNum");
	private String pager;
	private String multiselect;
	private String title;
	private String toolbar;
	private String autowidth;
	private String shrinkToFit;
	private String scroll;
	private String dataroot;
	private String tableWidth;
	private String tableHeight;
	private String dataEditUrl;
	private String rownumbers;
	private String userPageNo;
	private String userRowNum;
	public JqGrid(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}


	@Override
	protected String getTemplate() {
		return TEMPLATE;
	}

	//////seter&&geter///////

	public String getPager() {
		return pager;
	}

	public void setPager(String pager) {
		this.pager = pager;
	}

	public String getMultiselect() {
		return multiselect;
	}

	public void setMultiselect(String multiselect) {
		this.multiselect = multiselect;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getToolbar() {
		return toolbar;
	}

	public void setToolbar(String toolbar) {
		this.toolbar = toolbar;
	}

	public String getAutowidth() {
		return autowidth;
	}

	public void setAutowidth(String autowidth) {
		this.autowidth = autowidth;
	}

	public String getShrinkToFit() {
		return shrinkToFit;
	}

	public void setShrinkToFit(String shrinkToFit) {
		this.shrinkToFit = shrinkToFit;
	}

	public String getScroll() {
		return scroll;
	}

	public void setScroll(String scroll) {
		this.scroll = scroll;
	}

	public String getDataroot() {
		return dataroot;
	}

	public void setDataroot(String dataroot) {
		this.dataroot = dataroot;
	}

	public String getTableWidth() {
		return tableWidth;
	}

	public void setTableWidth(String tableWidth) {
		this.tableWidth = tableWidth;
	}

	public String getTableHeight() {
		return tableHeight;
	}

	public void setTableHeight(String tableHeight) {
		this.tableHeight = tableHeight;
	}

	public String getDataEditUrl() {
		return dataEditUrl;
	}

	public void setDataEditUrl(String dataEditUrl) {
		this.dataEditUrl = dataEditUrl;
	}

	public String getRownumbers() {
		return rownumbers;
	}

	public void setRownumbers(String rownumbers) {
		this.rownumbers = rownumbers;
	}

	public String getUserPageNo() {
		return userPageNo;
	}

	public void setUserPageNo(String userPageNo) {
		this.userPageNo = userPageNo;
	}

	public String getUserRowNum() {
		return userRowNum;
	}

	public void setUserRowNum(String userRowNum) {
		this.userRowNum = userRowNum;
	}

}
