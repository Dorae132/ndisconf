package com.nsb.ndisconf.nsb_test;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

//@Configuration
//@ConditionalOnProperty(prefix = "disconf", value = {"properties", "env"})
public class DisAutoConfiguration implements ApplicationContextAware{

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Bean
	public PropertySourcesPlaceholderConfigurer disconf() {
		Environment environment = applicationContext.getEnvironment();
		String env = environment.getProperty("disconf.env");
		PropertySourcesPlaceholderConfigurer result = new PropertySourcesPlaceholderConfigurer();
		result.setIgnoreResourceNotFound(true);
		result.setIgnoreUnresolvablePlaceholders(true);
		result.setOrder(1);
		return result;
	}
}
