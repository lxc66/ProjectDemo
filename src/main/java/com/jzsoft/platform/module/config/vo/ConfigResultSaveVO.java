package com.jzsoft.platform.module.config.vo;

import java.util.List;

public class ConfigResultSaveVO {
	private String configId;
	private String mode;
	private List<String> values;
	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public List<String> getValues() {
		return values;
	}
	public void setValues(List<String> values) {
		this.values = values;
	}
			
	
}
