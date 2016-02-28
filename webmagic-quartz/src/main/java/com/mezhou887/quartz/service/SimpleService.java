package com.mezhou887.quartz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mezhou887.quartz.dao.CustomerDaoImpl;

@Service("simpleService")
@Transactional(readOnly = true)
public class SimpleService {

	private static final Logger logger = LoggerFactory.getLogger(SimpleService.class);

	@Autowired
	private CustomerDaoImpl customerDao;

	public void testMethod(String triggerName, String group) {
		customerDao.test();
		logger.info("AAAA:" + triggerName + "==" + group);

	}

	public void testMethod2(String triggerName, String group) {
		customerDao.backupDateabase();
		logger.info("BBBB:" + triggerName + "==" + group);
	}

}
