package com.nsb.ndisconf.server.disconf.web.web.zookeeper.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsb.ndisconf.server.disconf.web.service.app.bo.App;
import com.nsb.ndisconf.server.disconf.web.service.app.service.AppMgr;
import com.nsb.ndisconf.server.disconf.web.service.env.bo.Env;
import com.nsb.ndisconf.server.disconf.web.service.env.service.EnvMgr;
import com.nsb.ndisconf.server.disconf.web.service.zookeeper.form.ZkDeployForm;
import com.nsb.ndisconf.server.disconf.web.web.config.dto.ConfigFullModel;
import com.nsb.ndisconf.server.dsp.common.exception.FieldException;

/**
 * @author liaoqiqi
 * @version 2014-9-11
 */
@Service
public class ZkDeployValidator {

    @Autowired
    private AppMgr appMgr;

    @Autowired
    private EnvMgr envMgr;

    /**
     * @param zkDeployForm
     *
     * @return
     */
    public ConfigFullModel verify(ZkDeployForm zkDeployForm) {

        //
        // app
        //
        if (zkDeployForm.getAppId() == null) {
            throw new FieldException("app is empty", null);
        }

        App app = appMgr.getById(zkDeployForm.getAppId());
        if (app == null) {
            throw new FieldException("app " + zkDeployForm.getAppId() + " doesn't exist in db.", null);
        }

        //
        // env
        //
        if (zkDeployForm.getEnvId() == null) {
            throw new FieldException("app is empty", null);
        }

        Env env = envMgr.getById(zkDeployForm.getEnvId());
        if (env == null) {
            throw new FieldException("env " + zkDeployForm.getEnvId() + " doesn't exist in db.", null);
        }

        //
        // version
        //
        if (StringUtils.isEmpty(zkDeployForm.getVersion())) {
            throw new FieldException("version is empty", null);
        }

        return new ConfigFullModel(app, env, zkDeployForm.getVersion(), "");
    }
}
