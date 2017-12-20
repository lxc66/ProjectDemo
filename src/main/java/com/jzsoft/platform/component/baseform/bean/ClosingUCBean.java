package com.jzsoft.platform.component.baseform.bean;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jzsoft.platform.component.abstractUC.bean.AbstractUCBean;

public abstract class ClosingUCBean extends AbstractUCBean {

	public ClosingUCBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public abstract String getOpenTemplate();

	public boolean start(Writer writer) {
		boolean result = super.start(writer);
		try {
			mergeTemplate(writer, getOpenTemplate());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
