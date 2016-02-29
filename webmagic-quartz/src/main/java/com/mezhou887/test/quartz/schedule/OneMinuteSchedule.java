package com.mezhou887.test.quartz.schedule;

import java.util.Date;

import org.apache.log4j.Logger;

public class OneMinuteSchedule {
	
    private Logger logger = Logger.getLogger(getClass());
	
	public void exec(){
	    System.out.println("this is one minute schedule:" + new Date().toString());
	    logger.info("this is one minute schedule:" + new Date().toString());
	  }

}
