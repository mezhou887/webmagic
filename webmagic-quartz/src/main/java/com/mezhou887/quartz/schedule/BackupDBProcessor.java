package com.mezhou887.quartz.schedule;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackupDBProcessor {
private Logger logger = LoggerFactory.getLogger(getClass());
	
	public void exec(){
	    logger.info("this is one hour schedule:" + new Date().toString());
	  }
}
