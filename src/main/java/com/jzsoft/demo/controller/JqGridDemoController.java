package com.jzsoft.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzsoft.demo.model.TBUser;
import com.jzsoft.demo.service.TBUserService;
import com.jzsoft.platform.component.jqGrid.controller.JqGridController;
import com.jzsoft.platform.core.dao.Page;
import com.jzsoft.platform.core.web.argument.annotation.FormModel;

@Controller
@RequestMapping("/dm/jqGrid")
public class JqGridDemoController extends JqGridController<TBUser> {
	final static Logger logger = LoggerFactory.getLogger(JqGridDemoController.class);
	@Autowired
	private TBUserService userService;
	@RequestMapping("/demo1")
	@ResponseBody
	public String demo1(@FormModel("page") Page<TBUser> page) {
		//return this.refreshDataTable(userService.getPageWithDemo(page).getList());
		return this.refreshDataTable(userService.getPageWithDemo(page));

	}

	@RequestMapping("/demo2")
	@ResponseBody
	public String demo2() {
		//return this.refreshDataTable(userService.getPageWithDemo(page).getList());
		return this.refreshDataTable(userService.getListSimple());

	}
	
	@RequestMapping("/ajaxDemo1/{type}")
	public String ajaxDemo1(@FormModel("page") Page<TBUser> page,@PathVariable String type) {
		this.getRequest().setAttribute("pageObj", userService.getPageWithDemo(page));
		this.getRequest().setAttribute("type", type);
		return "/demo/component/jqGrid/ajaxDemo1";
	}
	

	
}
