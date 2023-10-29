package org.rify.common.exception.user;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/10/28下午11:07
 * @className : RifyUserNotExistsException
 * @description : 用户不存在异常
 */
public class RifyUserNotExistsException extends RifyUserException {
    public RifyUserNotExistsException(Object... args) {
        super("login.user.not.exists", args);
    }
}
