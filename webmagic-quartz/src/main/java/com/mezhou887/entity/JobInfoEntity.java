package com.mezhou887.entity;

import java.io.Serializable;

public class JobInfoEntity implements Serializable {

	private static final long serialVersionUID = 4245640887564476204L;
	
	private String jobName;
	private String triggerName;
	private String triggerGroup = "DEFAULT";
	private String jobGroup = "DEFAULT";
	
	private String jobDescription;
	private String jobClassName;
	private String cronExpression;
	private String startTime;
	private String endTime;
	private String nextFireTime;
	private String prevFireTime;
	
	private String triggerState;
	
	public String getTriggerState() {
		return triggerState;
	}
	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}
	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public String getTriggerGroup() {
		return triggerGroup;
	}
	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
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
	public String getJobClassName() {
		return jobClassName;
	}
	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
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
	public String getNextFireTime() {
		return nextFireTime;
	}
	public void setNextFireTime(String nextFireTime) {
		this.nextFireTime = nextFireTime;
	}
	public String getPrevFireTime() {
		return prevFireTime;
	}
	public void setPrevFireTime(String prevFireTime) {
		this.prevFireTime = prevFireTime;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	@Override
	public String toString() {
		return "JobInfoEntity [jobName=" + jobName + ", triggerName=" + triggerName + ", triggerGroup=" + triggerGroup
				+ ", jobGroup=" + jobGroup + ", jobDescription=" + jobDescription + ", jobClassName=" + jobClassName
				+ ", cronExpression=" + cronExpression + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", nextFireTime=" + nextFireTime + ", prevFireTime=" + prevFireTime + ", triggerState=" + triggerState
				+ "]";
	}	
	
}
