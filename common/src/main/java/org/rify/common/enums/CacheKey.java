package org.rify.common.enums;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/29下午7:23
 * @className : CacheKey
 * @description : TODO(一句话描述该类的功能)
 */
public enum CacheKey {
    ACCESS_TOKEN_KEY("access_token");

    private final String key;

    CacheKey(String key) {
        this.key = key;
    }

    public String value() {
        return key;
    }
}
