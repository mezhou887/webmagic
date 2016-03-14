package com.mezhou887.jms.test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component("testMessageSender")
public class JMSTestMessageSender {
	
	private Logger logger = LoggerFactory.getLogger(getClass());	
	
	private String prefix="test.";
	
	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jmsTemplate;
	
	public void send(String queueName,final String message){
		queueName = prefix+queueName;
		jmsTemplate.send(queueName, new MessageCreator() {
			
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
		logger.info("发送一条消息到测试队列{}上", queueName);
	}
	
}
