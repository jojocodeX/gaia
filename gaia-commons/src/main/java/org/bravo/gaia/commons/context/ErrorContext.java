/**
 * bravo.org
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package org.bravo.gaia.commons.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bravo.gaia.commons.domain.ErrorCode;

import lombok.Getter;
import lombok.Setter;

/**
 * 错误上下文对象
 * 
 * <p>内容包含有：标准错误堆栈
 * 
 * @author lijian
 * @version $Id: ErrorContext.java, v 0.1 2018年1月6日 下午10:27:59 lijian Exp $
 */
public class ErrorContext implements Serializable {

    private static final long   serialVersionUID = -2191953263464121647L;

    @Setter
    @Getter
    private List<ErrorCode>     errorStack       = new ArrayList<>();

    private static final String SPLIT            = "|";

    /**
     * 默认构造方法
     */
    public ErrorContext() {

    }

    /**
     * 获取当前错误码
     */
    public ErrorCode fetchCurrentError() {

        if (errorStack != null && errorStack.size() > 0) {

            return errorStack.get(errorStack.size() - 1);
        }
        return null;
    }

    /**
     * 获取原始错误码
     */
    public ErrorCode fetchRootError() {

        if (errorStack != null && errorStack.size() > 0) {
            return errorStack.get(0);
        }
        return null;
    }

    /**
     * 向错误堆栈中添加错误对象
     * 
     * @param errorCode 错误码
     */
    public void addErrorCode(ErrorCode errorCode) {
        errorStack.add(errorCode);
    }

    /**
     * 向错误堆栈中添加另外一个堆栈
     *
     * @param errorContext 错误码
     */
    public void addErrorStack(ErrorContext errorContext) {
        errorStack.addAll(errorContext.getErrorStack());
    }

    // ~~~重写方法
    /** 
     * @see Object#toString()
     */
    public String toString() {

        StringBuffer sb = new StringBuffer();

        for (int i = errorStack.size(); i > 0; i--) {

            if (i == errorStack.size()) {

                sb.append(errorStack.get(i - 1));
            } else {

                sb.append(SPLIT).append(errorStack.get(i - 1));
            }
        }
        return sb.toString();
    }

}
