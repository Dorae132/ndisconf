package com.nsb.ndisconf.startertest.config.file;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.baidu.disconf.client.common.annotations.DisconfActiveBackupService;
import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;

/**
 * 配置文件测试
 * @date 2018年4月21日14:24:06
 * @author niushuangbing
 *
 */
@Component
@Scope("singleton")
@DisconfFile(filename = "redis.properties")
@DisconfActiveBackupService(classes={JedisConfig.class})
public class JedisConfig {

	private String host;
	
	private int port;

	@DisconfFileItem(name = "redis.host", associateField = "host")
	public String getHost() {
		return host;
	}

	@DisconfFileItem(name = "redis.port", associateField = "port")
	public int getPort() {
		return port;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	@Override
	public String toString() {
		return new StringBuilder("host: ").append(this.host).append("; port: ").append(this.port).toString();
	}
}
