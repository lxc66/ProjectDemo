package com.jzsoft.platform.module.userauth.model;



public class RolePrivilege implements java.io.Serializable {
	private static final long serialVersionUID = 828679044247570705L;
	private String id;
	private String roleId;
	private String privilegeId;
	private String ft;
	private String lt;
	private String fu;
	private String lu;

	/** default constructor */
	public RolePrivilege() {
	}

	/** minimal constructor */
	public RolePrivilege(String id) {
		this.id = id;
	}

	public RolePrivilege(String roleId, String privilegeId) {
		super();
		this.roleId = roleId;
		this.privilegeId = privilegeId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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