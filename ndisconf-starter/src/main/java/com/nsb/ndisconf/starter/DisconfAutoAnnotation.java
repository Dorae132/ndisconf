package com.nsb.ndisconf.starter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * the annotation of autoConfiguration
 * @date 2018年5月16日10:41:34
 * @author niushuangbing
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DisconfAutoAnnotation {

	/**
	 * config name of ndisconf
	 * @return
	 */
	public String nDisconfName();
	
	/**
	 * config name of disconf
	 * @return
	 */
	public String disconfName();
	
	/**
	 * default value
	 * @return
	 */
	public String defaultValue();
}
