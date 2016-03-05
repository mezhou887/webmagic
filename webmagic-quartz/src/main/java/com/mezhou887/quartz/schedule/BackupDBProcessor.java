package com.mezhou887.quartz.schedule;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class BackupDBProcessor extends QuartzJobBean {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	 //Ĭ��·���ָ����
	public static String PATH_SEPERATOR = "/";
	
	private String schema;
	
	private String user;
	
	private String password;
	
	private String basePath;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		Date currentDate = new Date();
		String path = setPath(basePath) + setPath(new SimpleDateFormat("yyyyMMdd").format(currentDate));
		checkAndMakeParentDirecotry(path);
		String filePath = path + schema +"_"+ new SimpleDateFormat("yyyyMMddHHmmss").format(currentDate) + ".dump";
		
		String stmt = "C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump " + schema +" -u" + user + " -p" + password + " --default-character-set=utf8 --result-file=" + filePath; 
		
		try {
			Runtime.getRuntime().exec(stmt);
			logger.info("stmt: " + stmt);
			logger.info("�����ѵ������ļ�" + filePath + "��, ʱ��: " + new Date().toString());
		} catch (IOException e) {
			logger.error("���ݵ���ʧ��!" + new Date().toString(), e);
			throw new JobExecutionException(e);
		}
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

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

}