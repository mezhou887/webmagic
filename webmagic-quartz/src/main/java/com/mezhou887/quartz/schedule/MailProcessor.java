package com.mezhou887.quartz.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MailProcessor  extends QuartzJobBean {
	
	@Autowired
	MailSender mailSender;
	
	@Autowired
	SimpleMailMessage simpleMailMessage;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
	}

}
