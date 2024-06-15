package org.rify.common.utils.location;

import org.rify.common.config.RifyProperty;
import org.rify.common.core.http.HttpRequest;
import org.rify.common.core.http.HttpResult;
import org.rify.common.utils.JacksonUtils;
import org.rify.common.utils.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.Locale;
import java.util.Map;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/29下午2:13
 * @className : LocationUtils
 * @description : 地址解析工具类
 */
public class LocationUtils {
    private static final String UNKNOWN = "xx-xx-xx";
    private static final String IP = "http://whois.pconline.com.cn/ipJson.jsp";
    private static final RifyProperty.Config RIFY_CONFIG = RifyProperty.instance().getConfig();
    public static final String LOCALE_REGEX = "^[a-z]{2}_[A-Z]{2}$";

    /**
     * 根据 ip 地址获取位置信息
     *
     * @param ip 要获取地址的 ip 地址 {@link String}
     * @return 返回一个 String 类型的位置信息
     */
    public static String getRelativeLocation(String ip) {
        // 是否为内网地址
        if (IpAddrUtils.isInternalIP(ip)) {
            return "内网 IP";
        }

        HttpResult result;

        // 是否为开启了定位功能, 并且获取定位信息成功
        if (RIFY_CONFIG.isEnableLocation() && (result = HttpRequest.instance().GET(IP + "?ip=" + ip + "&json=true")).statusCode() == HttpStatus.OK.value()) {
            @SuppressWarnings("unchecked") Map<String, String> map = JacksonUtils.parseValue(result.body(), Map.class);
            return StringUtils.format("省份: {} 城市: {}", map.get("pro"), map.get("city"));
        }
        return UNKNOWN;
    }

    /**
     * 根据语言简称获取 Locale 对象
     *
     * @param locale 要获取的 Local 对象的名称 {@link String}
     * @return 返回一个 Locale 类型的 Local 对象
     */
    public static Locale resolveLocale(String locale) {
        if (StringUtils.isEmpty(locale) || !locale.matches(LOCALE_REGEX)) {
            return Locale.getDefault();
        }
        String[] lang = locale.split("_");
        return Locale.of(lang[0], lang[1]);
    }

}
