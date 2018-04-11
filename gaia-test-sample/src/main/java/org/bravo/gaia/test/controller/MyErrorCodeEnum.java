package org.bravo.gaia.test.controller;

import org.bravo.gaia.commons.commoninterface.CodeLevel;
import org.bravo.gaia.commons.commoninterface.CodeType;
import org.bravo.gaia.commons.commoninterface.IErrorCodeEnum;
import org.bravo.gaia.commons.data.ErrorCode;

/**
 *
 * @author fengqian.lj
 * @version $Id: MyErrorCodeEnum.java, v 0.1 2018年04月10日 19:20 fengqian.lj Exp $
 */
public enum MyErrorCodeEnum implements IErrorCodeEnum {
    NOT_FOUND(CodeLevel.ERROR, CodeType.BIZ_ERROR, "001", "当前系统", "300", "NOT_FOUND", "没有找到数据"),
    NOT_EMPTY(CodeLevel.ERROR, CodeType.BIZ_ERROR, "001", "当前系统", "301", "NOT_EMPTY", "空的空的空的");


    private String codeLevel;
    private String codeType;
    private String systemCode;
    private String systemName;
    private String enumCode;
    private String codeAlias;
    private String description;

    MyErrorCodeEnum(String codeLevel, String codeType, String systemCode, String systemName,
                    String enumCode, String codeAlias, String description) {
        this.codeLevel = codeLevel;
        this.codeType = codeType;
        this.systemCode = systemCode;
        this.systemName = systemName;
        this.enumCode = enumCode;
        this.codeAlias = codeAlias;
        this.description = description;
    }

    @Override
    public ErrorCode getCode() {
        ErrorCode errorCode = new ErrorCode(
            codeLevel, codeType, systemCode, systemName,
            enumCode, codeAlias, description
        );
        return errorCode;
    }
}