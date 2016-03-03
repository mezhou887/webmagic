package com.mezhou887.quartz.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.MethodInvokingJob;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MailProcessor  extends QuartzJobBean {
	
	MailSender sender;
	
	SimpleMailMessage mailMessage;
	
	public void exec() {
		
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
	}
	
	
	

}
