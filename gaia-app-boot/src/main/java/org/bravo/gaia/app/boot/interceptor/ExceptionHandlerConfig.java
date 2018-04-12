package org.bravo.gaia.app.boot.interceptor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 *
 * @author lijian
 * @version $Id: ExceptionHandlerConfig.java, v 0.1 2018年04月11日 9:56 lijian Exp $
 */
@Configuration
public class ExceptionHandlerConfig {

    @Bean
    @ConditionalOnBean(HandlerExceptionResolver.class)
    public HandlerExceptionResolver exceptionHandler() {
        return new ExceptionHandler();
    }

}