package com.jzsoft.platform.module.userauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jzsoft.platform.core.web.controller.SpringController;
import com.jzsoft.platform.module.user.service.SysUserService;

@Controller
@RequestMapping("/sys/userauth")
public class UserAuthController extends SpringController {

	@Autowired
	private SysUserService userService;
	
	@RequestMapping("/index")
	public String index() {
		return "/platform/module/userauth/userauth-index";
	}
	
}
