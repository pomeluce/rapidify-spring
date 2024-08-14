package org.rify;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.rify.common.core.page.PaginationSupport;
import org.rify.common.utils.spring.SecurityUtils;
import org.rify.server.system.domain.entity.QUser;
import org.rify.server.system.domain.entity.User;
import org.rify.server.system.domain.enums.UserStatus;
import org.rify.server.system.repository.SystemUserRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2024/8/10 20:48
 * @className : RifyUserTest
 * @description : RifyUser 测试类
 */
@SpringBootTest
public class UserTest {

    private @Resource SystemUserRepository repository;

    @Test
    void save() {
        String[] names = {"zhangsan", "lisi", "wangwu", "jack", "alan", "amy", "xiaoming", "liyang"};
        for (String name : names) {
            User user = User.builder()
                    .account(name)
                    .password(SecurityUtils.encoderPassword(name + "pass"))
                    .email(name + "@gmail.com")
                    .status(UserStatus.ENABLED)
                    .role("everyone")
                    .createBy(name)
                    .updateBy(name)
                    .build();
            repository.save(user);
        }
    }

    @Test
    void findRifyUserByAccountTest() {
        System.out.println(repository.findUserByAccount("zhangsan").orElseThrow());
    }

    @Test
    void findUserList() {
        Optional<List<User>> result = repository.findUserList(User.builder().email("@qq.com").build(), PaginationSupport.pageable().setDefaultOrder(QUser.user.id.desc()));
        result.ifPresent(System.out::println);
    }
}
