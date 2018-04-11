package org.bravo.gaia.test.controller;

import org.bravo.gaia.commons.commoninterface.IErrorCodeEnum;
import org.bravo.gaia.commons.data.ErrorCode;
import org.bravo.gaia.commons.exception.PlatformException;

/**
 *
 * @author fengqian.lj
 * @version $Id: MyException.java, v 0.1 2018年04月10日 19:20 fengqian.lj Exp $
 */
public class MyException extends PlatformException {

    private IErrorCodeEnum errorCodeEnum;

    public MyException(Throwable throwable) {
        super(throwable);
    }

    public MyException(ErrorCode errorCode) {
        super(errorCode);
    }

    public MyException(String msg, ErrorCode errorCode) {
        super(msg, errorCode);
    }

    public MyException(String msg) {
        super(msg);
    }

    public MyException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    @Override
    public void setErrorCodeEnum(IErrorCodeEnum errorCodeEnum) {
        this.errorCodeEnum = errorCodeEnum;
    }
}