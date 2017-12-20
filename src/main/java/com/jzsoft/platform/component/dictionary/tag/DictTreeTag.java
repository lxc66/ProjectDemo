package com.jzsoft.platform.component.dictionary.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jzsoft.platform.component.abstractUC.bean.Component;
import com.jzsoft.platform.component.tree.bean.Tree;
import com.jzsoft.platform.component.tree.tag.TreeTag;
import com.jzsoft.platform.core.spring.SpringContextHolder;
import com.jzsoft.platform.module.dictionary.service.DictionaryValueService;

public class DictTreeTag extends TreeTag {
	private static final long serialVersionUID = 4319366742748453765L;
	private String dictCode;

	@Override
	public Component getBean(HttpServletRequest request, HttpServletResponse response) {
		return new Tree(request, response);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		Tree component_ = (Tree) component;
		DictionaryValueService dictValueService = SpringContextHolder.getBean(DictionaryValueService.class);
		String dictValueTreejson = dictValueService.getDictValueTreejsonWithDictcode(this.getDictCode());
		component_.setNodes(dictValueTreejson);
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}


}
