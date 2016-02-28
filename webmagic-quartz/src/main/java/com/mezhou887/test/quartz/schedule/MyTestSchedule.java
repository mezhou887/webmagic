package com.mezhou887.test.quartz.schedule;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTestSchedule {
	
    private Logger logger = LoggerFactory.getLogger(getClass());
	
	public void printSomething(){
	    System.out.println("this is my test schedule date:" + new Date().toString());
	    logger.info("this is my test schedule date:" + new Date().toString());
	  }

}
