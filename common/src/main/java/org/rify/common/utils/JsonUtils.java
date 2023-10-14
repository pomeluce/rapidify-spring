package org.rify.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.rify.common.exception.CommonGlobalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/29下午3:25
 * @className : JsonUtils
 * @description : json 工具类
 */
public class JsonUtils {

    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将 object 对象转换为 json 字符串
     *
     * @param obj 待转换的 object 对象 {@link Object}
     * @return 返回一个 string 类型的 json 字符串
     */
    public static String toJsonString(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("object 转 json 字符串失败: {}", e.getMessage());
            throw new CommonGlobalException("object convert to json failed", e);
        }
    }

    /**
     * 将 json 字符串转换为 object
     *
     * @param json      待转换的 json 字符串 {@link String}
     * @param valueType 待转换的类型 {@link Class<T>}
     * @param <T>       泛型参数
     * @return 返回一个 T 类型的 object
     */
    public static <T> T parseValue(String json, Class<T> valueType) {
        try {
            return MAPPER.readValue(json, valueType);
        } catch (JsonProcessingException e) {
            log.error("json 字符串转 object 失败: {}", e.getMessage());
            throw new CommonGlobalException("json convert to object failed", e);
        }
    }
}
