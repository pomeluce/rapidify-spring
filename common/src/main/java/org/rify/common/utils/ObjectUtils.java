package org.rify.common.utils;

import org.rify.common.exception.RifyCommonUtilException;
import org.rify.common.utils.beans.BeanCopyOptions;
import org.rify.common.utils.beans.BeanUtils;

import java.io.*;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/11/11下午3:46
 * @className : ObjectUtils
 * @description : 对象工具类
 */
public final class ObjectUtils {

    public static <T extends Serializable> T merge(T source, T target) {
        return merge(source, target, false);
    }

    /**
     * 对象合并
     * <br>
     * <p> 该方法仅适用于实现了 Serializable 接口的对象 </p>
     *
     * @param source     源对象 {@link T}
     * @param target     目标对象 {@link T}
     * @param isOverride 是否覆盖 {@link Boolean}
     * @param <T>        泛型
     * @return 返回一个实现了 Serializable 接口的 T 类型的对象的合并结果
     */
    public static <T extends Serializable> T merge(T source, T target, boolean isOverride) {
        T t = deepClone(target);
        BeanUtils.copyProperties(source, t, BeanCopyOptions.instance().ignoreNullValue().setOverride(isOverride));
        return t;
    }

    /**
     * 深拷贝方法
     * <br>
     * <strong>Tips: 该方法仅适用于实现了 Serializable 接口的对象</strong>
     * <br>
     *
     * @param source 源对象 {@link T}
     * @param <T>    泛型
     * @return 返回一个实现了 Serializable 接口的 T 类型的深拷贝对象
     */
    public static @SuppressWarnings("unchecked") <T extends Serializable> T deepClone(T source) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(byteOut)) {
            out.writeObject(source);
            out.flush();

            try (InputStream is = new ByteArrayInputStream(byteOut.toByteArray())) {
                ObjectInputStream in = new ObjectInputStream(is);
                return (T) in.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RifyCommonUtilException(e);
        }
    }
}
