package org.bravo.gaia.app.boot.interceptor;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bravo.gaia.commons.domain.ResultMessage;
import org.bravo.gaia.log.GaiaLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lijian
 * 定义MVC的异常处理，当MVC出现异常时候，此类中的方法进行捕获并进行处理
 */
public class ExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        //构造错误消息
        ResultMessage errorMessage = ResultMessage.buildErrorMessage(ex);

        //向客户端发送错误消息
        ResultMessage.sendError(response, errorMessage);

        //打印日志
        GaiaLogUtil.getGlobalErrorLogger().error(ExceptionUtils.getStackTrace(ex));

        return modelAndView;
    }

}
