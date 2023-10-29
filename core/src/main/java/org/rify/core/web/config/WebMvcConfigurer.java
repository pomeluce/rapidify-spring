package org.rify.core.web.config;

import org.rify.common.config.RifyEnvironment;
import org.rify.common.utils.LocationUtil;
import org.rify.core.web.resolver.RifyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/10/29下午6:25
 * @className : WebMvcConfigurer
 * @description : web mvc 配置
 */
@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {

    /**
     * 注入国际化处理器
     *
     * @return 返回一个 LocaleResolver 类型的 locale 解析器
     */
    public @Bean LocaleResolver localeResolver() {
        @SuppressWarnings("unchecked") List<String> locales = RifyEnvironment.instance.get("spring.messages.support-locale", List.class, Collections.singletonList("zh_CN"));
        List<Locale> supportLocales = locales.stream().filter(locale -> locale.matches("^[a-z]{2}_[A-Z]{2}$")).map(LocationUtil::resolveLocale).distinct().toList();
        RifyLocaleResolver resolver = new RifyLocaleResolver();
        resolver.setSupportedLocales(supportLocales);
        resolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return resolver;
    }
}
