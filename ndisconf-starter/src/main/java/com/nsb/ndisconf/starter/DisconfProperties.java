package com.nsb.ndisconf.starter;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import com.baidu.disconf.client.support.utils.ClassUtils;

/**
 * disconf properties
 * @date 2018年5月16日10:43:24
 * @author niushuangbing
 *
 */
//@ConfigurationProperties(prefix = "ndisconf")
public class DisconfProperties {
	
	private static final Logger logger = LoggerFactory.getLogger(DisconfProperties.class);
	
	public static final String DISCONF_PROPERTIES_PREFIX = "ndisconf";

	private static DisconfProperties INSTANCE = null;
	
	public static DisconfProperties getInstance(Environment environment) {
		if (INSTANCE == null) {
			synchronized (DisconfProperties.class) {
				if (INSTANCE == null) {
					INSTANCE = new DisconfProperties(environment);
				}
			}
		}
		return INSTANCE;
	}
	
	/**
	 * load the config files, and set the properties that will be used by disconf when init user's config into SystemEnv.
	 * @param environment
	 */
	private void loadConfig(Environment environment) {
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(DisconfAutoAnnotation.class)) {
				DisconfAutoAnnotation config = field
						.getAnnotation(DisconfAutoAnnotation.class);
				// get the value of the config
				String value = environment.getProperty(DisconfProperties.DISCONF_PROPERTIES_PREFIX + "." + config.nDisconfName(), config.defaultValue());
				// set the value intto systemEnv
				System.setProperty(config.disconfName(), value);
				try {
					field.setAccessible(true);
					ClassUtils.setFieldValeByType(field, this, value);
				} catch (Exception e) {
					logger.error(String.format("invalid config: %s", config.nDisconfName()), e);
				}
			}
		}
	}

	private DisconfProperties(Environment environment) {
		super();
		loadConfig(environment);
	}
	
	// 要扫描的包
	@DisconfAutoAnnotation(nDisconfName = "scan_package", disconfName = "disconf.scanpackage", defaultValue = "")
	private String scanPackage;
	
	// 不会自动reload的文件
	@DisconfAutoAnnotation(nDisconfName = "un_reload_files", disconfName = "disconf.un_reload_files", defaultValue = "")
	private String unReloadFiles;
	
	// 自动reload的文件
	@DisconfAutoAnnotation(nDisconfName = "reload_files", disconfName = "disconf.reload_files", defaultValue = "")
	private String reloadFiles;
	
	// the following age the native arrtibutes
	// 是否打开远程配置
	@DisconfAutoAnnotation(nDisconfName = "enable_remote_conf", disconfName = "disconf.enable.remote.conf", defaultValue = "true")
	private String enableRemoteConf;
	
	// 配置中心地址
	@DisconfAutoAnnotation(nDisconfName = "conf_server_host", disconfName = "disconf.conf_server_host", defaultValue = "127.0.1:8080")
	private String confServerHost;
	
	// app版本号
	@DisconfAutoAnnotation(nDisconfName = "version", disconfName = "disconf.version", defaultValue = "1_0_0_0")
	private String version;
	
	// app name
	@DisconfAutoAnnotation(nDisconfName = "app", disconfName = "disconf.app", defaultValue = "app")
	private String app;
	
	// 环境
	@DisconfAutoAnnotation(nDisconfName = "env", disconfName = "disconf.env", defaultValue = "test")
	private String env;
	
	// 要忽略的文件
	@DisconfAutoAnnotation(nDisconfName = "ignore", disconfName = "disconf.ignore", defaultValue = "")
	private String ignore;

	// 调试模式
	@DisconfAutoAnnotation(nDisconfName = "debug", disconfName = "disconf.debug", defaultValue = "false")
	private boolean debug;
	
	// 重试次数
	@DisconfAutoAnnotation(nDisconfName = "retry_times", disconfName = "disconf.conf_server_url_retry_times", defaultValue = "3")
	private int retryTimes;
	
	// 重试间隔
	@DisconfAutoAnnotation(nDisconfName = "retry_interval", disconfName = "disconf.conf_server_url_retry_sleep_seconds", defaultValue = "5")
	private int retryInterval;
	
	// 下载文件保存路径
	@DisconfAutoAnnotation(nDisconfName = "download_dir", disconfName = "disconf.user_define_download_dir", defaultValue = "./ndisconfig")
	private String downloadDir;
	
	// 是否将文件拷贝到classpath
	@DisconfAutoAnnotation(nDisconfName = "download_in_classpath", disconfName = "disconf.enable_local_download_dir_in_class_path", defaultValue = "true")
	private boolean downloadInClassPath;

	
	public String getReloadFiles() {
		return reloadFiles;
	}

	public void setReloadFiles(String reloadFiles) {
		this.reloadFiles = reloadFiles;
	}

	public String getUnReloadFiles() {
		return unReloadFiles;
	}

	public void setUnReloadFiles(String unReloadFiles) {
		this.unReloadFiles = unReloadFiles;
	}

	public String getScanPackage() {
		return scanPackage;
	}

	public void setScanPackage(String scanPackage) {
		this.scanPackage = scanPackage;
	}

	public String getEnableRemoteConf() {
		return enableRemoteConf;
	}

	public void setEnableRemoteConf(String enableRemoteConf) {
		this.enableRemoteConf = enableRemoteConf;
	}

	public String getConfServerHost() {
		return confServerHost;
	}

	public void setConfServerHost(String confServerHost) {
		this.confServerHost = confServerHost;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getIgnore() {
		return ignore;
	}

	public void setIgnore(String ignore) {
		this.ignore = ignore;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public int getRetryTimes() {
		return retryTimes;
	}

	public void setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
	}

	public int getRetryInterval() {
		return retryInterval;
	}

	public void setRetryInterval(int retryInterval) {
		this.retryInterval = retryInterval;
	}

	public String getDownloadDir() {
		return downloadDir;
	}

	public void setDownloadDir(String downloadDir) {
		this.downloadDir = downloadDir;
	}

	public boolean isDownloadInClassPath() {
		return downloadInClassPath;
	}

	public void setDownloadInClassPath(boolean downloadInClassPath) {
		this.downloadInClassPath = downloadInClassPath;
	}
}
