package com.jzsoft.platform.util;

public class ExceptionUtil {

	public static RuntimeException convertExceptionToUnchecked(Exception e) {
		return new RuntimeException("Unexpected Checked Exception.", e);
	}
}
