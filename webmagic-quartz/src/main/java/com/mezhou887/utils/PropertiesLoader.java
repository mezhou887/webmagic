package com.mezhou887.utils;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertiesLoader extends PropertyPlaceholderConfigurer {

	private static Properties properties;
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
		properties = props;
		super.processProperties(beanFactoryToProcess, props);
	}

	public static String getProperties(String propertyName){
		return properties.getProperty(propertyName);
	}
}
