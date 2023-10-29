package org.rify.core.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.rify.common.core.domain.entity.HttpEntity;
import org.rify.common.utils.spring.ServletClient;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/10/21下午6:06
 * @className : AuthEntryPointHandler
 * @description : 匿名请求处理器
 */
@Component
public class AuthEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        HttpStatus status = Objects.requireNonNullElse(HttpStatus.resolve(response.getStatus()), HttpStatus.UNAUTHORIZED);
        String message = String.format(switch (status) {
            case HttpStatus.OK, HttpStatus.UNAUTHORIZED -> {
                status = HttpStatus.UNAUTHORIZED;
                yield "请求资源 %s 需要认证";
            }
            case HttpStatus.FORBIDDEN -> "请求资源 %s 需要授权";
            case HttpStatus.NOT_FOUND -> "请求资源 %s 不存在";
            default -> "请求访问资源 %s 失败";
        }, ServletClient.getRequestURI());
        ServletClient.responseBody(response, HttpEntity.instance(status.value(), message));
    }
}
