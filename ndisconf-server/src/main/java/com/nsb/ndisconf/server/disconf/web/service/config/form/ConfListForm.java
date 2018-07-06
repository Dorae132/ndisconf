package com.nsb.ndisconf.server.disconf.web.service.config.form;

import javax.validation.constraints.NotNull;

import com.nsb.ndisconf.server.dsp.common.form.RequestListBase;

import lombok.Data;

/**
 * @author liaoqiqi
 * @version 2014-6-23
 */
@Data
public class ConfListForm extends RequestListBase {

	/**
	 *
	 */
	private static final long serialVersionUID = -2498128894396346299L;

	@NotNull
	private Long appId;

	@NotNull
	private String version;

	@NotNull
	private Long envId;

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Long getEnvId() {
		return envId;
	}

	public void setEnvId(Long envId) {
		this.envId = envId;
	}

}
