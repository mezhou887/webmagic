package com.mezhou887.quartz.test;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTestSchedule {
	
    private Logger logger = LoggerFactory.getLogger(getClass());
	
	public void printSomething(){
	    logger.info("this is my test schedule date:" + new Date().toString());
	  }

}
