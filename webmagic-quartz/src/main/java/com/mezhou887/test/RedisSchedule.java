package com.mezhou887.test;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 这个类是用来测试Redis是否可用的
 * @author Administrator
 */
public class RedisSchedule extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

	}

}
