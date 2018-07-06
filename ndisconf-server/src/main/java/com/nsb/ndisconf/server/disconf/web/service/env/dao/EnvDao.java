package com.nsb.ndisconf.server.disconf.web.service.env.dao;

import com.nsb.ndisconf.server.disconf.web.service.env.bo.Env;
import com.nsb.ndisconf.server.unbiz.common.genericdao.dao.BaseDao;

/**
 * @author liaoqiqi
 * @version 2014-6-16
 */
public interface EnvDao extends BaseDao<Long, Env> {

    /**
     * @param name
     *
     * @return
     */
    Env getByName(String name);
    
    /**
     * @param 
     *
     * @return int
     */
    int getCount();
    
}
