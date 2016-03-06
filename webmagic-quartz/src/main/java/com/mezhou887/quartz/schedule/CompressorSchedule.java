package com.mezhou887.quartz.schedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.mezhou887.utils.PropertiesLoader;
import com.mezhou887.utils.UUIDGenerator;
import com.mezhou887.utils.ZipCompressor;

public class CompressorSchedule extends QuartzJobBean {
	
	private Logger logger = LoggerFactory.getLogger(getClass());	
	
	protected Date currentDate = new Date();

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String basePath = PropertiesLoader.getProperties("basePath");
		String targetPath = PropertiesLoader.getProperties("targetPath");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,   -1);
		String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
		String folder = basePath + yesterday;
		String zipFile = targetPath+UUIDGenerator.getAddress()+yesterday+".zip";
		ZipCompressor zc = new  ZipCompressor(zipFile);  
        zc.compressExe(folder); 
        logger.info("文件夹{}压缩到{}中, 时间: {}。",folder, zipFile, new Date().toString());
	}

}
