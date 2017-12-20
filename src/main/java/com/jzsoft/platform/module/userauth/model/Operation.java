package com.jzsoft.platform.module.userauth.model;



public class Operation implements java.io.Serializable {
	private static final long serialVersionUID = -8094389644619422983L;
	private String id;
	private String parentId;
	private String moduleId;
	private String name;
	private String nameI18n;
	private String code;
	private String type;
	private String customKind;
	private String url;
	private String openMode;
	private String datascopeFlag;
	private Integer num;
	private String icon;
	private String winHeight;
	private String winWidth;
	private String description;
	private String ft;
	private String lt;
	private String fu;
	private String lu;

	/** default constructor */
	public Operation() {
	}

	/** minimal constructor */
	public Operation(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameI18n() {
		return nameI18n;
	}

	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCustomKind() {
		return customKind;
	}

	public void setCustomKind(String customKind) {
		this.customKind = customKind;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOpenMode() {
		return openMode;
	}

	public void setOpenMode(String openMode) {
		this.openMode = openMode;
	}

	public String getDatascopeFlag() {
		return datascopeFlag;
	}

	public void setDatascopeFlag(String datascopeFlag) {
		this.datascopeFlag = datascopeFlag;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getWinHeight() {
		return winHeight;
	}

	public void setWinHeight(String winHeight) {
		this.winHeight = winHeight;
	}

	public String getWinWidth() {
		return winWidth;
	}

	public void setWinWidth(String winWidth) {
		this.winWidth = winWidth;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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