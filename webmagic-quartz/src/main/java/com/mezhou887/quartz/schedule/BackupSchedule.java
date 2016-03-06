package com.mezhou887.quartz.schedule;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.scheduling.quartz.QuartzJobBean;

import com.mezhou887.utils.PropertiesLoader;

public abstract class BackupSchedule extends QuartzJobBean {
	
	protected Calendar cal = Calendar.getInstance();
	
	 //Ä¬ÈÏÂ·¾¶·Ö¸î·ûºÅ
	public static String PATH_SEPERATOR = "/";
	
	protected String genRealPath() {
		String basePath = PropertiesLoader.getProperties("basePath");
		String path = setPath(basePath) + setPath(new SimpleDateFormat("yyyyMMdd").format(cal.getTime()));
		checkAndMakeParentDirecotry(path);
		return path ;
	}

    public String setPath(String path) {
        if (!path.endsWith(PATH_SEPERATOR)) {
            path += PATH_SEPERATOR;
        }
        return path;
    }
    
    public void checkAndMakeParentDirecotry(String fullName) {
        int index = fullName.lastIndexOf(PATH_SEPERATOR);
        if (index > 0) {
            String path = fullName.substring(0, index);
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }
	
}
