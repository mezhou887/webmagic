package com.mezhou887.test;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * ���������������Mongodb�Ƿ���õ�
 * @author Administrator
 */
public class MongodbSchedule  extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

	}
	
}