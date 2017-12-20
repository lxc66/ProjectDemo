package com.jzsoft.platform.component.dateSelect.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jzsoft.platform.component.abstractUC.bean.AbstractUCBean;

public class DateSelect extends AbstractUCBean {
	final public static String TEMPLATE = "dateSelect";
	private String id;
	private String name;
	private String minDate;
	private String maxDate;
	private String disabledDays;
	private String specialDays;
	private String styleClass;
	private String dateFmt;
	private String sid;
	private String eid;
	private String doubleCalendar;
	private String dateValue;
	private String other;
	private String required;
	private String placeholder;

	public DateSelect(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	@Override
	protected String getTemplate() {
		return TEMPLATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMinDate() {
		return minDate;
	}

	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}

	public String getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	public String getDisabledDays() {
		return disabledDays;
	}

	public void setDisabledDays(String disabledDays) {
		this.disabledDays = disabledDays;
	}

	public String getSpecialDays() {
		return specialDays;
	}

	public void setSpecialDays(String specialDays) {
		this.specialDays = specialDays;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getDateFmt() {
		return dateFmt;
	}

	public void setDateFmt(String dateFmt) {
		this.dateFmt = dateFmt;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getDoubleCalendar() {
		return doubleCalendar;
	}

	public void setDoubleCalendar(String doubleCalendar) {
		this.doubleCalendar = doubleCalendar;
	}

	public String getDateValue() {
		return dateValue;
	}

	public void setDateValue(String dateValue) {
		this.dateValue = dateValue;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

}
