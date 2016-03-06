/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mezhou887.utils;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * �Ծ�̬��������Spring ApplicationContext, �����κδ����κεط��κ�ʱ��ȡ��ApplicaitonContext.
 * 
 * @author Zaric
 * @date 2013-5-29 ����1:25:40
 */
@Service
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

	private static ApplicationContext applicationContext = null;

	private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

	/**
	 * ȡ�ô洢�ھ�̬�����е�ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return applicationContext;
	}

	/**
	 * �Ӿ�̬����applicationContext��ȡ��Bean, �Զ�ת��Ϊ����ֵ���������.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * �Ӿ�̬����applicationContext��ȡ��Bean, �Զ�ת��Ϊ����ֵ���������.
	 */
	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}

	/**
	 * ���SpringContextHolder�е�ApplicationContextΪNull.
	 */
	public static void clearHolder() {
		if (logger.isDebugEnabled()){
			logger.debug("���SpringContextHolder�е�ApplicationContext:" + applicationContext);
		}
		applicationContext = null;
	}

	/**
	 * ʵ��ApplicationContextAware�ӿ�, ע��Context����̬������.
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextHolder.applicationContext = applicationContext;
	}

	/**
	 * ʵ��DisposableBean�ӿ�, ��Context�ر�ʱ����̬����.
	 */
	public void destroy() throws Exception {
		SpringContextHolder.clearHolder();
	}

	/**
	 * ���ApplicationContext��Ϊ��.
	 */
	private static void assertContextInjected() {
		Validate.validState(applicationContext != null, "applicaitonContext����δע��, ����applicationContext.xml�ж���SpringContextHolder.");
	}
}