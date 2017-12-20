package com.jzsoft.platform.core.config;

import lombok.core.Main;

public class SystemConstants extends ConfigurableConstants {

	static {
		init("/conf/system.properties");
	}

	public static final String I18N_ENABLECACHE = getProperty("i18n.enableCache", "false");
	public static final String SERVICE_URL = getProperty("service.url");
	public static final String IMAGE_URL = getProperty("image.url");
	public static void main(String[] args) {
		System.out.println(SystemConstants.IMAGE_URL);
	}
}
