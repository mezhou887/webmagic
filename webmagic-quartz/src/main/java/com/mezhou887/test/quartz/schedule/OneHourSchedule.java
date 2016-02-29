package com.mezhou887.test.quartz.schedule;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OneHourSchedule {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public void exec(){
	    logger.info("this is one hour schedule:" + new Date().toString());
	  }

}
