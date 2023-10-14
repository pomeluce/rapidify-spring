package org.rify.core.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.rify.common.config.RifyProperty;
import org.rify.core.web.service.RifyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/27下午11:07
 * @className : SecurityConfiguration
 * @description : security 核心配置类
 */
@Configuration
public class SecurityConfiguration {
    private @Resource RifyUserDetailsService service;
    private @Resource AuthenticationConfiguration authenticationConfiguration;
    private @Resource RifyProperty env;
    private String[] matchers = {"/**"};

    private @PostConstruct void init() {
        Boolean enabled = env.get("spring.security.enabled", Boolean.class, true);
        matchers = Boolean.TRUE.equals(enabled) ? env.get("spring.security.matchers", String[].class) : matchers;
    }

    /**
     * 生成 BCryptPasswordEncoder 对象
     *
     * @return 返回一个 BCryptPasswordEncoder 加密对象
     */
    public @Bean BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * AuthenticationManager 认证管理器
     *
     * @return 返回一个认证管理器
     * @throws Exception 抛出异常
     */
    public @Bean AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * security 核心配置
     *
     * @param http http 安全配置对象 {@link HttpSecurity}
     * @return 返回一个 HttpSecurity 对象
     * @throws Exception 抛出异常
     */
    public @Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(matchers)
                        .anonymous()
                        .anyRequest()
                        .authenticated()
                );
        return http.build();
    }
}
