package com.mezhou887.test;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.mezhou887.jms.test.JMSTestMessageSender;
import com.mezhou887.utils.SpringContextHolder;

public class MQSenderSchedule extends QuartzJobBean {
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JMSTestMessageSender sender = SpringContextHolder.getBean("testMessageSender");
		sender.send("queue", "message" + new Date());
	}

}
