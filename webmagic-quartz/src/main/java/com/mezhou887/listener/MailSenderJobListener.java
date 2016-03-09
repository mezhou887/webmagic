package com.mezhou887.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

import com.mezhou887.utils.SimpleMail;
import com.mezhou887.utils.SpringContextHolder;

public class MailSenderJobListener  extends JobListenerSupport {
	
	public String getName() {
		return "mailSenderJobListener";
	}

	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		SimpleMail simpleMail = SpringContextHolder.getBean("simpleMail");	
		String text = "JobName: " + context.getJobDetail().getKey().getName()+" 已经完成运行, TriggerName是: "+context.getTrigger().getKey().getName()+" 执行时间是: " + context.getTrigger().getPreviousFireTime();
		simpleMail.sendMessage(text);
	}

}
