package org.rify.common.exception.user;

import org.rify.common.exception.base.RifyBaseException;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/10/28下午6:03
 * @className : RifyUserException
 * @description : 用户异常
 */
public class RifyUserException extends RifyBaseException {
    public RifyUserException(String key) {
        super("user", key);
    }

    public RifyUserException(String key, String message) {
        super("user", key, message);
    }

    public RifyUserException(String key, Object... args) {
        super("user", key, args);
    }
}
