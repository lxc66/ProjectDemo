package com.jzsoft.platform.component.jqGrid.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.jzsoft.platform.component.abstractUC.bean.Component;
import com.jzsoft.platform.component.abstractUC.tag.AbstractUCTag;
import com.jzsoft.platform.component.jqGrid.bean.JqGrid;

public class JqGridTag extends AbstractUCTag {

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

	@Override
	public Component getBean(HttpServletRequest request, HttpServletResponse response) {
		return new JqGrid(request, response);
	}

	protected void populateParams() {
		super.populateParams();
		JqGrid component_ = (JqGrid) component;
		if (StringUtils.isNotEmpty(id)) {
			component_.setId(id);
		} else {
			component_.setId("");
		}
		if (StringUtils.isNotBlank(pager)) {

		}

	}
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
