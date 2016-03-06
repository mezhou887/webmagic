package com.mezhou887.quartz.schedule;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.mezhou887.utils.ZipCompressor;

public class CompressorSchedule extends QuartzJobBean {
	
	private Logger logger = LoggerFactory.getLogger(getClass());	

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String file = "E:\\Crawler\\data\\20160306\\";
		ZipCompressor zc = new  ZipCompressor("E:\\Crawler\\export\\20160306.zip");  
        zc.compressExe(file); 
        logger.info("数据压缩到" + file + "中, 时间: " + new Date().toString());
	}

}
