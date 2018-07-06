package com.nsb.ndisconf.server.dsp.common.exception;

import com.nsb.ndisconf.server.dsp.common.constant.ErrorCode;
import com.nsb.ndisconf.server.dsp.common.constant.ModuleCode;
import com.nsb.ndisconf.server.dsp.common.exception.base.RuntimeGlobalException;

/**
 * @author liaoqiqi
 * @version 2014-6-24
 */
public class UnExpectedException extends RuntimeGlobalException {

    private static final long serialVersionUID = 1L;

    public UnExpectedException(String exceptionMessage) {
        super(ErrorCode.DAO_ERROR, exceptionMessage);
    }

    @Override
    public ModuleCode getModuleCode() {
        return ModuleCode.EXCEPTION;
    }

    public UnExpectedException(String exceptionMessage, Throwable throwable) {

        super(ErrorCode.UN_EXPECTED, exceptionMessage, throwable);
    }

}
