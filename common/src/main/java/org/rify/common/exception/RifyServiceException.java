package org.rify.common.exception;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/10/28下午11:22
 * @className : RifyServiceException
 * @description : service 通用异常类
 */
public class RifyServiceException extends RuntimeException {
    private String message;

    public RifyServiceException() {
    }

    public RifyServiceException(String message) {
        this.message = message;
    }

    public @Override String getMessage() {
        return message;
    }

    public RifyServiceException setMessage(String message) {
        this.message = message;
        return this;
    }
}
