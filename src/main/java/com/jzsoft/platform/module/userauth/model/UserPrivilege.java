package com.jzsoft.platform.module.userauth.model;



public class UserPrivilege implements java.io.Serializable {
	private static final long serialVersionUID = 8315067874307268782L;
	private String id;
	private String userId;
	private String privilegeId;
	private String ft;
	private String lt;
	private String fu;
	private String lu;

	/** default constructor */
	public UserPrivilege() {
	}

	/** minimal constructor */
	public UserPrivilege(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public UserPrivilege(String userId, String privilegeId) {
		super();
		this.userId = userId;
		this.privilegeId = privilegeId;
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

	public String getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(String privilegeId) {
		this.privilegeId = privilegeId;
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