package org.rify.common.utils.spring;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.rify.common.core.domain.entity.HttpEntity;
import org.rify.common.utils.JacksonUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/28下午9:51
 * @className : ServletClient
 * @description : 客户端工具类
 */
public class ServletClient {

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

    /**
     * 响应数据到客户端
     *
     * @param response   HTTP 响应对象 {@link HttpServletResponse}
     * @param httpEntity HTTP 响应实体 {@link HttpEntity}
     * @param <K>        HTTP 响应实体的 key 类型
     * @param <V>        HTTP 响应实体的 value 类型
     * @throws IOException IO 异常
     */
    public static <K, V> void responseBody(HttpServletResponse response, HttpEntity<K, V> httpEntity) throws IOException {
        response.setStatus(httpEntity.getCode());
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JacksonUtil.toJsonString(httpEntity));
        writer.flush();
        writer.close();
    }

    /**
     * 设置请求 URI
     *
     * @param uri 请求 URI {@link String}
     */
    public static void setRequestURI(String uri) {
        getRequest().setAttribute("requestURI", uri);
    }

    /**
     * 获取请求 URI
     *
     * @return 返回一个 String 类型的 requestURI
     */
    public static String getRequestURI() {
        return (String) getRequest().getAttribute("requestURI");
    }
}
