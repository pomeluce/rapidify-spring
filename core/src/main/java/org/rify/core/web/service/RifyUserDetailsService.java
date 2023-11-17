package org.rify.core.web.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.rify.common.core.domain.entity.RifyUser;
import org.rify.common.core.domain.model.LoginUser;
import org.rify.common.exception.RifyServiceException;
import org.rify.common.utils.spring.SecurityUtils;
import org.rify.common.utils.spring.SpringMessage;
import org.rify.core.security.context.AuthenticationContextHolder;
import org.rify.server.system.mapper.RifyUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/27下午11:17
 * @className : RifyUserDetailsService
 * @description : 用户验证处理
 */
@Service
public class RifyUserDetailsService implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(RifyUserDetailsService.class);
    private @Resource RifyUserMapper mapper;

    public @Override UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<RifyUser> wrapper = new LambdaQueryWrapper<>();
        RifyUser user = mapper.selectOne(wrapper.eq(RifyUser::getAccount, username));
        if (user == null) {
            log.error("当前登录用户:{} 不存在", username);
            throw new RifyServiceException(SpringMessage.message("login.user.not.exists", username));
        }
        validateUser(user);
        return new LoginUser(user);
    }

    /**
     * 校验登录用户
     * 1. 校验登录密码
     * 2. 校验登录次数
     * 3. 检验登录锁定时间
     *
     * @param user 登录用户 {@link RifyUser}
     */
    private void validateUser(RifyUser user) {
        Authentication context = AuthenticationContextHolder.getContext();
        if (!SecurityUtils.matches(context.getCredentials().toString(), user.getPassword())) {
            // TODO: 登录校验
            log.warn("密码错误");
        }
    }
}
