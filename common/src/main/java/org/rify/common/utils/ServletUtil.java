package org.rify.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/28下午9:51
 * @className : ServletUtil
 * @description : 客户端工具类
 */
public class ServletUtil {

    /**
     * 获取 ServletRequestAttributes 对象
     *
     * @return 返回一个 ServletRequestAttributes 对象
     */
    private static ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * 获取 request 对象
     *
     * @return 返回一个 HttpServletRequest 对象
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取 response 对象
     *
     * @return 返回一个 HttpServletResponse 对象
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取 session 对象
     *
     * @return 返回一个 HttpSession 对象
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取请求参数
     *
     * @param request 请求对象 {@link HttpServletRequest}
     * @return 返回一个 Map<String, String[]> 类型的请求参数集合
     */
    public static Map<String, String[]> getUnmodifiableParamMap(HttpServletRequest request) {
        return Collections.unmodifiableMap(request.getParameterMap());
    }

    /**
     * 获取请求参数
     *
     * @param request http 请求对象 {@link HttpServletRequest}
     * @return 返回一个 Map<String, String> 类型的请求参数集合
     */
    public static Map<String, String> getParamMap(HttpServletRequest request) {
        return getUnmodifiableParamMap(request).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> StringUtils.join(e.getValue(), ",")));
    }
}
