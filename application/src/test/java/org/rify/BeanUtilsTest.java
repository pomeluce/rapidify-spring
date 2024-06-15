package org.rify;

import org.junit.jupiter.api.Test;
import org.rify.server.system.domain.entity.RifyUser;
import org.rify.common.utils.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.util.Arrays;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/11/12下午1:49
 * @className : BeanUtilsTest
 * @description : bean 工具测试类
 */
public class BeanUtilsTest {

    public @Test void getPropertyDescriptorsTest() {
        PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(RifyUser.class);
        Arrays.stream(descriptors).forEach(System.out::println);
        PropertyDescriptor descriptor = descriptors[0];
        System.out.println(descriptor.getReadMethod());
        System.out.println(descriptor.getWriteMethod());
        System.out.println(descriptor.getReadMethod().getReturnType().isAssignableFrom(descriptor.getWriteMethod().getParameterTypes()[0]));
    }

    public @Test void copyPropertiesTest() {
        RifyUser user1 = new RifyUser();
        user1.setAccount("2222222");
        // user1.setAge(20);
        user1.setEmail(null);

        RifyUser user2 = new RifyUser();
        // user2.setAge(18);
        user2.setEmail("aaaaaaaaa");
        BeanUtils.copyProperties(user1, user2);
        System.out.println(user2);
    }
}
