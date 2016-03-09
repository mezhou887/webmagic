package com.mezhou887.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component("sendMailUtil") 
public class SendMailUtil {
	
	@Autowired
	MailSender sender;
	
	@Autowired
	SimpleMailMessage message;
	
	/**
	 * ·¢ËÍÆÕÍ¨ÓÊ¼þ
	 * @param text
	 */
	public void sendCommonMail(String subject, String message) {
		
	}

}
