package org.bravo.gaia.commons.exception;

import lombok.Getter;
import lombok.Setter;
import org.bravo.gaia.commons.commoninterface.IErrorCodeEnum;
import org.bravo.gaia.commons.context.ErrorContext;
import org.bravo.gaia.commons.data.CommonError;
import org.bravo.gaia.commons.data.ErrorCode;

/**
 * 平台公共异常类，所有application的异常类都需要继承此类。
 * 
 * <p><b>注意：</b>
 * <p>子类继承时，必须实现此类所有的构造方法。即：XXX(String msg)，XXX(Throwable throwable)，XXX(String msg, Throwable throwable)
 * <p>子类使用的异常码，必须实现ErrorCode接口，{@link #addSuppressed(Throwable)}
 * 
 * @author lijian
 * @version $Id: PlatformException.java, v 0.1 2018年1月6日 上午11:50:10 lijian Exp $
 */
public abstract class PlatformException extends RuntimeException {

	private static final long serialVersionUID = -5143695406381565749L;
	
	/** 错误上下文 */
	@Setter
	@Getter
	protected ErrorContext errorContext = new ErrorContext();

    @Setter
    @Getter
	protected ErrorCode errorCode;

	public PlatformException(Throwable throwable) {
	    super(throwable);
        populateErrorCode(throwable);
    }

    public PlatformException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public PlatformException(String msg, ErrorCode errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

	public PlatformException(String msg) {
		super(msg);
	}

	public PlatformException(String msg, Throwable throwable) {
		super(msg, throwable);
        populateErrorCode(throwable);
	}
	
	/**
	 * 向错误上下文中添加错误对象
	 * 
	 * @param error 错误对象
	 */
	public void addErrorCode(CommonError error) {
        this.errorContext.addError(error);
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
		}
		else {
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
                this.errorCode = ((PlatformException) throwable).getErrorCode();
            }
        }
    }
	
	public abstract void setErrorCodeEnum(IErrorCodeEnum errorCodeEnum);
	
}
