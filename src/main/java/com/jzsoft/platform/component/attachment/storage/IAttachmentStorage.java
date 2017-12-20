package com.jzsoft.platform.component.attachment.storage;

import java.io.File;

import com.jzsoft.platform.component.attachment.model.Attachment;

public interface IAttachmentStorage {

	/**
	 * 保存文件
	 * 
	 * @param sourceFile
	 *            本地已存在的文件
	 * @param targetFile
	 *            要保存到的文件，可以是远程，远程会去掉盘符（如：d:）
	 */
	String storage(File sourceFile, File targetFile);

	/**
	 * 获取文件的本地路径，如果在远程服务器上需下载。
	 * 
	 * @param attachment
	 * @return
	 */
	File getFile(Attachment attachment);

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 */
	void deleteFile(Attachment attachment);

}
