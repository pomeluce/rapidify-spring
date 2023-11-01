package org.rify.common.utils;

import org.rify.common.text.Parser;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/10/29下午9:16
 * @className : StringUtils
 * @description : string 工具类
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    /**
     * 格式化字符串
     *
     * @param text 模板字符串 {@link String}
     * @param args 参数
     * @return 返回一个 String 类型的格式化字符串
     */
    public static String format(String text, Object... args) {
        return new Parser().parser("{", "}", text, args);
    }
}
