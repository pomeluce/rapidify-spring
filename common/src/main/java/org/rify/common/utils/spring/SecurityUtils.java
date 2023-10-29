package org.rify.common.utils.spring;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/10/29下午1:46
 * @className : SecurityUtils
 * @description : security 工具类
 */
public class SecurityUtils {
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    /**
     * 生成加密密码
     *
     * @param password 密码 {@link String}
     * @return 返回一个 String 类型的加密密码
     */
    public static String encoderPassword(String password) {
        return ENCODER.encode(password);
    }

    /**
     * 校验密码
     *
     * @param rowPassword     原始密码 {@link String}
     * @param encodedPassword 加密密码 {@link String}
     * @return 返回一个 boolean 类型的判断结果
     */
    public static boolean mathches(String rowPassword, String encodedPassword) {
        return ENCODER.matches(rowPassword, encodedPassword);
    }
}
