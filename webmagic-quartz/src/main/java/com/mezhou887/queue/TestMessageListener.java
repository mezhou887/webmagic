package com.mezhou887.queue;


import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMessageListener implements MessageListener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	public void onMessage(Message message) {
		try {
			logger.info("TestMessageListener ���յ���Ϣ: {}", ((TextMessage)message).getText());
		} catch (JMSException e) {
			logger.error("������Ϣʧ��!" + new Date().toString(), e);
		}
	}

}
