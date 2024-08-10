package org.rify.common.exception.user;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2024/8/10 16:03
 * @className : RifyUserPasswordRetryLimitExceedException
 * @description :  用户密码重试次数超限异常
 */
public class RifyUserPasswordRetryLimitExceedException extends RifyUserException {
    public RifyUserPasswordRetryLimitExceedException(Integer maxRetries, Integer lockTime) {
        super("login.user.password.retry.limit.exceed", maxRetries, lockTime);
    }
}
