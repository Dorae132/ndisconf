package com.nsb.ndisconf.startertest.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsb.ndisconf.startertest.config.TestAutoConfigXML;
import com.nsb.ndisconf.startertest.config.file.JedisConfig;
import com.nsb.ndisconf.startertest.config.item.BaoBaoService;
import com.nsb.ndisconf.startertest.config.item.Coefficients;

@RestController
public class TestController implements ApplicationContextAware, EnvironmentAware {

	@Autowired
	private JedisConfig jedisConfig;
	
	@Autowired
	private Coefficients coefficients;
	
	@Autowired
	private BaoBaoService baoBaoService;
	
	@Value("${myserver.host}")
	private String host;
	
	@Value("${myserver.port}")
	private String port;
	
	@Autowired
	private TestAutoConfigXML testAutoConfigXML;
	
	private ApplicationContext context;
	
	private ConfigurableEnvironment environment;
	
	@RequestMapping("/jedisConfig")
	public String jedisConfigController() {
		return jedisConfig.toString() + "<br>host: " + getProByName("redis.host") + ";port: " + getProByName("redis.port");
	}
	
	@RequestMapping("/discount")
	public String coefficients() {
		return String.valueOf(coefficients.getDiscount()) + "<br>" + getProByName("discountRate");
	}
	
	@RequestMapping("/baoBaoService")
	public String baoBaoService() {
		return String.valueOf(baoBaoService.calcMoney());
	}
	
	@RequestMapping("/myserver")
	public String myServer() {
		//return "host: " + host + "\nport: " + port;
		return "host: " + testAutoConfigXML.getHost() + ";port: " + testAutoConfigXML.getPort() + "<br>host: " + getProByName("myserver.host") + ";port: " + getProByName("myserver.port");
	}

	@RequestMapping("/host")
	public String host() {
		return "host: " + host + "; port: " + port;
	}
	
	@RequestMapping("/test")
	public String test() {
		return getProByName("test");
	}
	
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}
	
	private String getProByName(String key) {
		String result = System.getProperty(key);//context.getEnvironment().getProperty(key);
		return result;
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = (ConfigurableEnvironment) environment;
	}
}
