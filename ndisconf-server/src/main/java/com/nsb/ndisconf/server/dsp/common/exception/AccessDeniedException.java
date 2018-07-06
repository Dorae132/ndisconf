package com.nsb.ndisconf.server.dsp.common.exception;

import com.nsb.ndisconf.server.dsp.common.constant.ErrorCode;
import com.nsb.ndisconf.server.dsp.common.constant.ModuleCode;
import com.nsb.ndisconf.server.dsp.common.exception.base.RuntimeGlobalException;

/**
 * @author weiwei
 * @Description: the method is not accessible to current user
 */
public class AccessDeniedException extends RuntimeGlobalException {

    private static final long serialVersionUID = 1L;

    public AccessDeniedException(String exceptionMessage) {
        super(ErrorCode.GLOBAL_ERROR, exceptionMessage);
    }

    @Override
    public ModuleCode getModuleCode() {
        return ModuleCode.OTHER;
    }

}
