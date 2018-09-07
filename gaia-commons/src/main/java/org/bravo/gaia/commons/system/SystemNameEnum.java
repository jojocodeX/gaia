/**
 * bravo.org
 * Copyright (c) 2018-2019 All Rights Reserved
 */
package org.bravo.gaia.commons.system;

import lombok.Getter;

/**
 * 系统名称枚举
 *
 * @author alex.lj
 * @version @Id: SystemNameEnum.java, v 0.1 2018年09月07日 22:21 alex.lj Exp $
 */
@Getter
public enum SystemNameEnum {

    /** 用户中心 */
    USER_CENTER("001", "user center", "用户中心", "提供用户基础服务"),

    /** 权限中心 */
    PERMISSION_CENTER("002", "permission center", "权限中心", "提供权限基础服务")
    ;

    /** 系统编码--三位数 */
    private String code;
    /** 系统英文名 */
    private String englishName;
    /** 系统中文名 */
    private String chineseName;
    /** 描述 */
    private String desc;

    SystemNameEnum(String code, String englishName, String chineseName, String desc) {
        this.code = code;
        this.englishName = englishName;
        this.chineseName = chineseName;
        this.desc = desc;
    }

}
