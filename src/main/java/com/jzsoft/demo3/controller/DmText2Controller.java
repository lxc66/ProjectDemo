package com.jzsoft.demo3.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.lang.StringUtils;
import com.jzsoft.platform.core.dao.Page;
import com.jzsoft.platform.core.web.controller.SpringController;
import com.jzsoft.demo3.entity.DmText2;
import com.jzsoft.demo3.service.DmText2Service;

/**
 * 示例功能2Controller
 *
 * @author 小强
 * @version 2017-02-07
 */
@Controller
@RequestMapping("/demo3/dmText2")
public class DmText2Controller extends SpringController {

	/** 示例功能2Service接口*/
	@Resource
	private DmText2Service dmText2Service;

	@RequestMapping("/index")
	public String index() {
		return "/demo/module/demo3/dmText2-index";
	}
	
	/**
	 * 示例功能2列表
	 */
	@RequestMapping("/list")
	public String list(Model model,
			@RequestParam(required = false, value = "name", defaultValue = "") String name,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo) {

		Page<DmText2> pager = new Page<>();
		pager.setPageNo(pageNo);
		//分几页
//		pager.setPageSize(1);
		DmText2 dmText2 = new DmText2();
		pager.setCondition(dmText2);
		model.addAttribute("list", dmText2Service.getPage(pager).getResults());
		model.addAttribute("pager", pager);
		return "/demo/module/demo3/dmText2-list";
	}

	/**
	 * 跳转至示例功能2新增或修改页面
	 */
	@RequestMapping("/input")
	public String input(Model model , String id) {
		DmText2 entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = dmText2Service.get(id);
		}else{
			entity = new DmText2();
		}
		model.addAttribute("entity", entity);
		return "/demo/module/demo3/dmText2-input";
	}

	/**
	 * 示例功能2修改或保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public void save(@ModelAttribute DmText2 entity) {
		if(!beanValidatorForJson(entity))return;
		if (StringUtils.isBlank(entity.getId())) {
			dmText2Service.save(entity);
		} else {
			dmText2Service.update(entity);
		}
		this.print(entity.getId());
	}

	/**
	 * 示例功能2删除
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String id) {
		dmText2Service.delete(id);
	}
	
	
}