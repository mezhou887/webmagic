package com.mezhou887.quartz.schedule;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mezhou887.utils.PropertiesLoader;

/**
 * ʹ��Linux�Ĺܵ������window�²�����
 * @author Administrator
 *
 */
public class MysqlBackupAndCompressorSchedule extends BackupSchedule {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String user = PropertiesLoader.getProperties("jdbc.username");
		String password = PropertiesLoader.getProperties("jdbc.password");
		String name = "alldatabases_" + new SimpleDateFormat("yyyyMMddHHmmss").format(cal.getTime()) + ".dump.gz";
		String file = genRealPath() + name;
		String stmt = "mysqldump " + " -u" + user + " -p" + password + " --all-databases --default-character-set=utf8  | gzip >" + file; 
		
		try {
			Runtime.getRuntime().exec(stmt);
			logger.info("stmt: " + stmt);
			logger.info("�����ѵ�������ѹ�����ļ�" + file + "��, ʱ��: " + new Date().toString());
		} catch (IOException e) {
			logger.error("���ݵ���ʧ��!" + new Date().toString(), e);
			throw new JobExecutionException(e);
		}
	}	

}
