package com.jzsoft.platform.module.job.model;


public class SchedulerTrigger implements java.io.Serializable {
	private static final long serialVersionUID = 1117304685101674236L;
	private String id;
	private String jobId;
	private SchedulerJob Job;
	private String cronExpression;
	private String startTime;
	private String endTime;
	private String status;
	private String description;
	private Integer num;
	
	private String ft;
	private String lt;
	private String fu;
	private String lu;

	private QrtzTriggers qrtzTriggers;
	private String triggerStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public SchedulerJob getJob() {
		return Job;
	}

	public void setJob(SchedulerJob job) {
		Job = job;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public String getTriggerGroup(){
		return Job.getJobGroup();
	}
	
	public String getTriggerName(){
		return Job.getJobName()+"_"+id;
	}
	
	public void setQrtzTriggers(QrtzTriggers qrtzTriggers) {
		this.qrtzTriggers = qrtzTriggers;
	}
	
	public QrtzTriggers getQrtzTriggers() {
		return qrtzTriggers;
	}

	public String getTriggerStatus(){
		if(null==qrtzTriggers)return "未部署";
		return qrtzTriggers.getStatusView();
	}
}