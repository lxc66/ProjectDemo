package com.jzsoft.platform.component.attachment.storage;

import com.jzsoft.platform.component.attachment.model.AttachmentSetting;
import com.jzsoft.platform.component.attachment.service.AttachmentSettingService;
import com.jzsoft.platform.component.attachment.storage.impl.FastDfsStorageImpl;
import com.jzsoft.platform.component.attachment.storage.impl.FtpStorageImpl;
import com.jzsoft.platform.component.attachment.storage.impl.LocalStorageImpl;
import com.jzsoft.platform.core.spring.SpringContextHolder;

public class StorageFactory {

	private static final LocalStorageImpl LOCAL_STORAGE_IMPL = new LocalStorageImpl();

	private static final FtpStorageImpl FTP_STORAGE_IMPL = new FtpStorageImpl();
	
	private static final FastDfsStorageImpl FASTDFS_STORAGE_IMPL = new FastDfsStorageImpl();

	public static IAttachmentStorage getStorageService() {
		AttachmentSettingService attachmentSettingService = SpringContextHolder.getBeanOneOfType(AttachmentSettingService.class);
		AttachmentSetting attachmentSetting = attachmentSettingService.getAttachmentSetting();
		if(attachmentSetting.isFastDFS()){
			return FASTDFS_STORAGE_IMPL;
		}
		return attachmentSetting.isFtp() ? FTP_STORAGE_IMPL : LOCAL_STORAGE_IMPL;
	}

}
