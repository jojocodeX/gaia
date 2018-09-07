/**
 * bravo.org
 * Copyright (c) 2018-2019 All Rights Reserved
 */
package org.bravo.gaia.test.example.controller;

import org.bravo.gaia.commons.commoninterface.ErrorCodeLevel;
import org.bravo.gaia.commons.commoninterface.ErrorCodeType;
import org.bravo.gaia.commons.commoninterface.IErrorCode;
import org.bravo.gaia.commons.domain.ErrorCode;
import org.bravo.gaia.commons.system.SystemNameEnum;

/**
 * @author alex.lj
 * @version @Id: MyErrorEnum.java, v 0.1 2018年09月08日 00:07 alex.lj Exp $
 */
public enum MyErrorEnum implements IErrorCode {

    PARAM_ILLEGAL("PARAM_ILLEGAL", ErrorCodeLevel.ERROR, ErrorCodeType.BIZ_ERROR, SystemNameEnum.USER_CENTER.getCode(), "0001", "参数错误");

    private String enumCode;
    private String errorCodeLevel;
    private String errorCodeType;
    private String systemCode;
    private String errorCodeNum;
    private String errorDesc;

    MyErrorEnum(String enumCode, String errorCodeLevel, String errorCodeType, String systemCode, String errorCodeNum, String errorDesc) {
        this.enumCode = enumCode;
        this.errorCodeLevel = errorCodeLevel;
        this.errorCodeType = errorCodeType;
        this.systemCode = systemCode;
        this.errorCodeNum = errorCodeNum;
        this.errorDesc = errorDesc;
    }

    @Override
    public ErrorCode getCode() {
        ErrorCode errorCode = new ErrorCode(this.errorCodeLevel, this.errorCodeType, this.systemCode, this.errorCodeNum, this.errorDesc);
        return errorCode;
    }

}
