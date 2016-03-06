package com.mezhou887.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.mezhou887.utils.SpringContextHolder;

public class MailSenderJobListener  extends JobListenerSupport {

	public String getName() {
		return "mailSenderJobListener";
	}

	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		MailSender sender = SpringContextHolder.getBean("sender");
		SimpleMailMessage message = SpringContextHolder.getBean("message");
		String text = "Job: " + context.getJobDetail().getKey().getName()+" 已经完成运行。";
		message.setText(text);
		sender.send(message);
	}

}
