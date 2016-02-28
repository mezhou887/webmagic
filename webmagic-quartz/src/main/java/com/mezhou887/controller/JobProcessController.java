package com.mezhou887.controller;

import org.quartz.Scheduler;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mezhou887.utils.SpringContextHolder;

@Controller
public class JobProcessController {
	
	private Scheduler scheduler;

	public JobProcessController() {
		scheduler = SpringContextHolder.getBean("factoryBean", SchedulerFactoryBean.class).getScheduler();
	}

	@RequestMapping(value = "/index")
	public String index(Model model) {
		model.addAttribute("message", "Hello World!!!");
		return "index";
	}

}
