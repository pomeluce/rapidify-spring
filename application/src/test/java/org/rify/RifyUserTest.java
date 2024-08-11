package org.rify;

import com.blazebit.persistence.querydsl.BlazeJPAQueryFactory;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.rify.common.utils.spring.SecurityUtils;
import org.rify.core.types.ProjectionsQMap;
import org.rify.server.system.domain.entity.QRifyUser;
import org.rify.server.system.domain.entity.RifyUser;
import org.rify.server.system.domain.enums.RifyUserStatus;
import org.rify.server.system.repository.RifyUserRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

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
    private @Resource BlazeJPAQueryFactory factory;

    public @Test void save() {
        String[] names = {"zhangsan", "lisi", "wangwu", "jack", "alan", "amy", "xiaoming", "liyang"};
        for (String name : names) {

        }
        RifyUser user = new RifyUser();
        user.setAccount("zhangsan");
        user.setPassword(SecurityUtils.encoderPassword("password"));
        user.setEmail("zhangsan@qq.com");
        user.setStatus(RifyUserStatus.ENABLED);
        repository.save(user);
    }

    public @Test void findRifyUserByAccount() {
        QRifyUser user = QRifyUser.rifyUser;
        Map<String, ?> result = factory.select(ProjectionsQMap.builder(user.account.as("username"), user.email))
                .from(user)
                .where(user.account.eq("zhangsan"))
                .fetchOne();
        System.out.println(result);
    }
}
