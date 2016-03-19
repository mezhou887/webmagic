package com.mezhou887.listener;

import org.apache.commons.lang.StringUtils;
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
		String subject = "����״̬���-�ʼ�֪ͨ";
		String message = context.getJobDetail().getJobDataMap().getString("message");
		if(StringUtils.isBlank(message)) {
			message = "û����Ϣ����";
		}
		sendMailUtil.sendCommonMail(subject, message);
	}

}
