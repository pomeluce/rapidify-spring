package org.rify.common.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/10/14下午1:21
 * @className : RifyEnvironment
 * @description : Environment 环境变量配置类
 */
@Configuration
public class RifyEnvironment implements EnvironmentAware {

    public static RifyEnvironment instance;
    private Environment env;
    private final Logger log = LoggerFactory.getLogger(RifyEnvironment.class);

    /**
     * 初始化 RifyProperty 对象
     */
    private @PostConstruct void init() {
        instance = this;
    }

    /**
     * 获取 property 属性值
     *
     * @param key 待获取的 property 属性 key {@link String}
     * @return 返回一个 Object 类型的 property 值
     */
    public Object get(String key) {
        return env.getProperty(key);
    }

    /**
     * 获取 property 属性值
     *
     * @param key        待获取的 property 属性 key {@link String}
     * @param targetType 待获取的 property 类型 {@link Class<T>}
     * @param <T>        泛型参数 {@link T}
     * @return 返回一个泛型为 T 类型的 property 值
     */
    public <T> T get(String key, Class<T> targetType) {
        T value = env.getProperty(key, targetType);
        return value == null ? defaultClassValue(targetType) : value;
    }

    /**
     * 获取 property 属性值
     *
     * @param key          待获取的 property 属性 key {@link String}
     * @param targetType   待获取的 property 类型 {@link Class<T>}
     * @param defaultValue 默认值
     * @param <T>          泛型参数 {@link T}
     * @return 返回一个泛型为 T 类型的 property 值
     */
    public <T> T get(String key, Class<T> targetType, T defaultValue) {
        return env.getProperty(key, targetType, defaultValue);
    }

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        this.env = environment;
    }

    /**
     * 根据字节码类型获取默认值
     *
     * @param targetType 要获取默认值的字节码类型
     * @param <T>        泛型参数
     * @return 返回一个泛型为 T 类型的默认值
     */
    private <T> T defaultClassValue(Class<T> targetType) {
        String typeName = targetType.getSimpleName();
        @SuppressWarnings("unchecked") T value = typeName.endsWith("[]") ? (T) Array.newInstance(targetType.getComponentType(), 0) : switch (typeName) {
            case "byte" -> (T) Byte.valueOf((byte) 0);
            case "short" -> (T) Short.valueOf((short) 0);
            case "int" -> (T) Integer.valueOf(0);
            case "long" -> (T) Long.valueOf(0);
            case "float" -> (T) Float.valueOf(0);
            case "double" -> (T) Double.valueOf(0);
            case "boolean" -> (T) Boolean.FALSE;
            case "char" -> (T) Character.valueOf('\u0000');
            case "*[]" -> (T) new Object[]{};
            default -> {
                try {
                    Constructor<T> constructor = targetType.getConstructors().length != 0
                            ? targetType.getConstructor()
                            : targetType.getDeclaredConstructors().length != 0
                            ? targetType.getDeclaredConstructor()
                            : null;
                    if (constructor != null) {
                        constructor.setAccessible(true);
                        yield constructor.newInstance();
                    }
                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                         IllegalAccessException e) {
                    log.error("{} get {}.class default value failed", getClass().getSimpleName(), typeName, e);
                }
                yield null;
            }
        };
        return value;
    }
}
