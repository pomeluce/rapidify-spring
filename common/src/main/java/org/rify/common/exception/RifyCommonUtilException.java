package org.rify.common.exception;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/29下午3:29
 * @className : RifyCommonUtilException
 * @description : 工具类异常
 */
public class RifyCommonUtilException extends RuntimeException {
    public RifyCommonUtilException(Throwable cause) {
        super(cause);
    }

    public RifyCommonUtilException(String message) {
        super(message);
    }

    public RifyCommonUtilException(String message, Throwable cause) {
        super(message, cause);
    }
}
