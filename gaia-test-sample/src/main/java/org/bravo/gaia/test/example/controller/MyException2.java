/**
 * bravo.org
 * Copyright (c) 2018-2019 All Rights Reserved
 */
package org.bravo.gaia.test.example.controller;

import org.bravo.gaia.commons.domain.ErrorCode;
import org.bravo.gaia.commons.exception.PlatformException;
import org.bravo.gaia.utils.Profiler;

import java.util.concurrent.TimeUnit;

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

    public static void main(String[] args) throws InterruptedException {
        Profiler.start("11323");
        Profiler.start("2222");

        TimeUnit.SECONDS.sleep(2);

        Profiler.release();
        Profiler.release();

        String dump = Profiler.dump();

        System.out.println(dump);
    }
}
