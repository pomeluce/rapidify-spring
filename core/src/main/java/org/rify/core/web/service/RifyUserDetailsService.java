package org.rify.core.web.service;

import jakarta.annotation.Resource;
import org.rify.common.config.RifyProperty;
import org.rify.common.core.redis.RedisClient;
import org.rify.common.enums.CacheKey;
import org.rify.common.exception.RifyServiceException;
import org.rify.common.exception.user.RifyUserPasswordNotMatchException;
import org.rify.common.exception.user.RifyUserPasswordRetryLimitExceedException;
import org.rify.common.utils.ObjectUtils;
import org.rify.common.utils.spring.SecurityUtils;
import org.rify.common.utils.spring.SpringMessage;
import org.rify.core.security.context.AuthenticationContextHolder;
import org.rify.server.system.domain.entity.RifyUser;
import org.rify.server.system.domain.model.LoginUser;
import org.rify.server.system.repository.RifyUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/27 11:17
 * @className : RifyUserDetailsService
 * @description : 用户验证处理
 */
@Service
public class RifyUserDetailsService implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(RifyUserDetailsService.class);

    private @Resource RifyProperty property;
    private @Resource RifyUserRepository repository;
    private @Resource RedisClient redisClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RifyUser user = repository.findRifyUserByAccount(username).orElseThrow(() -> {
            log.error("当前登录用户:{} 不存在", username);
            return new RifyServiceException(SpringMessage.message("login.user.not.exists", username));
        });

        /*
         校验用户状态
          1. 判断用户是否被删除
          2. 判断用户是否被禁用
          3. 判断用户是否被锁定
         */
        switch (user.getStatus()) {
            case DELETE -> {
                log.warn("当前登录用户:{} 已被删除", user.getAccount());
                throw new RifyServiceException(SpringMessage.message("login.user.deleted", user.getAccount()));
            }
            case DISABLED -> {
                log.warn("当前登录用户:{} 已被禁用", user.getAccount());
                throw new RifyServiceException(SpringMessage.message("login.user.disabled", user.getAccount()));
            }
            case ENABLED -> isLock(user);
        }

        return new LoginUser(user);
    }

    /**
     * 校验登录用户
     * 1. 校验错误次数
     * 2. 校验登录密码
     * 3. 校验登录次数
     *
     * @param user 登录用户 {@link RifyUser}
     */
    private void isLock(RifyUser user) {
        Authentication context = AuthenticationContextHolder.getContext();
        String account = context.getName();
        Integer maxRetries = property.getUser().getMaxRetries();
        Integer lockTime = property.getUser().getLockTime();
        Integer count = ObjectUtils.isEmpty(redisClient.hget(CacheKey.PASSWORD_RETRIES_KEY.value(), account), 0);

        // 校验错误次数
        if (count >= maxRetries) {
            log.warn("当前登录用户:{} 密码错误错误次数超过限制, 已被锁定 {} 分钟", user.getAccount(), lockTime);
            throw new RifyUserPasswordRetryLimitExceedException(maxRetries, lockTime);
        }

        // 校验登录密码
        if (!SecurityUtils.matches(context.getCredentials().toString(), user.getPassword())) {
            redisClient.hset(CacheKey.PASSWORD_RETRIES_KEY.value(), account, ++count, lockTime, TimeUnit.MINUTES);
            log.warn("当前登录用户:{} 密码错误", user.getAccount());
            throw new RifyUserPasswordNotMatchException();
        }
        // 登录成功, 清空错误次数
        else {
            redisClient.hdel(CacheKey.PASSWORD_RETRIES_KEY.value(), account);
        }
    }
}
