package com.mezhou887.quartz.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.mezhou887.utils.SpringContextHolder;

@Service
public class MailSchedule  extends QuartzJobBean {
	
	private MailSender sender;
	
	private SimpleMailMessage message;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println(sender);
		System.out.println(message);
		sender = SpringContextHolder.getBean("sender");
		message = SpringContextHolder.getBean("message");
		message.setText("Test");
		sender.send(message);
	}

	public MailSender getSender() {
		return sender;
	}

	public void setSender(MailSender sender) {
		this.sender = sender;
	}

	public SimpleMailMessage getMessage() {
		return message;
	}

	public void setMessage(SimpleMailMessage message) {
		this.message = message;
	}

}
