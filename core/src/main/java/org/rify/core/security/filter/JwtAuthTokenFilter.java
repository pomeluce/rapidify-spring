package org.rify.core.security.filter;

import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.rify.common.core.domain.model.LoginUser;
import org.rify.core.web.service.RifyTokenService;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/10/14下午8:37
 * @className : JwtAuthTokenFilter
 * @description : security token 解析过滤器
 */
@Configuration
public class JwtAuthTokenFilter extends OncePerRequestFilter {
    private @Resource RifyTokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Expose-Headers", "refresh-token");

        // 从请求头获取 token
        String token = tokenService.getToken(request);
        // 判断 token 是否为空, 检查 token 是否有效, 是否在黑名单中, 是否已认证
        if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication()) && StringUtils.isNotEmpty(token) && tokenService.checkToken(token) && tokenService.isNotBlackList(token)) {
            // 判断 token 是否过期
            if (tokenService.isExpired(token)) {
                // 将过期 token 加入黑名单
                tokenService.putBlackList(token);
                // 判断是否可以刷新 token
                if (tokenService.isRefresh(token)) {
                    // 生成新 token
                    String refreshToken = tokenService.refreshToken(token);
                    // 放入 response 请求头
                    response.setHeader("refresh-token", refreshToken);
                }
            }
            // 获取用户信息
            LoginUser user = tokenService.getLoginUser(token);
            if (!Objects.isNull(user)) {
                // 如果用户信息不为空, 则将用户信息放入 SecurityContext 中
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
