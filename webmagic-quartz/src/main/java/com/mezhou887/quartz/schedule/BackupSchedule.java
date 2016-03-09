package com.mezhou887.quartz.schedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.mezhou887.utils.FolderUtils;
import com.mezhou887.utils.PropertiesLoader;

public abstract class BackupSchedule extends QuartzJobBean {
	
	protected Calendar cal = Calendar.getInstance();
	
	protected Logger logger = LoggerFactory.getLogger(getClass());		
	
	 //Ä¬ÈÏÂ·¾¶·Ö¸î·ûºÅ
	public static String PATH_SEPERATOR = "/";
	
	protected String getFolderPath(String pattern) {
		if(StringUtils.isBlank(pattern)){
			pattern = "yyyyMMdd";
		}
		String dataPath = PropertiesLoader.getProperties("dataPath");
		String folderPath = FolderUtils.setPath(dataPath) + FolderUtils.setPath(new SimpleDateFormat(pattern).format(cal.getTime()));
		FolderUtils.checkAndMakeDirecotry(folderPath);
		return folderPath ;
	}
	
	protected String getFolderPath() {
		return getFolderPath(null);
	}

}
