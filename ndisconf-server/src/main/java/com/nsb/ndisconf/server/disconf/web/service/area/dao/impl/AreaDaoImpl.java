package com.nsb.ndisconf.server.disconf.web.service.area.dao.impl;

import org.springframework.stereotype.Service;

import com.nsb.ndisconf.server.disconf.web.service.area.bo.Area;
import com.nsb.ndisconf.server.disconf.web.service.area.dao.AreaDao;
import com.nsb.ndisconf.server.dsp.common.dao.AbstractDao;

@Service
public class AreaDaoImpl extends AbstractDao<Long, Area> implements AreaDao {

	@Override
	public int getCount() {
		return count();
	}

}
