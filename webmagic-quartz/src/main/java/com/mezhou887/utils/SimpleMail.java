package com.mezhou887.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class SimpleMail {
	
	@Autowired
	MailSender sender;
	
	@Autowired
	SimpleMailMessage message;
	
	public void sendMessage(String text) {
		if(StringUtils.isNotBlank(text)) {
			message.setText(text);
		}
		sender.send(message);
	}

}
