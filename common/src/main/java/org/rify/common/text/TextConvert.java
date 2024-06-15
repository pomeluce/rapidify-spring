package org.rify.common.text;

import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/11/1下午10:30
 * @className : TextConvert
 * @description : 字符串转换工具
 */
public final class TextConvert implements Serializable {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private TextConvert() {
    }

    public static TextConvert instance() {
        return new TextConvert();
    }

    /**
     * 字符串转换
     * <br>
     * <p>将 byte[] 字节数组按照指定字符集转换成 string 字符串</p>
     * <br>
     *
     * @param bytes       待转换的字节数组 {@link Byte[]}
     * @param charsetName 转换字符集名称 {@link String}
     * @return 返回一个 {@link String} 类型的转换结果
     */
    public String string(byte[] bytes, String charsetName) {
        return string(bytes, Charset.forName(charsetName, DEFAULT_CHARSET));
    }

    /**
     * 字符串转换
     * <br>
     * <p>将 byte[] 字节数组按照指定字符集转换成 string 字符串</p>
     * <br>
     *
     * @param bytes   待转换的字节数组 {@link Byte[]}
     * @param charset 转换字符集 {@link Charset}
     * @return 返回一个 {@link String} 类型的转换结果
     */
    public String string(byte[] bytes, Charset charset) {
        return Objects.isNull(bytes) ? null : new String(bytes, Objects.isNull(charset) ? DEFAULT_CHARSET : charset);
    }

    /**
     * 字符串转换
     * <br>
     * <p>将 ByteBuffer 对象按照指定字符集转换成 string 字符串</p>
     * <br>
     *
     * @param buffer      待转换的 byteBuffer 对象 {@link ByteBuffer}
     * @param charsetName 转换字符集名称 {@link Charset}
     * @return 返回一个 {@link String} 类型的转换结果
     */
    public String string(ByteBuffer buffer, String charsetName) {
        return string(buffer, Charset.forName(charsetName, DEFAULT_CHARSET));
    }

    /**
     * 字符串转换
     * <br>
     * <p>将 ByteBuffer 对象按照指定字符集转换成 string 字符串</p>
     * <br>
     *
     * @param buffer  待转换的 byteBuffer 对象 {@link ByteBuffer}
     * @param charset 转换字符集 {@link Charset}
     * @return 返回一个 {@link String} 类型的转换结果
     */
    public String string(ByteBuffer buffer, Charset charset) {
        return (Objects.isNull(charset) ? DEFAULT_CHARSET : charset).decode(buffer).toString();
    }

    /**
     * 字符串转换
     * <br>
     * <p>将 object 对象按照指定字符集转换成 string 字符串</p>
     * <br>
     *
     * @param arg         待转化的 object 对象 {@link Object}
     * @param charsetName 转换字符集名称 {@link String}
     * @return 返回一个 {@link String} 类型的转换结果
     */
    public String string(Object arg, String charsetName) {
        return string(arg, Charset.forName(charsetName, DEFAULT_CHARSET));
    }

    /**
     * 字符串转换
     * <br>
     * <p>将 object 对象按照指定字符集转换成 string 字符串</p>
     * <br>
     *
     * @param arg     待转化的 object 对象 {@link Object}
     * @param charset 转换字符集 {@link Charset}
     * @return 返回一个 {@link String} 类型的转换结果
     */
    public String string(Object arg, Charset charset) {
        return switch (arg) {
            case String text -> text;
            case byte[] bytes -> string(bytes, charset);
            case Byte[] bytes -> string(ArrayUtils.toPrimitive(bytes), charset);
            case ByteBuffer buffer -> string(buffer, charset);
            case null -> null;
            default -> arg.toString();
        };
    }

    /**
     * 字符串转换
     * <br>
     * <p>将 object 对象按照默认字符集转换成 string 字符串</p>
     * <br>
     *
     * @param text 待转换的 object 对象 {@link Object}
     * @return 返回一个 {@link String} 类型的转换结果
     */
    public String toString(Object text) {
        return string(text, DEFAULT_CHARSET);
    }
}
