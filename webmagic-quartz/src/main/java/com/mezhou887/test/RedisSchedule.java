package com.mezhou887.test;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * ���������������Redis�Ƿ���õ�
 * @author Administrator
 */
public class RedisSchedule extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

	}

}
