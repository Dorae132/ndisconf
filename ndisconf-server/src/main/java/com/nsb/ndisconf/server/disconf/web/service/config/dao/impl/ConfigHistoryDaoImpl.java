package com.nsb.ndisconf.server.disconf.web.service.config.dao.impl;

import org.springframework.stereotype.Service;

import com.nsb.ndisconf.server.disconf.web.service.config.bo.ConfigHistory;
import com.nsb.ndisconf.server.disconf.web.service.config.dao.ConfigHistoryDao;
import com.nsb.ndisconf.server.dsp.common.dao.AbstractDao;

/**
 * Created by knightliao on 15/12/25.
 */
@Service
public class ConfigHistoryDaoImpl extends AbstractDao<Long, ConfigHistory> implements ConfigHistoryDao {
}
