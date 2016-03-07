package com.mezhou887.quartz.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.mezhou887.utils.SimpleMail;
import com.mezhou887.utils.SpringContextHolder;

public class MailSchedule  extends QuartzJobBean {
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		SimpleMail simpleMail = SpringContextHolder.getBean("simpleMail");	
		String text = "���Ͳ����ʼ���";
		simpleMail.sendMessage(text);
	}

}
