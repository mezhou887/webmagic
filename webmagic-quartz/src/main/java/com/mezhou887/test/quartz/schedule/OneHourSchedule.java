package com.mezhou887.test.quartz.schedule;

import java.util.Date;

import org.apache.log4j.Logger;

public class OneHourSchedule {
	
    private Logger logger = Logger.getLogger(getClass());
	
	public void exec(){
	    System.out.println("this is one hour schedule:" + new Date().toString());
	    logger.info("this is one hour schedule:" + new Date().toString());
	  }

}
