package com.nsb.ndisconf.server.disconf.web.service.data.dao;

import java.util.List;

import com.nsb.ndisconf.server.disconf.web.service.data.bo.DataSql;
import com.nsb.ndisconf.server.disconf.web.service.data.bo.DataSync;

public interface DataDao {
	int exec(String sql);

	List<String> getTabs();

	List<DataSql> getDatas(String tab);
	
	DataSync getDataSync(String tab);
}
