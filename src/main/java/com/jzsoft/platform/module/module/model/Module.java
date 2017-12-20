package com.jzsoft.platform.module.module.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Module implements java.io.Serializable {
	private static final long serialVersionUID = -314739318571189516L;
	private String id;
	private String name;
	private String nameI18n;
	private String code;
	private String icon;
	private Integer num;
	private String deployFlag;
	private String ft;
	private String lt;
	private String fu;
	private String lu;

	/** 模块部署标志 */
	public static final String DEPLOY_NO = "0";
	public static final String DEPLOY_YES = "1"; 
	
	/**
	 * 模块部署标志Map
	 */
	public static final Map<String, String> getDeployFlagMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(DEPLOY_YES, "已部署");
		map.put(DEPLOY_NO, "未部署");
		return map;
	}
	
	//--自定义属性
	private String deployFlagView;
	
	public String getDeployFlagView() {
		deployFlagView = Module.getDeployFlagMap().get(deployFlag);
		return deployFlagView;
	}
	
	

	public void setDeployFlagView(String deployFlagView) {
		this.deployFlagView = deployFlagView;
	}



	/** default constructor */
	public Module() {
	}

	/** minimal constructor */
	public Module(String id) {
		this.id = id;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
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
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getFt() {
		return this.ft;
	}

	public void setFt(String ft) {
		this.ft = ft;
	}

	public String getLt() {
		return this.lt;
	}

	public void setLt(String lt) {
		this.lt = lt;
	}

	public String getFu() {
		return this.fu;
	}

	public void setFu(String fu) {
		this.fu = fu;
	}

	public String getLu() {
		return this.lu;
	}

	public void setLu(String lu) {
		this.lu = lu;
	}

	public String getDeployFlag() {
		return deployFlag;
	}

	public void setDeployFlag(String deployFlag) {
		this.deployFlag = deployFlag;
	}

	public boolean isDeployed(){
		return Module.DEPLOY_YES.equals(deployFlag);
	}
	
	public int getOrderNum(){
		return num!=null?num:0;
	}
}