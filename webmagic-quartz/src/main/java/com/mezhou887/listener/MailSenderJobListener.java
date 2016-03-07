package com.mezhou887.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.mezhou887.utils.SimpleMail;

public class MailSenderJobListener  extends JobListenerSupport {
	
	@Autowired	
	SimpleMail simpleMail;	

	public String getName() {
		return "mailSenderJobListener";
	}

	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		String text = "Job: " + context.getJobDetail().getKey().getName()+" 已经完成运行。";
		simpleMail.sendMessage(text);
	}

}
