package com.nsb.ndisconf.server.disconf.web.service.area.service;

import java.util.List;

import com.nsb.ndisconf.server.disconf.web.service.area.bo.Area;

public interface AreaMgr {
	void addArea(Area area);
	
	void delArea(Long id);
	
	List<Area> list();
	
	Area getArea(Long id);
	
}
