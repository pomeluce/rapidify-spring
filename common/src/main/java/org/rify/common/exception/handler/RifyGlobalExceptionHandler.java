package org.rify.common.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.rify.common.core.domain.HttpEntity;
import org.rify.common.exception.RifyServiceException;
import org.rify.common.exception.user.RifyUserPasswordNotMatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/10/28下午11:42
 * @className : RifyGlobalExceptionHandler
 * @description : 全局异常处理
 */
@RestControllerAdvice
public class RifyGlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(RifyGlobalExceptionHandler.class);

    /**
     * RifyServiceException 异常处理
     *
     * @param request 请求对象 {@link HttpServletRequest}
     * @param e       异常对象 {@link RifyServiceException}
     * @return 返回一个泛型为 String, Object 的 HttpEntity 对象
     */
    public @ExceptionHandler(RifyServiceException.class) HttpEntity<String, Object> serviceExceptionHandler(HttpServletRequest request, RifyServiceException e) {
        log.error("发生业务异常: {}", e.getMessage());
        return HttpEntity.instance(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    /**
     * RifyUserPasswordNotMatchException 异常处理
     *
     * @param request 请求对象 {@link HttpServletRequest}
     * @param e       异常对象 {@link RifyUserPasswordNotMatchException}
     * @return 返回一个泛型为 String, Object 的 HttpEntity 对象
     */
    public @ExceptionHandler(RifyUserPasswordNotMatchException.class) HttpEntity<String, Object> userPasswordNotMatchExceptionHandler(HttpServletRequest request, RifyUserPasswordNotMatchException e) {
        log.error("用户登录发生异常: {}", e.getMessage());
        return HttpEntity.instance(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }
}
