package com.mezhou887.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

import com.mezhou887.utils.SendMailUtil;
import com.mezhou887.utils.SpringContextHolder;

public class MailSenderJobListener  extends JobListenerSupport {
	
	public String getName() {
		return "mailSenderJobListener";
	}

	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		SendMailUtil sendMailUtil = SpringContextHolder.getBean("sendMailUtil");	
		String subject = "����״̬���";
		String message = "�ʼ�֪ͨ";
		
		sendMailUtil.sendCommonMail(subject, message);
	}

}
