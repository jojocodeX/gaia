/**
 * bravo.org
 * Copyright (c) 2018-2019 All Rights Reserved
 */
package org.bravo.gaia.test.example.controller;

import org.bravo.gaia.commons.domain.ErrorCode;
import org.bravo.gaia.commons.exception.PlatformException;

/**
 * @author alex.lj
 * @version @Id: MyException.java, v 0.1 2018年09月07日 23:25 alex.lj Exp $
 */
public class MyException2 extends PlatformException {

    public MyException2(Throwable throwable) {
        super(throwable);
    }

    public MyException2(PlatformException e) {
        super(e);
    }

    public MyException2(ErrorCode errorCode) {
        super(errorCode);
    }

    public MyException2(String msg) {
        super(msg);
    }

    public MyException2(String msg, ErrorCode errorCode) {
        super(msg, errorCode);
    }

    public MyException2(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public MyException2(String msg, PlatformException e) {
        super(msg, e);
    }

    public static void main(String[] args) {
        throw new MyException2(new MyException2(MyErrorEnum.PARAM_ILLEGAL.getCode()));
    }
}
