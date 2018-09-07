package org.bravo.gaia.commons.domain;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bravo.gaia.commons.consist.WebConsist;
import org.bravo.gaia.commons.exception.PlatformException;
import org.bravo.gaia.log.GaiaLogUtil;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回给客户端的信息
 * @author lijian
 * @version $Id: ResultMessage.java, v 0.1 2018年04月10日 17:30 lijian Exp $
 */
public class ResultMessage {

    public final static String  ERROR_CODE   = "errorCode";
    public final static String  ERROR_MSG    = "errorMsg";
    public final static String  DATA         = "errorCode";

    @Setter
    @Getter
    private String              errorCode;
    @Setter
    @Getter
    private String              errorMsg;
    @Setter
    @Getter
    private String              data;

    private final static String UNKOWN_MSG   = "系统未知错误";
    private final static String UNKOWN_CODE  = "UNKOWN";

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 向客户端写回标准错误信息(包含标准错误码)
     */
    public static void sendError(HttpServletResponse response, ResultMessage resultMessage) {
        response.setHeader(WebConsist.CONTENT_TYPE, WebConsist.TEXT_JSON);
        try {
            String errorMessageJson = objectMapper.writeValueAsString(resultMessage);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessageJson.getBytes("UTF-8"));
            outputStream.flush();
        } catch (IOException e) {
            GaiaLogUtil.getGlobalErrorLogger().error("servlet response写入客户端错误:" + e.getMessage());
            GaiaLogUtil.getGlobalErrorLogger().error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
    }

    /**
     * 构造错误消息
     * @param ex 异常对象
     */
    public static ResultMessage buildErrorMessage(Exception ex) {
        String errorCode = StringUtils.EMPTY;
        String errorMsg = StringUtils.EMPTY;
        if (ex instanceof PlatformException) {
            if (((PlatformException) ex).getCurrentErrorCode() != null) {
                errorCode = ((PlatformException) ex).getCurrentErrorCode().toString();
                errorMsg = ((PlatformException) ex).getCurrentErrorCode().getErrorDesc();
            }
        }

        if (StringUtils.isBlank(errorCode)) {
            errorCode = UNKOWN_CODE;
        }

        if (StringUtils.isBlank(errorMsg)) {
            errorMsg = UNKOWN_MSG;
        }

        ResultMessage errorMessage = new ResultMessage();
        errorMessage.setErrorMsg(errorMsg);
        errorMessage.setErrorCode(errorCode);

        return errorMessage;
    }

}