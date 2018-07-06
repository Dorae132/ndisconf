package com.nsb.ndisconf.nsb_test.config;

public class TestAutoConfigXML {

	private String host;
	
	private String port;
	
	public TestAutoConfigXML() {
		super();
		System.out.println("TestAutoConfigXML-------------------------------------------");
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
}
