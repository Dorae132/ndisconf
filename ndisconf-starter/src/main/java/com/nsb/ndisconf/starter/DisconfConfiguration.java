package com.nsb.ndisconf.starter;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;

import com.baidu.disconf.client.DisconfMgrBean;
import com.baidu.disconf.client.DisconfMgrBeanSecond;
import com.baidu.disconf.client.addons.properties.ReloadablePropertiesFactoryBean;
import com.baidu.disconf.client.addons.properties.ReloadingPropertyPlaceholderConfigurer;
import com.baidu.disconf.client.config.DisClientSysConfig;
import com.google.common.collect.Lists;

/**
 * @date 2018年5月16日13:07:29
 * @author niushuangbing
 *
 */
@Configuration
//@EnableConfigurationProperties(DisconfProperties.class)
@ConditionalOnClass(DisconfMgrBean.class)
@ConditionalOnProperty(prefix="ndisconf", value = {"scan_package"}, matchIfMissing = false)
public class DisconfConfiguration implements EnvironmentAware {

	private static final Logger logger = LoggerFactory.getLogger(DisconfConfiguration.class);
	
	private static final String SEPARATOR = ",";
	
//	@Autowired
	private DisconfProperties disconfProperties;

	private ConfigurableEnvironment environment;
	
	@Override
	public void setEnvironment(Environment environment) {
		this.environment = (ConfigurableEnvironment) environment;
		disconfProperties = DisconfProperties.getInstance(environment);
		// the purpose is to replace the download path
		loadDisClientSysConfig();
	}
	
	/**
	 * load the sys files of disconf
	 */
	private void loadDisClientSysConfig() {
		DisClientSysConfig disClientSysConfig = DisClientSysConfig.getInstance();
		try {
			disClientSysConfig.loadConfig(null);
		} catch (Exception e) {
			logger.error("DisconfConfiguration#disClientSysConfig() load sys files error!");
		}
		disClientSysConfig.LOCAL_DOWNLOAD_DIR = disconfProperties.getDownloadDir();
	}
	
	/**
	 * first scan
	 * @return
	 */
	@Bean(name = "disconfMgrBean", destroyMethod = "destroy")
	public DisconfMgrBean disconfMgrBean() {
		DisconfMgrBean disconfMgrBean = new DisconfMgrBean();
		if (StringUtils.isBlank(disconfProperties.getScanPackage())) {
			logger.error("DisconfConfiguration#disconfMgrBean() the scanPackage could not be null!");
			throw new RuntimeException("the scanPackage could not be null!");
		}
		disconfMgrBean.setScanPackage(disconfProperties.getScanPackage());
		return disconfMgrBean;
	}
	
	/**
	 * second scan
	 * @return
	 */
	@Bean(name = "disconfMgrBean2", initMethod = "init", destroyMethod = "destroy")
	public DisconfMgrBeanSecond disconfMgrBeanSecond() {
		return new DisconfMgrBeanSecond();
	}
	
	/**
	 * let the files be managed by ndisconf
	 * @return
	 */
	@Bean(name = "unReloadablePropertiesFactoryBean")
	@ConditionalOnExpression("'${ndisconf.un_reload_files}' != '' and !'${ndisconf.un_reload_files}'.startsWith('${')")
	public ReloadablePropertiesFactoryBean unReloadablePropertiesFactoryBean() {
		ReloadablePropertiesFactoryBean bean = new ReloadablePropertiesFactoryBean();
		bean.setLocations(getFileNames(disconfProperties.getUnReloadFiles()));
		return bean;
	}
	
	/**
	 * will not reload the files to the system when the configuration files change
	 * @param reloadablePropertiesFactoryBean
	 * @return
	 * @throws IOException
	 */
	@Bean(name = "propertyPlaceholderConfigurer")
	@ConditionalOnBean(name = "unReloadablePropertiesFactoryBean")
	public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(@Qualifier("unReloadablePropertiesFactoryBean") ReloadablePropertiesFactoryBean reloadablePropertiesFactoryBean) throws IOException {
		PropertyPlaceholderConfigurer bean = new PropertyPlaceholderConfigurer();
		bean.setOrder(1);
		bean.setIgnoreResourceNotFound(true);
		bean.setIgnoreUnresolvablePlaceholders(true);
		bean.setProperties(reloadablePropertiesFactoryBean.getObject());
		addPropertiesPropertySource("disconfUnReloadableProperties", reloadablePropertiesFactoryBean.getObject());
		return bean;
	}
	
	/**
	 * let the files be managed by ndisconf
	 * @return
	 */
	@Bean(name = "reloadablePropertiesFactoryBean")
	@ConditionalOnExpression("'${ndisconf.reload_files}' != '' and !'${ndisconf.reload_files}'.startsWith('${')")
	public ReloadablePropertiesFactoryBean reloadablePropertiesFactoryBean() {
		ReloadablePropertiesFactoryBean reloadablePropertiesFactoryBean = new ReloadablePropertiesFactoryBean();
		reloadablePropertiesFactoryBean.setLocations(getFileNames(disconfProperties.getReloadFiles()));
		return reloadablePropertiesFactoryBean;
	}
	
	/**
	 * will reload the files to the system when the configuration files change
	 * @param reloadablePropertiesFactoryBean
	 * @return
	 * @throws IOException
	 */
	@Bean(name = "propertyConfigurer")
	@ConditionalOnBean(name = "reloadablePropertiesFactoryBean")
	public ReloadingPropertyPlaceholderConfigurer reloadingPropertyPlaceholderConfigurer(@Qualifier("reloadablePropertiesFactoryBean") ReloadablePropertiesFactoryBean reloadablePropertiesFactoryBean) throws IOException {
		ReloadingPropertyPlaceholderConfigurer bean = new ReloadingPropertyPlaceholderConfigurer();
		bean.setOrder(1);
		bean.setIgnoreResourceNotFound(true);
		bean.setIgnoreUnresolvablePlaceholders(true);
		bean.setProperties(reloadablePropertiesFactoryBean.getObject());
		return bean;
	}
	
	/**
	 * reslove the problem that spring could not find the propertites which be imported by xml or other ways which exclusive the application.xml file
	 * @param name
	 * @param source
	 */
    private void addPropertiesPropertySource(String name, Properties source) {
        PropertiesPropertySource propertiesPropertySource = new PropertiesPropertySource(name, source);
        environment.getPropertySources().addLast(propertiesPropertySource);
    }
    
	/**
     * get the file list
     * @param files
     * @return List<String>
     */
    private List<String> getFileNames(String files) {
    	if (StringUtils.isBlank(files)) {
    		throw new RuntimeException("the file name could not be blank");
    	}
        List<String> fileList = Lists.newArrayList(files.trim().split(SEPARATOR));
        // find the files in the current classpath
        if (disconfProperties.isDownloadInClassPath()) {
            return fileList;
        }
        // find the files in the system
        return fileList.stream().map(e -> "file:" + disconfProperties.getDownloadDir() + "/" + e).collect(Collectors.toList());
    }
}
