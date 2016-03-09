package com.mezhou887.jms.test;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JMSTestMessageListener implements MessageListener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	public void onMessage(Message message) {
		try {
			logger.info("TestMessageListener 接收到消息: {}, 时间: {}。", ((TextMessage)message).getText(), new Date().toString());
		} catch (JMSException e) {
			logger.error("TestMessageListener 接收消息失败!" + new Date().toString(), e);
		}
	}

}
