/**
 * bravo.org
 * Copyright (c) 2018-2019 All Rights Reserved
 */
package org.bravo.gaia.log.annotation;

import java.lang.annotation.*;

import org.apache.commons.lang3.StringUtils;

/**
 * 通用日志注解
 *
 * @author alex.lj
 * @version @Id: NeedLog.java, v 0.1 2018年09月08日 22:13 alex.lj Exp $
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedLog {

    /** 场景名称 */
    String sceneName() default StringUtils.EMPTY;

    /** 是否将异常外抛 */
    boolean isThrowing() default false;
}
