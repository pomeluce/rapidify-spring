package org.rify;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.rify.common.core.domain.entity.RifyUser;
import org.rify.server.system.mapper.RifyUserMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/27下午11:46
 * @className : SecurityTest
 * @description : Security 测试类
 */
@SpringBootTest
public class SecurityTest {
    private @Resource RifyUserMapper mapper;
    private @Resource BCryptPasswordEncoder encoder;

    public @Test void findAll() {
        List<RifyUser> selectList = mapper.selectList(new LambdaQueryWrapper<>());
        selectList.forEach(System.out::println);
    }

    public @Test void bcrypt() {
        // 加密测试
        String encode = encoder.encode("123456");
        System.out.println(encode);

        // 校验测试
        boolean matches = encoder.matches("1234", "$2a$10$ZqVB18PPA3P/MR9So/i8N.1UvVb.PblNl2sbj6pQJNDCgqiZqNQUm");
        System.out.println(matches);
    }

}
