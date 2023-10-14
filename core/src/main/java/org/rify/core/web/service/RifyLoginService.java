package org.rify.core.web.service;

import jakarta.annotation.Resource;
import org.rify.common.core.domain.model.LoginUser;
import org.rify.common.core.domain.model.RifyUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/27下午11:16
 * @className : RifyLoginService
 * @description : 登录校验服务
 */
@Service
public class RifyLoginService {
    private @Resource AuthenticationManager authenticationManager;
    private @Resource RifyTokenService tokenService;

    /**
     * 用户登录
     *
     * @param user 用户信息 {@link RifyUser}
     * @return 返回 String 类型的 token 信息
     */
    public String login(RifyUser user) {
        // 获取 authenticationToken 对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getAccount(), user.getPassword());
        // 调用 authenticationManager 的 authenticate 方法进行登录验证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 判断是否登录成功
        if (authenticate == null) throw new RuntimeException("用户名或密码错误!");
        // 获取登录成功的用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        // 返回结果
        return tokenService.accessToken(loginUser);
    }
}
