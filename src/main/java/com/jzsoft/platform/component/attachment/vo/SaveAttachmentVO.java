package com.jzsoft.platform.component.attachment.vo;

import java.io.File;
import java.util.List;

public class SaveAttachmentVO {
	private String ownerId;

	private String configCode;

	private List<File> files;

	public SaveAttachmentVO(String ownerId, String configCode, List<File> files) {
		super();
		this.ownerId = ownerId;
		this.configCode = configCode;
		this.files = files;
	}

	public SaveAttachmentVO() {

	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getConfigCode() {
		return configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

}
