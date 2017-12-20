package com.jzsoft.platform.component.attachment.external.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jzsoft.platform.component.attachment.constant.AttachmentConstant;
import com.jzsoft.platform.component.attachment.dao.AttachmentDAO;
import com.jzsoft.platform.component.attachment.external.IAttachmentService;
import com.jzsoft.platform.component.attachment.model.Attachment;
import com.jzsoft.platform.component.attachment.model.AttachmentConfig;
import com.jzsoft.platform.component.attachment.service.AttachmentConfigService;
import com.jzsoft.platform.component.attachment.service.AttachmentService;
import com.jzsoft.platform.component.attachment.storage.StorageFactory;
import com.jzsoft.platform.component.attachment.util.AttachmentUtil;
import com.jzsoft.platform.component.attachment.vo.SaveAttachmentVO;
import com.jzsoft.platform.util.FileUtil;
import com.jzsoft.platform.util.UUID;


@Service
@Transactional
public class AttachmentServiceImpl implements IAttachmentService {

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private AttachmentConfigService attachmentConfigService;
	@Autowired
	private AttachmentDAO attachmentDAO;

	@Override
	public void bindAttachment(Object obj, String ids, String delIds, String configCode) {
		attachmentService.bindAttachment(obj, ids, delIds, configCode);
	}

	@Override
	public void bindAttachment(Object obj, String ids, String delIds, String configCode, String fieldName) {
		attachmentService.bindAttachment(obj, ids, delIds, configCode, fieldName);
	}

	@Override
	public List<Attachment> findAttachment(String ownerId) {
		return attachmentService.getAttachmentsWithOwnerId(ownerId);
	}

	@Override
	public Map<String, List<Attachment>> getAttachmentMapWithOwnerIds(List<String> ownerIds) {
		return attachmentService.getAttachmentMapWithOwnerIds(ownerIds);
	}
	
	@Override
	public List<File> findFile(String ownerId, String configCode) {
		List<Attachment> attachments = attachmentService.getAttachmentsWithOwnerId(ownerId);
		List<File> files = new ArrayList<File>();
		for (Attachment att : attachments) {
			File file = AttachmentUtil.getAttachmentFile(att);
			files.add(file);
		}
		return files;
	}

	@Override
	public void saveAttachment(String ownerId, String configCode, List<File> files) {
		AttachmentConfig attachmentConfig = null;
		if (StringUtils.isNotBlank(configCode)) {
			attachmentConfig = attachmentConfigService.getAttachmentConfigWithCode(configCode);
		} else {
			attachmentConfig = attachmentConfigService.getAttachmentConfigDefault();
		}
		int fileNum = 0;
		for (File f : files) {
			File targetFile = new File(AttachmentUtil.getSaveFilePath(attachmentConfig) + UUID.getUUID() + "." + FileUtil.getFileExtention(f));
			
			String serverFileName = StorageFactory.getStorageService().storage(f, targetFile);
			serverFileName=serverFileName!=null?serverFileName:targetFile.getName();
			Attachment attachment = new Attachment();
			attachment.setFileNum(fileNum);
			fileNum++;
			attachment.setId_owner(ownerId);
			attachment.setSourceFilename(f.getName());
			attachment.setServerFilename(serverFileName);
			attachment.setStatus(AttachmentConstant.ATTACHMENT_STATUS_USE);
			attachment.setPath(AttachmentUtil.getRelativePath(attachmentConfig));
			attachment.setConfigCode(attachmentConfig.getCode());
			attachmentService.save(attachment);
		}
	}
	
	@Override
	public void saveAttachment(List<SaveAttachmentVO> saveAttachmentVOs) {
		List<String>configCodes = new ArrayList<String>();
		for(SaveAttachmentVO vo : saveAttachmentVOs){
			configCodes.add(vo.getConfigCode());
		}
		Map<String, AttachmentConfig> configMap = attachmentConfigService.getAttachmentConfigMapWithCode(configCodes);
		List<Attachment> saveAttachments = new ArrayList<Attachment>();
		AttachmentConfig defaultConfig = null;
		for(SaveAttachmentVO vo : saveAttachmentVOs){
			AttachmentConfig attachmentConfig = configMap.get(vo.getConfigCode());
			if (attachmentConfig==null) {
				if(defaultConfig==null){
					defaultConfig = attachmentConfigService.getAttachmentConfigDefault();
				}
				attachmentConfig = defaultConfig;
			}
			int fileNum = 0;
			for (File f : vo.getFiles()) {
				File targetFile = new File(AttachmentUtil.getSaveFilePath(attachmentConfig) + UUID.getUUID() + "." + FileUtil.getFileExtention(f));
				
				String serverFileName = StorageFactory.getStorageService().storage(f, targetFile);
				serverFileName=serverFileName!=null?serverFileName:targetFile.getName();
				Attachment attachment = new Attachment();
				attachment.setFileNum(fileNum);
				fileNum++;
				attachment.setId_owner(vo.getOwnerId());
				attachment.setSourceFilename(f.getName());
				attachment.setServerFilename(serverFileName);
				attachment.setStatus(AttachmentConstant.ATTACHMENT_STATUS_USE);
				attachment.setPath(AttachmentUtil.getRelativePath(attachmentConfig));
				attachment.setConfigCode(attachmentConfig.getCode());
				saveAttachments.add(attachment);
			}
		}
		attachmentDAO.batchSave(saveAttachments);
	}

	@Override
	public void deleteAttachment(String ownerId) {
		AttachmentUtil.unbindAttachment(ownerId);
	}

	@Override
	public String getDownloadUrlWithoutContextPath(Attachment attachment, boolean checkUser, String period) {
		return AttachmentUtil.getDownloadUrlWithoutContextPath(attachment, checkUser, period);
	}

	@Override
	public String getPictureShowUrlWithoutContextPath(Attachment attachment, boolean checkUser, String period) {
		return AttachmentUtil.getPictureShowUrlWithoutContextPath(attachment, checkUser, period);
	}

	@Override
	public List<File> getTempFiles() {
		return AttachmentUtil.getTempFiles();
	}

	@Override
	public Attachment get(String attachmentId) {
		return attachmentService.get(attachmentId);
	}

	@Override
	public void copyAttachment(String fromOwnerId, String toOwnerId) {
		List<Attachment> attachments = attachmentService.getAttachmentsWithOwnerId(fromOwnerId);
		List<Attachment> toAttachments = attachmentService.getAttachmentsWithOwnerId(toOwnerId);
		int count = toAttachments.size();
		for (Attachment att : attachments) {
			Attachment newAtt = new Attachment();
			newAtt.setFileKind(att.getFileKind());
			newAtt.setFileNum(count);
			newAtt.setFileSize(att.getFileSize());
			newAtt.setId_owner(toOwnerId);
			newAtt.setConfigCode(att.getConfigCode());
			newAtt.setPath(att.getPath());
			newAtt.setServerFilename(att.getServerFilename());
			newAtt.setSourceFilename(att.getSourceFilename());
			newAtt.setStatus(att.getStatus());
			attachmentService.save(newAtt);
			count++;
		}
	}

	@Override
	public void copyAttachment(String fromOwnerId, String toOwnerId, String fromConfigCode, String toConfigCode) {
		List<Attachment> attachments = attachmentService.getAttachmentsWithOwnerId(fromOwnerId);
		List<Attachment> toAttachments = attachmentService.getAttachmentsWithOwnerId(toOwnerId);
		int count = toAttachments.size();
		AttachmentConfig attachmentConfig = attachmentConfigService.getAttachmentConfigWithCode(fromConfigCode);
		AttachmentConfig toAttachmentConfig = attachmentConfigService.getAttachmentConfigWithCode(toConfigCode);
		for (Attachment att : attachments) {
			File file = AttachmentUtil.getAttachmentFile(att);
			String newFileName = UUID.getUUID() + "." + AttachmentUtil.getFileExtension(att.getSourceFilename());
			File targetFile = new File(AttachmentUtil.getSaveFilePath(toAttachmentConfig) + newFileName);
			String serverFileName = StorageFactory.getStorageService().storage(file, targetFile);
			serverFileName=serverFileName!=null?serverFileName:newFileName;
			Attachment newAtt = new Attachment();
			newAtt.setFileKind(att.getFileKind());
			newAtt.setFileNum(count);
			newAtt.setFileSize(att.getFileSize());
			newAtt.setId_owner(toOwnerId);
			newAtt.setConfigCode(toConfigCode);
			newAtt.setPath(AttachmentUtil.getRelativePath(attachmentConfig));
			newAtt.setServerFilename(serverFileName);
			newAtt.setSourceFilename(att.getSourceFilename());
			newAtt.setStatus(att.getStatus());
			attachmentService.save(newAtt);
			count++;
		}
	}

}
