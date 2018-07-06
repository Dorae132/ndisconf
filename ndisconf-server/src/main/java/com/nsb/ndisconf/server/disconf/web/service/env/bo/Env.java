package com.nsb.ndisconf.server.disconf.web.service.env.bo;

import com.github.knightliao.apollo.db.bo.BaseObject;
import com.nsb.ndisconf.server.dsp.common.dao.Columns;
import com.nsb.ndisconf.server.dsp.common.dao.DB;
import com.nsb.ndisconf.server.unbiz.common.genericdao.annotation.Column;
import com.nsb.ndisconf.server.unbiz.common.genericdao.annotation.Table;

/**
 * @author liaoqiqi
 * @version 2014-6-16
 */
@Table(db = DB.DB_NAME, name = "env", keyColumn = Columns.ENV_ID)
public class Env extends BaseObject<Long> {

    /**
     *
     */
    private static final long serialVersionUID = -665241738023640732L;

    /**
     *
     */
    @Column(value = Columns.NAME)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Env [name=" + name + "]";
    }

}
