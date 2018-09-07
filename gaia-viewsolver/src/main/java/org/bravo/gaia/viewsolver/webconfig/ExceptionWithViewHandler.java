package org.bravo.gaia.viewsolver.webconfig;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bravo.gaia.commons.consist.WebConsist;
import org.bravo.gaia.commons.domain.ResultMessage;
import org.bravo.gaia.log.GaiaLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lijian
 * 定义MVC的异常处理，当MVC出现异常时候，此类中的方法进行捕获并进行处理
 */
public class ExceptionWithViewHandler implements HandlerExceptionResolver {

    @Value("${web.errorPage._500:/500}")
    private String errorPage_500;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();

        String ajaxType = request.getHeader(WebConsist.AJAX_REQUEST_IDENTIFIER);

        //构造错误消息
        ResultMessage errorMessage = ResultMessage.buildErrorMessage(ex);

        if (StringUtils.isEmpty(ajaxType)) { //非ajax请求

            //设置request作用域变量，返回500页面
            modelAndView.addObject(ResultMessage.DATA, errorMessage.getErrorMsg());
            modelAndView.addObject(ResultMessage.ERROR_MSG, errorMessage.getErrorMsg());
            modelAndView.addObject(ResultMessage.ERROR_CODE, errorMessage.getErrorCode());
            modelAndView.setViewName(errorPage_500);
        } else {

            //向客户端发送错误消息
            ResultMessage.sendError(response, errorMessage);
        }

        //打印日志
        GaiaLogUtil.getGlobalErrorLogger().error(ExceptionUtils.getStackTrace(ex));
        return modelAndView;
    }

}
