package org.rify.common.exception.base;

import org.apache.commons.lang3.StringUtils;
import org.rify.common.utils.spring.SpringMessage;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/10/22下午10:43
 * @className : RifyBaseException
 * @description : 基础异常
 */
public class RifyBaseException extends RuntimeException {
    private String module;
    private String key;
    private String message;
    private Object[] args;

    public RifyBaseException() {
    }

    public RifyBaseException(String message) {
        this.message = message;
    }

    public RifyBaseException(String module, String key) {
        this.module = module;
        this.key = key;
    }

    public RifyBaseException(String key, Object[] args) {
        this.key = key;
        this.args = args;
    }

    public RifyBaseException(String module, String key, String message) {
        this.module = module;
        this.key = key;
        this.message = message;
    }

    public RifyBaseException(String module, String key, Object[] args) {
        this.module = module;
        this.key = key;
        this.args = args;
    }

    public RifyBaseException(String module, String key, String message, Object[] args) {
        this.module = module;
        this.key = key;
        this.message = message;
        this.args = args;
    }

    public @Override String getMessage() {
        return StringUtils.isNotBlank(key) ? SpringMessage.message(key, args) : StringUtils.isNotBlank(message) ? message : super.getMessage();
    }

    public String getModule() {
        return module;
    }

    public String getKey() {
        return key;
    }

    public Object[] getArgs() {
        return args;
    }
}
