package org.bravo.gaia.commons.exception;

import lombok.Getter;
import lombok.Setter;
import org.bravo.gaia.commons.context.ErrorContext;
import org.bravo.gaia.commons.domain.ErrorCode;

/**
 * 平台公共异常类，所有application的异常类都需要继承此类。
 * 
 * <p><b>注意：</b>
 * <p>子类继承时，必须实现此类所有的构造方法。即：XXX(String msg)，XXX(Throwable throwable)，XXX(String msg, Throwable throwable)
 *
 * @author lijian
 * @version $Id: PlatformException.java, v 0.1 2018年1月6日 上午11:50:10 lijian Exp $
 */
public abstract class PlatformException extends RuntimeException {

    private static final long   serialVersionUID = -5143695406381565749L;

    /** 错误上下文 */
    @Getter
    protected ErrorContext      errorContext     = new ErrorContext();

    /** 当前错误码 */
    @Getter
    protected ErrorCode         currentErrorCode;

    private static final String SPLITOR          = "-";

    protected PlatformException(Throwable throwable) {
        super(throwable);
        populateErrorCode(throwable);
    }

    protected PlatformException(PlatformException e) {
        super(e.getCurrentErrorCode().toString() + SPLITOR + e.getCurrentErrorCode().getErrorDesc());
        populateErrorCode(e);
    }

    protected PlatformException(ErrorCode errorCode) {
        super(errorCode.toString() + SPLITOR + errorCode.getErrorDesc());
        this.currentErrorCode = errorCode;
    }

    protected PlatformException(String msg) {
        super(msg);
    }

    protected PlatformException(String msg, ErrorCode errorCode) {
        super(msg + errorCode.toString() + SPLITOR + errorCode.getErrorDesc());
        this.currentErrorCode = errorCode;
        this.addErrorCode(errorCode);
    }

    protected PlatformException(String msg, Throwable throwable) {
        super(msg, throwable);
        populateErrorCode(throwable);
    }

    protected PlatformException(String msg, PlatformException e) {
        super(msg + e.getCurrentErrorCode().toString() + SPLITOR + e.getCurrentErrorCode().getErrorDesc(), e);
        populateErrorCode(e);
    }

    /**
     * 向错误上下文中添加错误对象
     * 
     * @param errorCode 错误对象
     */
    public void addErrorCode(ErrorCode errorCode) {
        this.errorContext.addErrorCode(errorCode);
    }

    /**
     * 向错误上下文中添加另外一个错误上下文
     *
     * @param errorContext 错误对象
     */
    public void addErrorContext(ErrorContext errorContext) {
        this.errorContext.addErrorStack(errorContext);
    }

    @Override
    public String getMessage() {
        return buildMessage(super.getMessage(), getCause());
    }

    /**
     * Build a message for the given base message and root cause.
     * @param message the base message
     * @param cause the root cause
     * @return the full exception message
     */
    public static String buildMessage(String message, Throwable cause) {
        if (cause != null) {
            StringBuilder sb = new StringBuilder();
            if (message != null) {
                sb.append(message).append("; ");
            }
            sb.append("nested exception is ").append(cause);
            return sb.toString();
        } else {
            return message;
        }
    }

    /**
     * 填充errorCode
     * @param throwable
     */
    private void populateErrorCode(Throwable throwable) {
        if (throwable != null) {
            if (throwable instanceof PlatformException) {
                this.currentErrorCode = ((PlatformException) throwable).currentErrorCode;
                this.errorContext = ((PlatformException) throwable).errorContext;
            }
        }
    }

}
