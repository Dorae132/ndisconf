package com.nsb.ndisconf.server.disconf.web.web.app.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nsb.ndisconf.server.disconf.web.service.app.bo.App;
import com.nsb.ndisconf.server.disconf.web.service.app.form.AppNewForm;
import com.nsb.ndisconf.server.disconf.web.service.app.service.AppMgr;
import com.nsb.ndisconf.server.dsp.common.exception.FieldException;

/**
 * 权限验证
 *
 * @author liaoqiqi
 * @version 2014-7-2
 */
@Component
public class AppValidator {

    @Autowired
    private AppMgr appMgr;

    /**
     * 验证创建
     */
    public void validateCreate(AppNewForm appNewForm) {

        // trim
        if (appNewForm.getApp() != null) {
            appNewForm.setApp(appNewForm.getApp().trim());
        }
        if (appNewForm.getDesc() != null) {
            appNewForm.setDesc(appNewForm.getDesc().trim());
        }

        App app = appMgr.getByName(appNewForm.getApp());
        if (app != null) {
            throw new FieldException(AppNewForm.APP, "app.exist", null);
        }
    }

}
