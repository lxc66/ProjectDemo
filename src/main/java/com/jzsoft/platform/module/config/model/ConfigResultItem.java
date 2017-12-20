package com.jzsoft.platform.module.config.model;



public class ConfigResultItem implements java.io.Serializable {
	private static final long serialVersionUID = 992011462999740931L;
	private String id;
	private String configResultId;
	private String configItemId;
	private String ft;
	private String lt;
	private String fu;
	private String lu;

	public ConfigResultItem() {
	}

	public ConfigResultItem(String id) {
		this.id = id;
	}

	public ConfigResultItem(String configResultId, String configItemId) {
		super();
		this.configResultId = configResultId;
		this.configItemId = configItemId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConfigResultId() {
		return configResultId;
	}

	public void setConfigResultId(String configResultId) {
		this.configResultId = configResultId;
	}

	public String getConfigItemId() {
		return configItemId;
	}

	public void setConfigItemId(String configItemId) {
		this.configItemId = configItemId;
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

}