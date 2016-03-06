package com.mezhou887.entity;

public class JobModel {
	
	private String jobName;
	private String triggerName;
	private String jobClassName;
	private String cronExpression;
	private boolean isListener = false;
	
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public boolean isListener() {
		return isListener;
	}

	public void setListener(boolean isListener) {
		this.isListener = isListener;
	}
	
}
