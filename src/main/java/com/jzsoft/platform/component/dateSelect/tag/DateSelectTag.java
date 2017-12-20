package com.jzsoft.platform.component.dateSelect.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.jzsoft.platform.component.abstractUC.bean.Component;
import com.jzsoft.platform.component.abstractUC.tag.AbstractUCTag;
import com.jzsoft.platform.component.dateSelect.bean.DateSelect;

public class DateSelectTag extends AbstractUCTag {

	private static final long serialVersionUID = 2241069931118844083L;

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

	@Override
	public Component getBean(HttpServletRequest req, HttpServletResponse res) {
		return new DateSelect(req, res);
	}

	protected void populateParams() {
		super.populateParams();
		DateSelect component_ = (DateSelect) component;
		if (StringUtils.isNotEmpty(id)) {
			component_.setId(id);
		} else {
			component_.setId("");
		}
		if (StringUtils.isNotEmpty(name)) {
			component_.setName(name);
		} else {
			component_.setName("");
		}
		if (StringUtils.isNotEmpty(minDate)) {
			component_.setMinDate(minDate);
		} else {
			component_.setMinDate("");
		}
		if (StringUtils.isNotEmpty(maxDate)) {
			component_.setMaxDate(maxDate);
		} else {
			component_.setMaxDate("");
		}
		if (StringUtils.isNotEmpty(disabledDays)) {
			component_.setDisabledDays(disabledDays);
		} else {
			component_.setDisabledDays("");
		}
		if (StringUtils.isNotEmpty(specialDays)) {
			component_.setSpecialDays(specialDays);
		} else {
			component_.setSpecialDays("");
		}
		if (StringUtils.isNotEmpty(styleClass)) {
			component_.setStyleClass(styleClass);
		} else {
			component_.setStyleClass("");
		}
		if (StringUtils.isNotEmpty(dateFmt)) {
			component_.setDateFmt(dateFmt);
		} else {
			component_.setDateFmt("");
		}
		if (StringUtils.isNotEmpty(sid)) {
			component_.setSid(sid);
		} else {
			component_.setSid("");
		}
		if (StringUtils.isNotEmpty(eid)) {
			component_.setEid(eid);
		} else {
			component_.setEid("");
		}
		if (StringUtils.isNotEmpty(doubleCalendar)) {
			component_.setDoubleCalendar(doubleCalendar);
		} else {
			component_.setDoubleCalendar("");
		}
		if (StringUtils.isNotEmpty(dateValue)) {
			component_.setDateValue(dateValue != null ? dateValue.trim() : null);
		} else {
			component_.setDateValue("");
		}
		if (StringUtils.isNotEmpty(other)) {
			component_.setOther(other);
		} else {
			component_.setOther("");
		}
		if (StringUtils.isNotEmpty(required)) {
			component_.setRequired(required);
		} else {
			component_.setRequired("");
		}
		if (StringUtils.isNotEmpty(placeholder)) {
			component_.setPlaceholder(placeholder);
		} else {
			component_.setPlaceholder("");
		}
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
