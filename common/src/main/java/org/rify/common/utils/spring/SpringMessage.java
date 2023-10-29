package org.rify.common.utils.spring;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/10/28下午10:31
 * @className : SpringMessage
 * @description : spring 信息国际化
 */
public class SpringMessage {

    /**
     * 获取国际化信息
     *
     * @param key    消息 key {@link String}
     * @param params 参数 {@link Object}
     * @return 返回一个 String 类型的国际化信息
     */
    public static String message(String key, Object... params) {
        MessageSource message = SpringClient.getBean(MessageSource.class);
        return message.getMessage(key, params, LocaleContextHolder.getLocale());
    }

}
