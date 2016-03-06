package com.mezhou887.quartz.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.mezhou887.utils.SpringContextHolder;

public class MailSchedule  extends QuartzJobBean {
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		MailSender sender = SpringContextHolder.getBean("sender");
		SimpleMailMessage message = SpringContextHolder.getBean("message");
		sender.send(message);
	}

}
