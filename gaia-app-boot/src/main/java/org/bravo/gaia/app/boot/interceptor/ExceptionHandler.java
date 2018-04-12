package org.bravo.gaia.app.boot.interceptor;

import org.bravo.gaia.commons.data.ErrorMessage;
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

    private final static Logger LOG = LoggerFactory.getLogger(ExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        //构造错误消息
        ErrorMessage errorMessage = ErrorMessage.buildErrorMessage(ex);

        //向客户端发送错误消息
        ErrorMessage.sendError(response, errorMessage);

		ex.printStackTrace();
		return modelAndView;
	}

}
