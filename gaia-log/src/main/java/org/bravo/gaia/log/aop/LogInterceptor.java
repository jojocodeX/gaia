/**
 * bravo.org
 * Copyright (c) 2018-2019 All Rights Reserved
 */
package org.bravo.gaia.log.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.bravo.gaia.log.annotation.NeedLog;
import org.springframework.context.annotation.Configuration;

/**
 * 通用日志拦截器
 *
 * @author alex.lj
 * @version @Id: LogInterceptor.java, v 0.1 2018年09月08日 22:05 alex.lj Exp $
 */
@Configuration
@Aspect
public class LogInterceptor {

    @Pointcut("@annotation(org.bravo.gaia.log.annotation.NeedLog)")
    public void logPointcut(){}

    @Around("logPointcut()")
    public Object methodAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = null;
        if (signature instanceof MethodSignature) {
            methodSignature = (MethodSignature) signature;
        }
        NeedLog needLog = methodSignature.getMethod().getDeclaredAnnotation(NeedLog.class);
        String sceneName = needLog.sceneName();
        System.out.println("before invoke " + sceneName);
        Object result = joinPoint.proceed();
        System.out.println("after invoke" + sceneName);

        return result;
    }

}
