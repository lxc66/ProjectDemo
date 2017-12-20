package com.jzsoft.platform.component.attachment.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jzsoft.platform.component.attachment.constant.AttachmentConstant;
import com.jzsoft.platform.component.attachment.dao.AttachmentDAO;
import com.jzsoft.platform.component.attachment.model.Attachment;
import com.jzsoft.platform.component.attachment.model.AttachmentConfig;
import com.jzsoft.platform.component.attachment.storage.StorageFactory;
import com.jzsoft.platform.component.attachment.storage.impl.LocalStorageImpl;
import com.jzsoft.platform.component.attachment.util.AttachmentUtil;
import com.jzsoft.platform.core.spring.SpringMVCUtil;
import com.jzsoft.platform.util.FileUtil;
import com.jzsoft.platform.util.ReflectUtil;
import com.jzsoft.platform.util.UUID;

@Service
public class AttachmentService {
	private final static Logger logger = LoggerFactory.getLogger(LocalStorageImpl.class);
	@Autowired
	private AttachmentDAO attachmentDAO;

	@Autowired
	private AttachmentConfigService attachmentConfigService;

	public void save(Attachment attachment) {
		attachmentDAO.save(attachment);
	}

	public List<Attachment> getAttachmentsWithOwnerId(String ownerId) {
		return attachmentDAO.getAttachmentsWithOwnerId(ownerId, AttachmentConstant.ATTACHMENT_STATUS_USE);
	}
	
	public Map<String,List<Attachment>> getAttachmentMapWithOwnerIds(List<String> ownerIds) {
		Map<String,List<Attachment>> attachmentMap = new HashMap<String, List<Attachment>>();
		List<Attachment> attachments = attachmentDAO.getAttachmentsWithOwnerIds(ownerIds, AttachmentConstant.ATTACHMENT_STATUS_USE);
		for(String ownerId : ownerIds){
			attachmentMap.put(ownerId, new ArrayList<Attachment>());
		}
		for(Attachment attachment : attachments){
			String ownerId = attachment.getId_owner();
			attachmentMap.get(ownerId).add(attachment);
		}
		return attachmentMap;
	}

	public void bindAttachment(Object obj, String ids, String delIds, String configCode) {
		bindAttachment(obj, ids, delIds, configCode, "id");
	}

	public void saveAttachment(Attachment attachment, Integer fileNum, AttachmentConfig attachmentConfig, Object obj, String fieldName) {
		File sourceFile = new File(AttachmentUtil.getTempDir() + "/" + attachment.getServerFilename());
		String targetFilePath = AttachmentUtil.getSaveFilePath(attachmentConfig) + attachment.getServerFilename();
		File targetFile = new File(targetFilePath);
		
		String serverFileName = StorageFactory.getStorageService().storage(sourceFile, targetFile);
		if(serverFileName!=null){
			attachment.setServerFilename(serverFileName);
		}
		// 删除临时目录中文件
		sourceFile.delete();
		attachment.setId_owner(ReflectUtil.getFieldValue(obj, fieldName).toString());
		attachment.setFileNum(fileNum);
		attachment.setStatus(AttachmentConstant.ATTACHMENT_STATUS_USE);
		attachment.setPath(AttachmentUtil.getRelativePath(attachmentConfig));
		attachment.setConfigCode(attachmentConfig.getCode());
		attachmentDAO.update(attachment);
	}

	public void delete(String attachmentId) {
		attachmentDAO.delete(attachmentId);
	}

	public void logicDelete(String attachmentId) {
		attachmentDAO.logicDelete(attachmentId);
	}

	public Attachment get(String id) {
		return attachmentDAO.get(id);
	}

	public void download(String downloadToken, boolean checkUser, String period, Boolean isZip, String fileName) {
		String newToken = AttachmentUtil.getAttachmentToken(downloadToken.substring(0, 32), period, checkUser);
		if (newToken.equals(downloadToken)) {
			if (!isZip) {
				downloadSingleFile(downloadToken);
			} else {
				downloadZipFile(downloadToken, fileName);
			}
		} else {
			SpringMVCUtil.renderText("无权限下载");
		}
	}

	private void downloadZipFile(String downloadToken, String fileName) {
		String ownerId = downloadToken.substring(0, 32);
		List<Attachment> attachments = this.getAttachmentsWithOwnerId(ownerId);
		List<File> files = new ArrayList<File>();
		List<String> fileNames = new ArrayList<String>();
		for (Attachment att : attachments) {
			files.add(AttachmentUtil.getAttachmentFile(att));
			fileNames.add(att.getSourceFilename());
		}
		File zipFile = new File(AttachmentUtil.getTempDir() + "/" + UUID.getUUID() + ".zip");
		AttachmentUtil.getZip(files, fileNames, zipFile);
		FileUtil.download(zipFile, StringUtils.isBlank(fileName) ? zipFile.getName().trim() : fileName.trim());
	}

	private void downloadSingleFile(String downloadToken) {
		Attachment attachment = attachmentDAO.get(downloadToken.substring(0, 32));
		FileUtil.download(AttachmentUtil.getAttachmentFile(attachment), attachment.getSourceFilename());
	}

	public void showTemp(String attachmentId, String contentType) {
		Attachment attachment = attachmentDAO.get(attachmentId);
		File file = new File(AttachmentUtil.getTempDir() + "/" + attachment.getServerFilename());
		FileUtil.renderMediaToClient(file, contentType);
	}

	public void showPic(String token, boolean checkUser, String period) {
		String newToken = AttachmentUtil.getAttachmentToken(token.substring(0, 32), period, checkUser);
		if (newToken.equals(token)) {
			Attachment attachment = attachmentDAO.get(token.substring(0, 32));
			if(attachment==null){
				logger.error("attachment is null !! token:"+token);
			}
			FileUtil.renderMediaToClient(AttachmentUtil.getAttachmentFile(attachment), AttachmentConstant.CONTENTTYPE_IMAGE);
		} else {
			SpringMVCUtil.renderText("无权限");
		}
	}

	public void showAudio(String token, boolean checkUser, String period) {
		String newToken = AttachmentUtil.getAttachmentToken(token.substring(0, 32), period, checkUser);
		if (newToken.equals(token)) {
			Attachment attachment = attachmentDAO.get(token.substring(0, 32));
			FileUtil.renderMediaToClient(AttachmentUtil.getAttachmentFile(attachment), AttachmentConstant.CONTENTTYPE_AUDIO);
		} else {
			SpringMVCUtil.renderText("无权限");
		}
	}

	public void show(String token, boolean checkUser, String period) {
		String newToken = AttachmentUtil.getAttachmentToken(token.substring(0, 32), period, checkUser);
		if (newToken.equals(token)) {
			Attachment attachment = attachmentDAO.get(token.substring(0, 32));
			String mimeType = AttachmentConstant.contentTypeMap.get(FileUtil.getFileExtention(attachment.getSourceFilename()));
			if (StringUtils.isBlank(mimeType)) {
				mimeType = AttachmentConstant.CONTENTTYPE_APPLICATION;
			}
			FileUtil.renderMediaToClient(AttachmentUtil.getAttachmentFile(attachment), mimeType);
		} else {
			SpringMVCUtil.renderText("无权限");
		}
	}

	public void downloadMulitOwner(Map<String, String> map) {
		List<File> files = new ArrayList<File>();
		List<String> fileNames = new ArrayList<String>();
		for (String ownerId : map.keySet()) {
			List<Attachment> attachments = this.getAttachmentsWithOwnerId(ownerId);
			for (Attachment att : attachments) {
				files.add(AttachmentUtil.getAttachmentFile(att));
				fileNames.add(att.getSourceFilename());
			}
		}
		File zipFile = new File(AttachmentUtil.getTempDir() + "/" + UUID.getUUID() + ".zip");
		AttachmentUtil.getZip(files, fileNames, zipFile);
		FileUtil.download(zipFile, zipFile.getName());
	}

	public void deleteUEditorFile(String id) {
		Attachment att = attachmentDAO.get(id);
		attachmentDAO.updateNum(att.getId_owner(), att.getFileNum());
		StorageFactory.getStorageService().deleteFile(att);
		attachmentDAO.delete(id);
	}

	public void bindAttachment(Object obj, String ids, String delIds, String configCode, String fieldName) {
		AttachmentConfig attachmentConfig = null;
		if (StringUtils.isNotBlank(configCode)) {
			attachmentConfig = attachmentConfigService.getAttachmentConfigWithCode(configCode);
		}
		if (attachmentConfig == null) {
			attachmentConfig = attachmentConfigService.getAttachmentConfigDefault();
		}
		bindAttachment(obj, ids, delIds, fieldName, attachmentConfig);
	}

	public void bindAttachment(Object obj, String ids, String delIds, String fieldName, AttachmentConfig attachmentConfig) {
		int i = this.getAttachmentsWithOwnerId(ReflectUtil.getFieldValue(obj, fieldName).toString()).size();
		for (String id : ids.split(",")) {
			if (StringUtils.isNotBlank(id)) {
				Attachment att = attachmentDAO.get(id);
				saveAttachment(att, i, attachmentConfig, obj, fieldName);
			}
			i++;
		}
		for (String delId : delIds.split(",")) {
			if (StringUtils.isNotBlank(delId)) {
				logicDelete(delId);
			}
		}
	}
}
