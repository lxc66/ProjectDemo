package com.jzsoft.platform.module.userauth.model;

import org.apache.commons.lang.StringUtils;

import com.jzsoft.platform.module.module.model.Module;



public class Privilege implements java.io.Serializable {
	private static final long serialVersionUID = 1394897289224409932L;
	private String id;
	private String parentId;
	private String moduleId;
	private Privilege parent;
	private Module module;
	private String name;
	private String code;
	private String type;
	private String url;
	private String description;
	private Integer num;
	private String ft;
	private String lt;
	private String fu;
	private String lu;

	/** default constructor */
	public Privilege() {
	}

	/** minimal constructor */
	public Privilege(String id) {
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
	
	public Privilege getParent() {
		return parent;
	}

	public void setParent(Privilege parent) {
		this.parent = parent;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
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


	public boolean isExistsParent() {
		if (StringUtils.isNotEmpty(this.getParentId())) {
			return true;
		}
		return false;
	}

}