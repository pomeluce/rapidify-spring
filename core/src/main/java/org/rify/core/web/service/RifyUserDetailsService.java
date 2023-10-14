package org.rify.core.web.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.rify.common.core.domain.model.LoginUser;
import org.rify.common.core.domain.model.RifyUser;
import org.rify.server.system.mapper.RifyUserMapper;
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
    private @Resource RifyUserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<RifyUser> wrapper = new LambdaQueryWrapper<>();
        RifyUser user = mapper.selectOne(wrapper.eq(RifyUser::getAccount, username));
        if (user == null) throw new UsernameNotFoundException(username + "用户不存在!");
        return new LoginUser(user);
    }
}
