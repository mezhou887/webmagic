package com.mezhou887.quartz.schedule;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MysqlBackupSchedule extends BackupSchedule {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String user = "mezhou887";
	
	private String password = "Admin1234#";
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String name = "alldatabases_" + new SimpleDateFormat("yyyyMMddHHmmss").format(currentDate) + ".dump";
		String file = genRealPath() + name;
		String stmt = "mysqldump " + " -u" + user + " -p" + password + " --all-databases --default-character-set=utf8 --result-file=" + file; 
		
		try {
			Runtime.getRuntime().exec(stmt);
			logger.info("stmt: " + stmt);
			logger.info("数据已导出到文件" + file + "中, 时间: " + new Date().toString());
		} catch (IOException e) {
			logger.error("数据导出失败!" + new Date().toString(), e);
			throw new JobExecutionException(e);
		}
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

}
