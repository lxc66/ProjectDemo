package com.jzsoft.platform.module.userauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jzsoft.platform.core.web.controller.SpringController;
import com.jzsoft.platform.module.userauth.service.OperationService;

@Controller
@RequestMapping("/sys/operation")
public class OperationController extends SpringController {

	@Autowired
	private OperationService operationService;
	
	
}
