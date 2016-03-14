package com.mezhou887.quartz.schedule;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.mezhou887.utils.PropertiesLoader;

/**
 * 使用Linux的管道命令执行mysqldump命令，该任务在window下不可用
 * @author Administrator
 */
public class MysqlDumpAndCompressorSchedule extends BackupSchedule {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String user = PropertiesLoader.getProperties("jdbc.username");
		String password = PropertiesLoader.getProperties("jdbc.password");
		String name = "alldatabases_" + new SimpleDateFormat("yyyyMMddHHmm").format(cal.getTime()) + ".dump.gz";
		String file = getFolderPath() + name;
		String stmt = "mysqldump " + " -u" + user + " -p" + password + " --all-databases --default-character-set=utf8  | gzip >" + file; 
		
		try {
			Runtime.getRuntime().exec(stmt);
			logger.info("stmt: " + stmt);
			String message = "数据已导出到并压缩到文件" + file + "中, 时间: " + new Date().toString();
			context.getJobDetail().getJobDataMap().put("message", message+". class: "+this.getClass().getName());
			logger.info(message);
		} catch (IOException e) {
			logger.error("数据导出失败!" + new Date().toString(), e);
			throw new JobExecutionException(e);
		}
	}	

}
