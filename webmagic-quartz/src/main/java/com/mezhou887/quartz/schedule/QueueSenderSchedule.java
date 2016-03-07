package com.mezhou887.quartz.schedule;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.mezhou887.queue.TestMessageSender;
import com.mezhou887.utils.SpringContextHolder;

public class QueueSenderSchedule extends QuartzJobBean {
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		TestMessageSender sender = SpringContextHolder.getBean("testMessageSender");
		sender.send("queue", "message" + new Date());
	}

}
