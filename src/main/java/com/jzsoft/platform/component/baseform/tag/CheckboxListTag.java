package com.jzsoft.platform.component.baseform.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jzsoft.platform.component.abstractUC.bean.Component;
import com.jzsoft.platform.component.baseform.bean.CheckboxListBean;

public class CheckboxListTag extends AbstractListTag {

	private static final long serialVersionUID = -1231035864473929464L;

	@Override
	public Component getBean(HttpServletRequest request, HttpServletResponse response) {
		return new CheckboxListBean(request, response);
	}

}
