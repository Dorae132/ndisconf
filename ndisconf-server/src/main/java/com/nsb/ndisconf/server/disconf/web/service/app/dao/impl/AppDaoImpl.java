package com.nsb.ndisconf.server.disconf.web.service.app.dao.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.nsb.ndisconf.server.disconf.web.service.app.bo.App;
import com.nsb.ndisconf.server.disconf.web.service.app.dao.AppDao;
import com.nsb.ndisconf.server.dsp.common.dao.AbstractDao;
import com.nsb.ndisconf.server.dsp.common.dao.Columns;
import com.nsb.ndisconf.server.unbiz.common.genericdao.operator.Match;

/**
 * @author liaoqiqi
 * @version 2014-6-16
 */
@Service
public class AppDaoImpl extends AbstractDao<Long, App> implements AppDao {

    @Override
    public App getByName(String name) {

        return findOne(new Match(Columns.NAME, name));
    }

    @Override
    public List<App> getByIds(Set<Long> ids) {

        if (CollectionUtils.isEmpty(ids)) {
            return findAll();
        }

        return find(match(Columns.APP_ID, ids));
    }

	@Override
	public int getCount() {
		return count();
	}

}
