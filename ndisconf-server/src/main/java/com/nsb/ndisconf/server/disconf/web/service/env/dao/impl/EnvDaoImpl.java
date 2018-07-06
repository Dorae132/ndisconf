package com.nsb.ndisconf.server.disconf.web.service.env.dao.impl;

import org.springframework.stereotype.Service;

import com.nsb.ndisconf.server.disconf.web.service.env.bo.Env;
import com.nsb.ndisconf.server.disconf.web.service.env.dao.EnvDao;
import com.nsb.ndisconf.server.dsp.common.dao.AbstractDao;
import com.nsb.ndisconf.server.dsp.common.dao.Columns;
import com.nsb.ndisconf.server.unbiz.common.genericdao.operator.Match;

/**
 * @author liaoqiqi
 * @version 2014-6-16
 */
@Service
public class EnvDaoImpl extends AbstractDao<Long, Env> implements EnvDao {

    @Override
    public Env getByName(String name) {

        return findOne(new Match(Columns.NAME, name));
    }

	@Override
	public int getCount() {
		return count();
	}

}
