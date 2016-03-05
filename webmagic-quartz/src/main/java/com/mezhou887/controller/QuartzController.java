package com.mezhou887.controller;

import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mezhou887.bean.JobModel;
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
	
	@RequestMapping(value="/add")
	public @ResponseBody Boolean deploy() {
		JobModel model = new JobModel();
		model.setJobName("job1");
		model.setJobClassName("com.mezhou887.controller.SimpleJob");
		model.setCronExpression("0/20 * * * * ?");
		return quartzService.deploy(model);
	}
	
	@RequestMapping(value="/remove")
	public @ResponseBody Boolean unDeploy(String jobName, String jobGroup) {
		JobKey jobKey = new JobKey(jobName, jobGroup);
		return quartzService.unDeploy(jobKey);
	}
	
	@RequestMapping(value="/run")
	public @ResponseBody Boolean startNow() {
		return quartzService.startNow();
		
	}
	
	
	
	
}
