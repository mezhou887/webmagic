package com.mezhou887.quartz.schedule;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackupDBProcessor {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	 //默认路径分割符号
	public static String PATH_SEPERATOR = "/";
	
	private String schema;
	
	private String user;
	
	private String password;
	
	private String basePath;

	
	public void exec() {
		
		Date currentDate = new Date();
		String path = setPath(basePath) + setPath(new SimpleDateFormat("yyyyMM").format(currentDate));
		checkAndMakeParentDirecotry(path);
		String filePath = path + schema +"_"+ new SimpleDateFormat("yyyyMMddHHmmss").format(currentDate) + ".dump";
		
		String stmt = "C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump " + schema +" -u" + user + " -p" + password + " --default-character-set=utf8 --result-file=" + filePath; 
		
		try {
			Runtime.getRuntime().exec(stmt);
			logger.info("stmt: " + stmt);
			logger.info("数据已导出到文件" + filePath + "中, 时间: " + new Date().toString());
		} catch (IOException e) {
			logger.error("数据导出失败!" + new Date().toString(), e);
			e.printStackTrace();
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

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
    
}
