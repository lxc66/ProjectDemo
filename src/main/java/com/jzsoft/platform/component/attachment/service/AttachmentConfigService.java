package com.jzsoft.platform.component.attachment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jzsoft.platform.component.attachment.constant.AttachmentConstant;
import com.jzsoft.platform.component.attachment.dao.AttachmentConfigDAO;
import com.jzsoft.platform.component.attachment.model.AttachmentConfig;
import com.jzsoft.platform.core.dao.Page;

@Service
public class AttachmentConfigService {

	@Autowired
	private AttachmentConfigDAO attachmentConfigDAO;

	public List<AttachmentConfig> getAttachmentConfigs() {
		return attachmentConfigDAO.getAll();
	}

	public Page<AttachmentConfig> getPage(AttachmentConfig model, Page<AttachmentConfig> page, String sord,String sidx) {
		page.addParams("sord", sord);
		page.addParams("sidx", sidx);
		return attachmentConfigDAO.getPage(page);
	}
	
	public AttachmentConfig getAttachmentConfig(String id) {
		return attachmentConfigDAO.get(id);
	}

	public void save(AttachmentConfig attachmentConfig) {
		attachmentConfigDAO.saveOrUpdate(attachmentConfig);
	}

	public void delete(String[] ids) {
		for (String id : ids) {
			if (StringUtils.isNotBlank(id)) {
				attachmentConfigDAO.delete(id);
			}
		}
	}

	public void delete(String id) {
		attachmentConfigDAO.delete(id);
	}

	public AttachmentConfig getAttachmentConfigDefault() {
		return attachmentConfigDAO.getAttachmentConfigWithCode(AttachmentConstant.ATTACHMENT_DEFAULTCONFIG_CODE);
	}

	public AttachmentConfig getAttachmentConfigWithCode(String code) {
		return attachmentConfigDAO.getAttachmentConfigWithCode(code);
	}
	
	public Map<String,AttachmentConfig> getAttachmentConfigMapWithCode(List<String> codes) {
		Map<String,AttachmentConfig>  configMap= new HashMap<String, AttachmentConfig>();
		List<AttachmentConfig> attachmentConfigs = attachmentConfigDAO.getAttachmentConfigMapWithCodes(codes);
		for(AttachmentConfig config: attachmentConfigs){
			configMap.put(config.getCode(), config);
		}
		return configMap;
	}

	public AttachmentConfig get(String id) {
		return attachmentConfigDAO.get(id);
	}

	public boolean checkRepeatName(String code, String id) {
		List<AttachmentConfig> attachmentConfigs = attachmentConfigDAO.findAttachmentConfigWithCodeAndNotId(code, id);
		return attachmentConfigs.size() == 0;
	}
	
	public boolean isExistsCode(String code,final String excludeCode) {
		AttachmentConfig model = attachmentConfigDAO.getWithCodeAndExclude(code, excludeCode);
		if (model != null) {
			return true;
		} 
		return false;
	}
}
