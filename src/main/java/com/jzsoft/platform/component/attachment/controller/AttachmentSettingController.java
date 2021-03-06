package com.jzsoft.platform.component.attachment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzsoft.platform.component.attachment.model.AttachmentSetting;
import com.jzsoft.platform.component.attachment.service.AttachmentSettingService;
import com.jzsoft.platform.core.web.argument.annotation.FormModel;
import com.jzsoft.platform.core.web.controller.SpringController;

@Controller
@RequestMapping("/sys/attachmentSetting")
public class AttachmentSettingController extends SpringController {

	@Autowired
	private AttachmentSettingService attachmentSettingService;

	@RequestMapping("/index")
	public String index() {
		return "/platform/component/attachment/attachmentSetting-index";
	}
	
	@RequestMapping("/input")
	public String input() {
		AttachmentSetting model = attachmentSettingService.getAttachmentSetting();
		setAttribute("model", model);
		return "/platform/component/attachment/attachmentSetting-input";
	}

	@RequestMapping("/ajaxTest")
	@ResponseBody
	public void ajaxTest(@FormModel("model") AttachmentSetting model) {
		String result = attachmentSettingService.testConfig(model);
		this.print(result);
	}

	@RequestMapping("/ajaxSave")
	@ResponseBody
	public void ajaxSave(@FormModel("model") AttachmentSetting model) {
		AttachmentSetting old = attachmentSettingService.getAttachmentSetting();
		if (old != null) {
			model.setId(old.getId());
		}
		attachmentSettingService.save(model);
		this.print("OK");
	}
}
