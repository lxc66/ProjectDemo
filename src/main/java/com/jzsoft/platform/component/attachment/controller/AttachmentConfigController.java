package com.jzsoft.platform.component.attachment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jzsoft.platform.component.attachment.constant.AttachmentConstant;
import com.jzsoft.platform.component.attachment.model.AttachmentConfig;
import com.jzsoft.platform.component.attachment.model.AttachmentSetting;
import com.jzsoft.platform.component.attachment.service.AttachmentConfigService;
import com.jzsoft.platform.component.attachment.service.AttachmentSettingService;
import com.jzsoft.platform.component.jqGrid.controller.JqGridController;
import com.jzsoft.platform.core.dao.Page;
import com.jzsoft.platform.core.i18n.util.I18nUtil;
import com.jzsoft.platform.core.web.argument.annotation.FormModel;
import com.jzsoft.platform.util.JsonUtil;
import com.jzsoft.platform.util.UUID;

@Controller
@RequestMapping("/sys/attachmentConfig")
public class AttachmentConfigController extends JqGridController<AttachmentConfig> {

	@Autowired
	private AttachmentSettingService attachmentSettingService;
	@Autowired
	private AttachmentConfigService attachmentConfigService;

	@RequestMapping("/toConfigIndex")
	public String toConfigIndex() {
		List<AttachmentConfig> list = attachmentConfigService.getAttachmentConfigs();
		setAttribute("list", list);
		return "/platform/component/attachment/attachmentConfig";
	}
	
	@RequestMapping("/list")
	public String list() {
		return "/platform/component/attachment/attachmentConfig";
	}
	
	@RequestMapping("/listData")
	@ResponseBody
	public String listData(@FormModel("page") Page<AttachmentConfig> page,@FormModel("model") AttachmentConfig model,String sord,String sidx){
		return this.refreshDataTable(attachmentConfigService.getPage(model, page, sord, sidx));
	}
	

	@RequestMapping("/input")
	public String input(String id) {
		if (StringUtils.isNotBlank(id)) {
			setAttribute("model", attachmentConfigService.get(id));
		}
		return "/platform/component/attachment/attachmentConfig-input";
	}

	@RequestMapping("/ajaxCheckRepeatName")
	@ResponseBody
	public String ajaxCheckRepeatName(String code, String id) {
		boolean result = attachmentConfigService.checkRepeatName(code, id);
		if (result) {
			return "OK";
		} else {
			return "ERROR";
		}
	}

	@RequestMapping("/ajaxCheckCode")
	@ResponseBody
	public String ajaxCheckCode(String code , String excludeCode) {
		if (this.attachmentConfigService.isExistsCode(code, excludeCode)) {
			return "false";
		}else{
			return "true";
		}
	}
	
	@RequestMapping("/ajaxDele")
	@ResponseBody
	public String ajaxDele(String id) {
		attachmentConfigService.delete(id);
		return null;
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(@FormModel("model") AttachmentConfig model) {
		attachmentConfigService.save(model);
		return "";
	}

	@RequestMapping("/ajaxGetConfig")
	@ResponseBody
	public void ajaxGetConfig(String configCode) {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isBlank(configCode)) {
			configCode = AttachmentConstant.ATTACHMENT_DEFAULTCONFIG_CODE;
		}
		AttachmentSetting attachmentSetting = attachmentSettingService.getAttachmentSetting();
		AttachmentConfig attachmentConfig = attachmentConfigService.getAttachmentConfigWithCode(configCode);

		map.put("filterArr", attachmentConfig.getFlexFileFilterStr());
		map.put("maxFileSize", (attachmentConfig.getFileSize() == null ? -1 : attachmentConfig.getFileSize()) + "");
		map.put("maxFileCount", (attachmentConfig.getFileCount() == null ? -1 : attachmentConfig.getFileCount()) + "");
		map.put("limitFileExtention", attachmentSetting.getLimitFileExtention());
		String allowTypeStr = "";
		if (StringUtils.isNotBlank(attachmentConfig.getExtension())) {
			allowTypeStr = I18nUtil.getI18nString("upload_allowType") + attachmentConfig.getExtension() + "<br>";
		}
		map.put("allowTypeStr", allowTypeStr);
		String limitFileExtention = "";
		if (StringUtils.isBlank(allowTypeStr) && StringUtils.isNotBlank(attachmentSetting.getLimitFileExtention())) {// 没有允许的类型，才提示禁止类型
			limitFileExtention = I18nUtil.getI18nString("upload_notAllowType") + attachmentSetting.getLimitFileExtention() + "<br>";
		}
		map.put("limitFileExtentionStr", limitFileExtention);
		String maxCountStr = "";
		if (attachmentConfig.getFileCount() != null && attachmentConfig.getFileCount() > 0) {
			maxCountStr = I18nUtil.getI18nString("upload_allowFileCount") + attachmentConfig.getFileCount() + "<br>";
		}
		map.put("maxCountStr", maxCountStr);
		String maxFileSize = "";
		if (attachmentConfig.getFileSize() != null && attachmentConfig.getFileSize() > 0) {
			maxFileSize = I18nUtil.getI18nString("upload_allowFileSize") + attachmentConfig.getFileSize() + "M";
		}
		map.put("maxFileSize", maxFileSize);
		map.put("configId", attachmentConfig.getId());
		map.put("sessionId", this.getRequest().getSession().getId());
		this.print(JsonUtil.map2Json(map));
//		return JsonUtil.map2Json(map);
	}

	@RequestMapping("/saveUploadFile")
	@ResponseBody
	public String saveUploadFile(@RequestParam(value = "filedata") MultipartFile file) {
		System.out.println(file.getOriginalFilename());
		return UUID.getUUID();
	}

}
