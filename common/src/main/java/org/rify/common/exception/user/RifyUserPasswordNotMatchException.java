package org.rify.common.exception.user;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/10/28下午5:52
 * @className : RifyUserPasswordNotMatchException
 * @description : 用户密码不匹配异常
 */
public class RifyUserPasswordNotMatchException extends RifyUserException {
    public RifyUserPasswordNotMatchException() {
        super("login.user.password.not.match");
    }

    public RifyUserPasswordNotMatchException(String message) {
        super("login.user.password.not.match", message);
    }

}
