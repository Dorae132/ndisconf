package com.nsb.ndisconf.server.disconf.web.service.user.dao;

import com.nsb.ndisconf.server.disconf.web.service.user.bo.User;
import com.nsb.ndisconf.server.unbiz.common.genericdao.dao.BaseDao;

/**
 * @author liaoqiqi
 * @version 2013-11-28
 */
public interface UserDao extends BaseDao<Long, User> {

    void executeSql(String sql);

    User getUserByName(String name);

}
