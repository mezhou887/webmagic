package com.mezhou887.quartz.schedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.mezhou887.utils.FolderUtils;
import com.mezhou887.utils.PropertiesLoader;
import com.mezhou887.utils.UUIDGenerator;
import com.mezhou887.utils.ZipCompressor;

public class CompressorSchedule extends QuartzJobBean {
	
	private Logger logger = LoggerFactory.getLogger(getClass());	
	
	protected Calendar cal = Calendar.getInstance();

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String dataPath = PropertiesLoader.getProperties("dataPath");
		String exportPath = PropertiesLoader.getProperties("exportPath");
		String distinct = context.getJobDetail().getJobDataMap().getString("distinct");
		if(StringUtils.isBlank(distinct)) {
			// ȡ�����
			cal.add(Calendar.DATE,   -1);
		} else {
			try {
				Integer dist = Integer.parseInt(distinct);
				cal.add(Calendar.DATE, dist);
			} catch (NumberFormatException e) {
				logger.error("distinct �ֶ�ֵ��������: {}", e);
				// �������ȡ�����
				cal.add(Calendar.DATE, 0);
			}
		}
		
		String zipDay = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
		String folder = FolderUtils.setPath(dataPath) + zipDay;
		String zipFile = FolderUtils.setPath(exportPath)+UUIDGenerator.getAddress()+zipDay+".zip";
		ZipCompressor zc = new  ZipCompressor(zipFile);  
        zc.compressExe(folder); 
        String message = "�ļ���" + folder + "�Ѿ�ѹ�����ļ�"+zipFile+"�С�";
        context.getJobDetail().getJobDataMap().put("message", message+this.getClass().getName());		
        logger.info(message);
	}

}
