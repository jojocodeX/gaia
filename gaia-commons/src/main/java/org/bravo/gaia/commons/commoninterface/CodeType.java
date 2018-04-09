/**
 * bravo.org
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package org.bravo.gaia.commons.commoninterface;

/**
 * 综合码类型
 * 
 * @author lijian
 * @version $Id: CodeType.java, v 0.1 2018年1月6日 下午3:01:55 lijian Exp $
 */
public interface CodeType {

    /** 码类型-成功 */
    String SUCCESS = "0";
    
    /** 码类型-业务错误 */
    String BIZ_ERROR = "1";
    
    /** 码类型-系统错误 */
    String SYS_ERROR = "2";
    
    /** 码类型-第三方错误 */
    String THIRD_ERROR = "3";
    
}
