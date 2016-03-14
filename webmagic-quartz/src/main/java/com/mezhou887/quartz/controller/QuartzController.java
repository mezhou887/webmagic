package com.mezhou887.quartz.controller;

import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mezhou887.entity.JobModel;
import com.mezhou887.quartz.service.QuartzService;

@Controller
@RequestMapping("/quartz")
public class QuartzController {
	
	@Autowired
	private QuartzService quartzService;	
	
	@RequestMapping(value = "/index")
	public String index(Model model) {
		model.addAttribute("jobs", quartzService.getAllJobs());
		return "index";
	}
	
	@RequestMapping(value = "/toAdd")
	public String toAdd(Model model) {
		return "add";
	}	
	
	@RequestMapping(value="/add")
	public @ResponseBody Boolean deploy(String jobName, String triggerName, String jobClassName, String cronExpression, Boolean listener) {
		JobModel model = new JobModel();
		model.setJobName(jobName);
		model.setTriggerName(triggerName);
		model.setJobClassName(jobClassName); 
		model.setCronExpression(cronExpression);
		if(listener != null) {
			model.setListener(true);			
		}else {
			model.setListener(false);						
		}
		return quartzService.deploy(model);
	}
	
	@RequestMapping(value="/remove")
	public @ResponseBody Boolean unDeploy(String jobName, String jobGroup) {
		JobKey jobKey = new JobKey(jobName, jobGroup);
		return quartzService.unDeploy(jobKey);
	}
	
	@RequestMapping(value="/pause")
	public @ResponseBody Boolean pause(String jobName, String jobGroup) {
		JobKey jobKey = new JobKey(jobName, jobGroup);
		return quartzService.pause(jobKey);
	}
	
	@RequestMapping(value="/resume")
	public @ResponseBody Boolean resume(String jobName, String jobGroup) {
		JobKey jobKey = new JobKey(jobName, jobGroup);
		return quartzService.resume(jobKey);
	}	
	
	@RequestMapping(value="/run")
	public @ResponseBody Boolean startNow(String jobName, String jobGroup) {
		JobKey jobKey = new JobKey(jobName, jobGroup);
		return quartzService.startNow(jobKey);
	}
	
}
