package com.jzsoft.platform.component.attachment.storage.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jzsoft.platform.component.attachment.model.Attachment;
import com.jzsoft.platform.component.attachment.storage.FastDfsHelper;
import com.jzsoft.platform.component.attachment.storage.IAttachmentStorage;

public class FastDfsStorageImpl implements IAttachmentStorage {

	private final static Logger logger = LoggerFactory.getLogger(FastDfsHelper.class);
	
	@Override
	public String storage(File sourceFile, File targetFile) {
		return FastDfsHelper.upload(sourceFile);
	}

	@Override
	public File getFile(Attachment attachment) {
		return FastDfsHelper.download(attachment.getServerFilename());
	}

	@Override
	public void deleteFile(Attachment attachment) {
		FastDfsHelper.delete(attachment.getServerFilename());
	}
}
