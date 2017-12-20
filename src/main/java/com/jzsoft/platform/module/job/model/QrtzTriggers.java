package com.jzsoft.platform.module.job.model;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jzsoft.platform.util.DateUtil;

public class QrtzTriggers implements java.io.Serializable {
	private static final long serialVersionUID = 6022035509389173405L;
	private String jobGroup;
	private String jobName;
	private String triggerName;
	private Long nextFireTime;
	private Long prevFireTime;
	private Long startTime;
	private Long endTime;
	private String status;

	public static final String STATUS_WAITING = "WAITING";
	public static final String STATUS_PAUSED = "PAUSED";
	public static final String STATUS_ACQUIRED = "ACQUIRED";
	
	public static final Map<String, String> getTriggerStatusMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(STATUS_WAITING, "等待中");
		map.put(STATUS_PAUSED, "暂停");
		map.put(STATUS_ACQUIRED, "执行中");
		return map;
	}
	
	private String statusView;
	
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

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getNextFireTime() {
		if(null==nextFireTime)return "";
		return DateUtil.getDateSecondString(new Date(nextFireTime));
	}

	public void setNextFireTime(Long nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public String getPrevFireTime() {
		if(null==prevFireTime)return "";
		if(prevFireTime==-1){
			return "";
		}
		return DateUtil.getDateSecondString(new Date(prevFireTime));
	}

	public void setPrevFireTime(Long prevFireTime) {
		this.prevFireTime = prevFireTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartTime() {
		if(null==startTime)return "";
		return DateUtil.getDateSecondString(new Date(startTime));
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		if(null==endTime)return "";
		if(endTime==0){
			return "";
		}
		return DateUtil.getDateSecondString(new Date(endTime));
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	
	public String getStatusView(){
		return getTriggerStatusMap().get(status);
	}
}