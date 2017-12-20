package com.jzsoft.platform.module.config.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConfigResult implements Serializable {
	private static final long serialVersionUID = -5366162029522090174L;
	private String id;
	private String userId;
	private Config config;
	private String configId;
	private String customValue;
	private String ft;
	private String lt;
	private String fu;
	private String lu;
	private List<ConfigResultItem> configResultItems = new ArrayList<ConfigResultItem>();

	public ConfigResult() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getConfigId() {
		return configId;
	}


	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public String getCustomValue() {
		return customValue;
	}

	public void setCustomValue(String customValue) {
		this.customValue = customValue;
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

	public List<ConfigResultItem> getConfigResultItems() {
		return configResultItems;
	}

	public void setConfigResultItems(List<ConfigResultItem> configResultItems) {
		this.configResultItems = configResultItems;
	}

}
