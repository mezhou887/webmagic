package com.mezhou887.test;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class LoggerSchedule extends QuartzJobBean {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("JobRunTikme: ", new Date(context.getJobRunTime()).toString());
		logger.info("JobKeyName: ", context.getJobDetail().getKey().getName());
		logger.info("JobKeyGroup: ", context.getJobDetail().getKey().getGroup());
		logger.info("TriggerKeyName: ", context.getTrigger().getKey().getName());
		logger.info("TriggerKeyGroup: ", context.getTrigger().getKey().getGroup());
        context.getJobDetail().getJobDataMap().put("message", this.getClass().getName()+"Ö´ÐÐÍê³É");	
	}

}
