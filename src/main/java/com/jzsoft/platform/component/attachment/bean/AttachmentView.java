package com.jzsoft.platform.component.attachment.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jzsoft.platform.component.abstractUC.bean.AbstractUCBean;
import com.jzsoft.platform.component.attachment.vo.AttachmentVO;


public class AttachmentView extends AbstractUCBean {

	final public static String TEMPLATE = "attachmentView";

	protected List<AttachmentVO> attachmentVOs;

	protected String zipUrl;

	public AttachmentView(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	protected String getTemplate() {
		return TEMPLATE;
	}


	public List<AttachmentVO> getAttachmentVOs() {
		return attachmentVOs;
	}

	public void setAttachmentVOs(List<AttachmentVO> attachmentVOs) {
		this.attachmentVOs = attachmentVOs;
	}

	public String getZipUrl() {
		return zipUrl;
	}

	public void setZipUrl(String zipUrl) {
		this.zipUrl = zipUrl;
	}

}
