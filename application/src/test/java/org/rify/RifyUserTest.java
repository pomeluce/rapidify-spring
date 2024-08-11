package org.rify;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.rify.common.utils.spring.SecurityUtils;
import org.rify.server.system.domain.entity.QRifyUser;
import org.rify.server.system.domain.entity.RifyUser;
import org.rify.server.system.domain.enums.RifyUserStatus;
import org.rify.server.system.repository.RifyUserRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
public class RifyUserTest {

    private @Resource RifyUserRepository repository;

    public @Test void save() {
        String[] names = {"zhangsan", "lisi", "wangwu", "jack", "alan", "amy", "xiaoming", "liyang"};
        for (String name : names) {
            RifyUser user = RifyUser.builder()
                    .account(name).password(SecurityUtils.encoderPassword(name + "pass"))
                    .email(name + "@gmail.com").status(RifyUserStatus.ENABLED)
                    .role("everyone").permissions(List.of("user"))
                    .createBy(name).updateBy(name)
                    .build();
            repository.save(user);
        }
    }

    @Test
    void findRifyUserByAccountTest() {
        System.out.println(repository.findByAccount("zhangsan").orElseThrow());
    }

    public @Test void findByPage() {
        QRifyUser user = QRifyUser.rifyUser;
        PageRequest page = PageRequest.of(2, 2, Sort.by(Sort.Direction.ASC, "id").ascending());

        Optional<List<RifyUser>> list = repository.findByStatus(RifyUserStatus.ENABLED);
        System.out.println(list);

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
