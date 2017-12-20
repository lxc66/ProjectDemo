package com.jzsoft.platform.component.attachment.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jzsoft.platform.component.attachment.model.AttachmentConfig;
import com.jzsoft.platform.core.dao.BaseDAO;

@Repository
public class AttachmentConfigDAO extends BaseDAO<AttachmentConfig, String>{

	public AttachmentConfig getAttachmentConfigWithCode(String code) {
		List<AttachmentConfig> attachmentConfigs = this.getList("getAttachmentConfigWithCode", code);
		return attachmentConfigs.size() > 0 ? attachmentConfigs.get(0) : null;
	}
	
	public List<AttachmentConfig> getAttachmentConfigMapWithCodes(List<String> codes) {
		return this.getList("getAttachmentConfigWithCodes", codes);
	}

	public List<AttachmentConfig> findAttachmentConfigWithCodeAndNotId(String code, String id) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("code", code);
		params.put("id", id);
		return this.getList("findAttachmentConfigWithCodeAndNotId", params);
	}

	public AttachmentConfig getWithCodeAndExclude(String code , String excludeCode) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("code", code);
		if(StringUtils.isNotBlank(excludeCode)){
			params.put("excludeCode", excludeCode);
		}
		return this.get("getWithCodeAndExclude", params);
	}
}
