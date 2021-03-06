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
		String subject = "任务状态监控-邮件通知";
		String message = context.getJobDetail().getJobDataMap().getString("message");
		if(StringUtils.isBlank(message)) {
			message = "没有消息正文";
		}
		sendMailUtil.sendCommonMail(subject, message);
	}

}
