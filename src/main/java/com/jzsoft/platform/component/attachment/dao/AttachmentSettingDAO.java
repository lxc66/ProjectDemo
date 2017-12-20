package com.jzsoft.platform.component.attachment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jzsoft.platform.component.attachment.model.AttachmentSetting;
import com.jzsoft.platform.core.dao.BaseDAO;

@Repository
public class AttachmentSettingDAO extends BaseDAO<AttachmentSetting, String>{

	public AttachmentSetting getAttachmentSetting() {
		List<AttachmentSetting> attachmentSetting = this.getAll();
		return attachmentSetting.size() > 0 ? attachmentSetting.get(0) : null;
	}

}
