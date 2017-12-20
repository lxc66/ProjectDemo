package com.jzsoft.platform.component.attachment.storage.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jzsoft.platform.component.attachment.model.Attachment;
import com.jzsoft.platform.component.attachment.storage.FileNotExistException;
import com.jzsoft.platform.component.attachment.storage.IAttachmentStorage;
import com.jzsoft.platform.component.attachment.util.AttachmentUtil;
import com.jzsoft.platform.core.exception.BusinessException;

public class LocalStorageImpl implements IAttachmentStorage {
	private final static Logger logger = LoggerFactory.getLogger(LocalStorageImpl.class);
	@Override
	public String storage(File sourceFile, File targetFile) {
		if (!targetFile.getParentFile().exists()) {
			targetFile.getParentFile().mkdirs();
		}
		try {
			FileUtils.copyFile(sourceFile, targetFile);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString()+":"+e.getMessage());
			throw new BusinessException("attachment.file.save.fail");
		}
		return null;
	}

	public File getFile(File file) {
		if (!file.exists()) {
			throw new FileNotExistException("文件不存在！");
		}
		return file;
	}

	@Override
	public File getFile(Attachment attachment) {
		String filePath = AttachmentUtil.getAttachmentFilePath(attachment);
		return new File(filePath);
	}

	@Override
	public void deleteFile(Attachment attachment) {
		String filePath = AttachmentUtil.getAttachmentFilePath(attachment);
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
	}

}
