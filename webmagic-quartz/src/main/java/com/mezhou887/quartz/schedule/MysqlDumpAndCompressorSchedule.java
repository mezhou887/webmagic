package com.mezhou887.quartz.schedule;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.mezhou887.utils.PropertiesLoader;

/**
 * ʹ��Linux�Ĺܵ�����ִ��mysqldump�����������window�²�����
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
			String message = "�����ѵ�������ѹ�����ļ�" + file + "��, ʱ��: " + new Date().toString();
			context.getJobDetail().getJobDataMap().put("message", message+". class: "+this.getClass().getName());
			logger.info(message);
		} catch (IOException e) {
			logger.error("���ݵ���ʧ��!" + new Date().toString(), e);
			throw new JobExecutionException(e);
		}
	}	

}
