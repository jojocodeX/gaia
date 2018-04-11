package org.bravo.gaia.commons.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.bravo.gaia.commons.consist.WebConsist;
import org.bravo.gaia.commons.exception.PlatformException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 返回给客户端的错误消息
 * @author fengqian.lj
 * @version $Id: ErrorMessage.java, v 0.1 2018年04月10日 17:30 fengqian.lj Exp $
 */
@Setter
@Getter
public class ErrorMessage {

    public final static String ERROR_CODE = "errorCode";
    public final static String ERROR_MSG = "errorMsg";

    private String errorCode;

    private String errorMsg;

    private final static String UNKOWN_MSG = "系统未知错误";
    private final static String UNKOWN_CODE = "UNKOWN";

    private static ObjectMapper objectMapper = new ObjectMapper();

    private final static Logger LOG = LoggerFactory.getLogger(ErrorMessage.class);

    /**
     * 向客户端写回标准错误信息(包含标准错误码)
     */
    public static void sendError(HttpServletResponse response, ErrorMessage errorMessage) {
        response.setHeader(WebConsist.CONTENT_TYPE, WebConsist.TEXT_JSON);
        try {
            String errorMessageJson = objectMapper.writeValueAsString(errorMessage);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessageJson.getBytes("UTF-8"));
            outputStream.flush();
        } catch (IOException e) {
            LOG.error("servlet response写入客户端错误:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 构造错误消息
     * @param ex 异常对象
     */
    public static ErrorMessage buildErrorMessage(Exception ex) {
        String errorCode;
        String errorMsg;
        if (ex instanceof PlatformException) {
            errorCode = ((PlatformException) ex).getErrorCode().getEnumCode();
            errorMsg = ((PlatformException) ex).getErrorCode().getDescription();
        } else {
            errorCode = UNKOWN_CODE;
            errorMsg = UNKOWN_MSG;
        }

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorMsg(errorMsg);
        errorMessage.setErrorCode(errorCode);
        return errorMessage;
    }

}