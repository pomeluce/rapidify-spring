package org.rify.common.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/29下午3:51
 * @className : RifyConfig
 * @description : rapidify 配置类
 */
@Configuration
@ConfigurationProperties(prefix = "spring.rify")
public class RifyConfig {
    private static RifyConfig instance;
    private boolean enableLocation;
    private String tokenEncryptKey;
    private String issuer;
    private int expireTime;
    private int refreshExpireTime;

    /**
     * 初始化 RifyConfig 对象
     */
    public @PostConstruct void init() {
        instance = this;
    }

    public static RifyConfig instance() {
        return instance;
    }

    public boolean isEnableLocation() {
        return enableLocation;
    }

    public void setEnableLocation(boolean enableLocation) {
        this.enableLocation = enableLocation;
    }

    public String getTokenEncryptKey() {
        return tokenEncryptKey;
    }

    public void setTokenEncryptKey(String tokenEncryptKey) {
        this.tokenEncryptKey = tokenEncryptKey;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public int getRefreshExpireTime() {
        return refreshExpireTime;
    }

    public void setRefreshExpireTime(int refreshExpireTime) {
        this.refreshExpireTime = refreshExpireTime;
    }
}
