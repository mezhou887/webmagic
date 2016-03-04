package com.mezhou887.test.quartz.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseModelJob implements Job {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Test ParseModelJob");
		logger.info("Test ParseModelJob");;
	}

}
