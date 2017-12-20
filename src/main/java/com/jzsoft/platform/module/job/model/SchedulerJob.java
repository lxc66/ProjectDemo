package com.jzsoft.platform.module.job.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class SchedulerJob implements java.io.Serializable {
	private static final long serialVersionUID = 1117304685101674236L;
	private String id;
	private String jobName;
	private String jobGroup;
	private String jobClass;
	private String jobMethod;
	private String status;
	private String description;
	private Integer num;
	
	private String ft;
	private String lt;
	private String fu;
	private String lu;

	public static final String DEPLOY_NO = "0";
	public static final String DEPLOY_YES = "1"; 
	
	public static final Map<String, String> getDeployFlagMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(DEPLOY_YES, "已部署");
		map.put(DEPLOY_NO, "未部署");
		return map;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getJobMethod() {
		return jobMethod;
	}

	public void setJobMethod(String jobMethod) {
		this.jobMethod = jobMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	
}