package com.nsb.ndisconf.server.disconf.web.service.area.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nsb.ndisconf.server.disconf.web.service.area.bo.Area;
import com.nsb.ndisconf.server.disconf.web.service.area.dao.AreaDao;
import com.nsb.ndisconf.server.disconf.web.service.area.service.AreaMgr;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class AreaMgrImpl implements AreaMgr {

	@Autowired
	private AreaDao areaDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addArea(Area area) {
		areaDao.create(area);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delArea(Long id) {
		areaDao.delete(id);
	}

	@Override
	public List<Area> list() {
		return areaDao.findAll();
	}

	@Override
	public Area getArea(Long id) {
		return areaDao.get(id);
	}

}
