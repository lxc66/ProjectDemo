package com.jzsoft.platform.module.config.model;

import java.io.Serializable;


public class ConfigItem implements Serializable {
	private static final long serialVersionUID = 3952583658783363133L;
	private String id;
	private String configId;
	private String name;
	private String value;
	private String defaultFlag;
	private String enableFlag;
	private String ft;
	private String lt;
	private String fu;
	private String lu;
	public static final String DEFAULT_NO = "0";
	public static final String DEFAULT_YES = "1";

	
	public static final String ENABLE_NO = "0";
	public static final String ENABLE_YES = "1";//默认值不能禁用
	public ConfigItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public String getFt() {
		return ft;
	}

	public void setFt(String ft) {
		this.ft = ft;
	}

	public String getLt() {
		return lt;
	}

	public void setLt(String lt) {
		this.lt = lt;
	}

	public String getFu() {
		return fu;
	}

	public void setFu(String fu) {
		this.fu = fu;
	}

	public String getLu() {
		return lu;
	}

	public void setLu(String lu) {
		this.lu = lu;
	}

	public String getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}

}
