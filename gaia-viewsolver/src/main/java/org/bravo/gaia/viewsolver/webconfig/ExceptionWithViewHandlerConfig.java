package org.bravo.gaia.viewsolver.webconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 *
 * @author fengqian.lj
 * @version $Id: ExceptionHandlerConfig.java, v 0.1 2018年04月11日 10:30 fengqian.lj Exp $
 */
@Configuration
public class ExceptionWithViewHandlerConfig {

    @Bean
    public HandlerExceptionResolver exceptionHandler() {
        return new ExceptionWithViewHandler();
    }

}