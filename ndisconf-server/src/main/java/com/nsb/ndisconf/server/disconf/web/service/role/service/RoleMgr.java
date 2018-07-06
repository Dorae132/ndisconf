package com.nsb.ndisconf.server.disconf.web.service.role.service;

import java.util.List;

import com.nsb.ndisconf.server.disconf.web.service.role.bo.Role;

/**
 * @author weiwei
 * @date 2013-12-24
 */
public interface RoleMgr {

    public Role get(Integer roleId);

    public List<Role> findAll();

}
