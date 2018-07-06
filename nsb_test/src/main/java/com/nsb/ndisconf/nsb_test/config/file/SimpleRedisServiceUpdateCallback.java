package com.nsb.ndisconf.nsb_test.config.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;

/**
 * 更新回调
 * @author niushuangbing
 *
 */
@Component
@Scope("singleton")
@DisconfUpdateService(classes = {JedisConfig.class})
public class SimpleRedisServiceUpdateCallback implements IDisconfUpdate {

	@Autowired
	private SimpleRedisService simpleRedisService;
	
	@Override
	public void reload() throws Exception {
		simpleRedisService.changeJedis();
	}

}
