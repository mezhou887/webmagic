package com.mezhou887.quartz.schedule;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class OneHourSchedule  extends QuartzJobBean {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		 JobKey jobKey = context.getJobDetail().getKey();	        
		logger.info("JoName: {}  run at {}", jobKey.getName(), new Date().toString());
	}

}
