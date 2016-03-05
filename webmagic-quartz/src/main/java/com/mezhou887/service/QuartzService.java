package com.mezhou887.service;

import static org.quartz.CronScheduleBuilder.cronSchedule;

import java.util.Date;
import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mezhou887.bean.JobInfoBean;
import com.mezhou887.bean.JobModel;
import com.mezhou887.dao.QuartzDAO;

@Service("quartzService")
public class QuartzService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private Scheduler scheduler;	
	
	@Autowired
	private QuartzDAO quartzDAO;

	//添加Job
	@SuppressWarnings("unchecked")
	public Boolean deploy(JobModel model) {

		try {
			Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(model.getJobClassName());
			JobDetail job = JobBuilder.newJob(clazz).withIdentity(model.getJobName(), model.getJobGroup()).build();
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(model.getTriggerGroup(), model.getJobGroup()).withSchedule(cronSchedule(model.getCronExpression())).build();
			Date runTime = scheduler.scheduleJob(job, trigger);
			logger.info( "{} has been scheduled to run at: {} and repeat based on expression: {}", job.getKey(), runTime, trigger.getCronExpression());
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
	public Boolean startNow() {
		return true;
	}
	
	public List<JobInfoBean> getAllJobs() {
		return quartzDAO.getAllQuartzJobs();
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
