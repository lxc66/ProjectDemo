package com.jzsoft.platform.core.shiro.exception;

import org.apache.shiro.authc.CredentialsException;

public class IncompleteCredentialsException  extends CredentialsException {
	private static final long serialVersionUID = 1L;

	public IncompleteCredentialsException() {
        super();
    }
    
    public IncompleteCredentialsException(String message) {
        super(message);
    }
    
    public IncompleteCredentialsException(Throwable cause) {
        super(cause);
    }

    public IncompleteCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

}
