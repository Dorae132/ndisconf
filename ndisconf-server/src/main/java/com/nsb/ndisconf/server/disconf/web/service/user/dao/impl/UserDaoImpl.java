package com.nsb.ndisconf.server.disconf.web.service.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.nsb.ndisconf.server.disconf.web.service.user.bo.User;
import com.nsb.ndisconf.server.disconf.web.service.user.dao.UserDao;
import com.nsb.ndisconf.server.dsp.common.dao.AbstractDao;
import com.nsb.ndisconf.server.dsp.common.dao.Columns;

/**
 * @author liaoqiqi
 * @version 2013-11-28
 */
@Repository
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

    /**
     * 执行SQL
     */
    public void executeSql(String sql) {

        executeSQL(sql, null);
    }

    /**
     */
    @Override
    public User getUserByName(String name) {

        return findOne(match(Columns.NAME, name));
    }

}