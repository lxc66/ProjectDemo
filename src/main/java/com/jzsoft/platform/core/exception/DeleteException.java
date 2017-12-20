package com.jzsoft.platform.core.exception;

public class DeleteException extends BusinessException {
	private static final long serialVersionUID = 3411959600698110088L;

	public DeleteException() {
		super();
	}


	public DeleteException(String errorMessage, Throwable t) {
		super(errorMessage, t);
	}

}
