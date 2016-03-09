package com.mezhou887.service;

import static org.quartz.CronScheduleBuilder.cronSchedule;

import java.util.Date;
import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mezhou887.dao.QuartzMapper;
import com.mezhou887.entity.JobInfoEntity;
import com.mezhou887.entity.JobModel;
import com.mezhou887.listener.MailSenderJobListener;
import com.mezhou887.utils.UUIDGenerator;

@Service("quartzService")
public class QuartzService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private Scheduler scheduler;	
	
	@Autowired
	private QuartzMapper quartzMapper;

	//添加Job
	@SuppressWarnings("unchecked")
	public Boolean deploy(JobModel model) {
		String jobGroup = "JobGroup"+UUIDGenerator.genUUID();
		String triggetGroup = "Trigger"+ UUIDGenerator.genUUID();

		try {
			Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(model.getJobClassName());
			JobDetail job = JobBuilder.newJob(clazz).withIdentity(model.getJobName(), jobGroup).build();
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(model.getTriggerName(), triggetGroup).withSchedule(cronSchedule(model.getCronExpression())).build();
			if(model.isListener()) {
				JobListener listener = new MailSenderJobListener();
				scheduler.getListenerManager().addJobListener(listener);				
			}
			
			Date runTime = scheduler.scheduleJob(job, trigger);
			logger.info( "{} has been scheduled to run at: {} and repeat based on expression: "+ trigger.getCronExpression(), job.getKey(), runTime);
			return true;
			
		} catch (ClassNotFoundException e) {
			logger.error("[ClassNotFoundException at deploy]: ", e);
		} catch (SchedulerException e) {
			logger.error("[SchedulerException at deploy]: ", e);
		}
		return false;
    }
	
	//删除Job
	public Boolean unDeploy(JobKey jobKey) {
		try {
			scheduler.deleteJob(jobKey);
			return true;
		} catch (SchedulerException e) {
			logger.error("[SchedulerException at unDeploy]: ", e);
		}
		return false;
	}
	
	//暂停一个Job
	public Boolean pause(JobKey jobKey) {
		try {
			scheduler.pauseJob(jobKey);
			return true;
		} catch (SchedulerException e) {
			logger.error("[SchedulerException at pause]: ", e);
		}
		return false;
	}
	
	//恢复一个Job
	public Boolean resume(JobKey jobKey) {
		try {
			scheduler.resumeJob(jobKey);
			return true;
		} catch (SchedulerException e) {
			logger.error("[SchedulerException at resume]: ", e);
		}
		return false;
	}
	
	//立刻运行
	public Boolean startNow(JobKey jobKey) {
		try {
			scheduler.triggerJob(jobKey);
			return true;
		} catch (SchedulerException e) {
			logger.error("[SchedulerException at startNow]: ", e);
		}
		return false;
	}
	
	public List<JobInfoEntity> getAllJobs() {
		return quartzMapper.selectAll();
	}	
	
	public Boolean shutdown(Boolean waitForJobsToComplete) {
		try {
			if(waitForJobsToComplete == null) {
				scheduler.shutdown();
			}
			if(!scheduler.isShutdown()) {
				scheduler.shutdown(waitForJobsToComplete);		
			}
			return true;
		} catch (SchedulerException e) {
			logger.error("[SchedulerException at shutdown]: ", e);
		}
		return false;
	}

}
