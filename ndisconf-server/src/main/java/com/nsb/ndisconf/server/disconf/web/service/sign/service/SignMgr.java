package com.nsb.ndisconf.server.disconf.web.service.sign.service;

import com.nsb.ndisconf.server.disconf.web.service.user.bo.User;

/**
 * @author liaoqiqi
 * @version 2014-2-6
 */
public interface SignMgr {

    User getUserByName(String name);

    boolean validate(String userPassword, String passwordToBeValidate);

    User signin(String phone);
}
