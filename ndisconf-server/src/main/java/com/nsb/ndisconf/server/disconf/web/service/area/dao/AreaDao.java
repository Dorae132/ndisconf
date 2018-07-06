package com.nsb.ndisconf.server.disconf.web.service.area.dao;

import com.nsb.ndisconf.server.disconf.web.service.area.bo.Area;
import com.nsb.ndisconf.server.unbiz.common.genericdao.dao.BaseDao;

public interface AreaDao  extends BaseDao<Long, Area> {
	 /**
     * @param 
     *
     * @return int
     */
    int getCount();
}
