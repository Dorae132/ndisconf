package com.nsb.ndisconf.server.disconf.web.service.config.dao;

import java.util.List;

import com.baidu.disconf.core.common.constants.DisConfigTypeEnum;
import com.nsb.ndisconf.server.disconf.web.service.config.bo.Config;
import com.nsb.ndisconf.server.dsp.common.form.RequestListBase.Page;
import com.nsb.ndisconf.server.ub.common.db.DaoPageResult;
import com.nsb.ndisconf.server.unbiz.common.genericdao.dao.BaseDao;

/**
 * @author liaoqiqi
 * @version 2014-6-16
 */
public interface ConfigDao extends BaseDao<Long, Config> {

    /**
     * @param appId
     * @param envId
     * @param version
     * @param key
     * @param disConfigTypeEnum
     *
     * @return
     */
    Config getByParameter(Long appId, Long envId, String version, String key, DisConfigTypeEnum disConfigTypeEnum);

    /**
     * @param
     *
     * @return
     */
    List<Config> getConfByAppEnv(Long appId, Long envId);

    /**
     * @param appId
     * @param envId
     * @param version
     *
     * @return
     */
    DaoPageResult<Config> getConfigList(Long appId, Long envId, String version, Page page);

    /**
     * @param configId
     *
     * @return
     */
    void updateValue(Long configId, String value);

    /**
     *
     */
    String getValue(Long configId);

    /**
     * @param appId
     * @param envId
     * @param version
     * @param hasValue
     * @return
     */
    List<Config> getConfigList(Long appId, Long envId, String version, Boolean hasValue);


    /**
     * @param configId
     */
    void deleteItem(Long configId);

	void deleteAppConfig(Long appid);
	
	/**
     * @param 
     *
     * @return int
     */
    int getCount();
    
    List<Config> getAllNormal();
}
