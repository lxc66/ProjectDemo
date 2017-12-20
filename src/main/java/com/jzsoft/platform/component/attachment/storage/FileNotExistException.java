package com.jzsoft.platform.component.attachment.storage;

import com.jzsoft.platform.core.exception.BusinessException;

/**
 * 文件不存在异常，下载文件时可能抛出此异常
 * 
 * @author tianyl
 * 
 */
public class FileNotExistException extends BusinessException {

	private static final long serialVersionUID = 8884584577088905437L;

	public FileNotExistException() {
		super();
	}

	public FileNotExistException(String errorMessage, Throwable t) {
		super(errorMessage, t);
	}
	
	public FileNotExistException(String errorMessage) {
		super(errorMessage);
	}


}
