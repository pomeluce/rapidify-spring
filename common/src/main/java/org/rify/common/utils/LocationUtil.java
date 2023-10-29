package org.rify.common.utils;

import org.rify.common.config.RifyProperty;
import org.rify.common.core.http.HttpResult;
import org.rify.common.core.http.HttpRequest;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/29下午2:13
 * @className : LocationUtil
 * @description : 地址解析工具类
 */
public class LocationUtil {
    private static final String UNKNOWN = "xx-xx-xx";
    private static final String IP = "http://whois.pconline.com.cn/ipJson.jsp";
    private static final RifyProperty.Config RIFY_CONFIG = RifyProperty.instance().getConfig();

    /**
     * 根据 ip 地址获取位置信息
     *
     * @param ip 要获取地址的 ip 地址 {@link String}
     * @return 返回一个 String 类型的位置信息
     */
    public static String getRelativeLocation(String ip) {
        // 是否为内网地址
        if (IpAddrUtil.isInternalIP(ip)) {
            return "内网 IP";
        }

        HttpResult result;

        // 是否为开启了定位功能, 并且获取定位信息成功
        if (RIFY_CONFIG.isEnableLocation() && (result = HttpRequest.instance().GET(IP + "?ip=" + ip + "&json=true")).statusCode() == HttpStatus.OK.value()) {
            @SuppressWarnings("unchecked") Map<String, String> map = JacksonUtil.parseValue(result.body(), Map.class);
            return String.format("省份: %s 城市: %s", map.get("pro"), map.get("city"));
        }
        return UNKNOWN;
    }

}
