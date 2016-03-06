package com.mezhou887.controller;

import java.util.HashMap;
import java.util.Map;

import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mezhou887.entity.JobModel;
import com.mezhou887.service.QuartzService;

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
	public @ResponseBody Map<String,Object> deploy(String jobName, String triggerName, String jobClassName, String cronExpression) {
		Map<String,Object> map = new HashMap<String,Object>();
		JobModel model = new JobModel();
		model.setJobName(jobName);
		model.setTriggerName(triggerName);
		model.setJobClassName(jobClassName); 
		model.setCronExpression(cronExpression);
		map.put("status", quartzService.deploy(model));
		return map;
	}
	
	@RequestMapping(value="/remove")
	public @ResponseBody Map<String,Object> unDeploy(String jobName, String jobGroup) {
		Map<String,Object> map = new HashMap<String,Object>();
		JobKey jobKey = new JobKey(jobName, jobGroup);
		map.put("status", quartzService.unDeploy(jobKey));
		return map;
	}
	
	@RequestMapping(value="/pause")
	public @ResponseBody Map<String,Object> pause(String jobName, String jobGroup) {
		Map<String,Object> map = new HashMap<String,Object>();
		JobKey jobKey = new JobKey(jobName, jobGroup);
		map.put("status", quartzService.pause(jobKey));
		return map;
	}
	
	@RequestMapping(value="/resume")
	public @ResponseBody Map<String,Object> resume(String jobName, String jobGroup) {
		Map<String,Object> map = new HashMap<String,Object>();
		JobKey jobKey = new JobKey(jobName, jobGroup);
		map.put("status", quartzService.resume(jobKey));
		return map;
	}	
	
	@RequestMapping(value="/run")
	public @ResponseBody Map<String,Object> startNow(String jobName, String jobGroup) {
		Map<String,Object> map = new HashMap<String,Object>();
		JobKey jobKey = new JobKey(jobName, jobGroup);
		map.put("status", quartzService.startNow(jobKey));
		return map;
	}
	
	@RequestMapping(value="/shutdown")
	public @ResponseBody Map<String,Object> shutdown() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", quartzService.shutdown(true));
		return map;
	}
	
}
