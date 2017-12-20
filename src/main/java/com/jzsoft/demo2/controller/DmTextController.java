package com.jzsoft.demo2.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang.StringUtils;
import com.jzsoft.platform.core.web.controller.SpringController;
import com.jzsoft.platform.core.dao.Page;
import com.jzsoft.demo2.entity.DmText;
import com.jzsoft.demo2.service.DmTextService;

/**
 * 示例功能Controller
 *
 * @author 小哈
 * @version 2017-01-23
 */
@Controller
@RequestMapping("/demo2/dmText")
public class DmTextController extends SpringController {

	/** 示例功能Service接口*/
	@Resource
	private DmTextService dmTextService;

	@RequestMapping("/index")
	public String index() {
		return "/demo/module/demo2/dmText-index";
	}
	
	/**
	 * 示例功能列表
	 */
	@RequestMapping("/list")
	public String list(Model model,@ModelAttribute DmText entity) {
		model.addAttribute("list", dmTextService.getList(entity));
		return "/demo/module/demo2/dmText-list";
	}

	/**
	 * 跳转至示例功能新增或修改页面
	 */
	@RequestMapping("/input")
	public String input(Model model , String id) {
		DmText entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = dmTextService.get(id);
		}else{
			entity = new DmText();
		}
		model.addAttribute("entity", entity);
		return "/demo/module/demo2/dmText-input";
	}

	/**
	 * 示例功能修改或保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public void save(@ModelAttribute DmText entity,@RequestParam(value="upFiles",required=true)MultipartFile[]upFiles) {
		if(!beanValidatorForJson(entity))return;
		if (StringUtils.isBlank(entity.getId())) {
			dmTextService.save(entity);
		} else {
			dmTextService.update(entity);
		}
		this.print(entity.getId());
	}

	/**
	 * 示例功能删除
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String id) {
		dmTextService.delete(id);
	}
	
	
}