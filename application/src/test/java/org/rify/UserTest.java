package org.rify;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.rify.common.utils.spring.SecurityUtils;
import org.rify.server.system.domain.entity.User;
import org.rify.server.system.domain.enums.UserStatus;
import org.rify.server.system.repository.SystemUserRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    public @Test void save() {
        String[] names = {"zhangsan", "lisi", "wangwu", "jack", "alan", "amy", "xiaoming", "liyang"};
        for (String name : names) {
            User user = User.builder()
                    .account(name).password(SecurityUtils.encoderPassword(name + "pass"))
                    .email(name + "@gmail.com").status(UserStatus.ENABLED)
                    .role("everyone").permissions(List.of("user"))
                    .createBy(name).updateBy(name)
                    .build();
            repository.save(user);
        }
    }

    @Test
    void findRifyUserByAccountTest() {
        System.out.println(repository.findUserByAccount("zhangsan").orElseThrow());
    }

    public @Test void findByPage() {
        // Optional<List<User>> list = repository.findUserList();
        // System.out.println(list);
        //
        // System.out.println(Pageable.builder().build().getSort());

        // List<RifyUser> list = factory.selectFrom(user)
        //         .where(user.status.eq(RifyUserStatus.ENABLED))
        //         .offset(page.getOffset())
        //         .limit(page.getPageSize())
        //         .fetch();

        // BlazeJPAQuery<RifyUser> query = factory.selectFrom(user)
        //         .where(user.status.eq(RifyUserStatus.ENABLED))
        //         .orderBy(user.id.asc());
        //
        // query.orderBy(user.id.asc());
        //
        // List<RifyUser> list = factory.selectFrom(user)
        //         .where(user.status.eq(RifyUserStatus.ENABLED))
        //         .orderBy(user.id.asc())
        //         .fetchPage((int) page.getOffset(), page.getPageSize());
        // System.out.println(list);
    }
}
