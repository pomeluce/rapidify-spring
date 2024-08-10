package org.rify.common.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/29下午3:51
 * @className : RifyProperty
 * @description : rapidify 配置类
 */
@Configuration
@ConfigurationProperties(prefix = "spring.rify")
public class RifyProperty {
    private static RifyProperty instance;
    private Config config;
    private Security security;
    private Token token;
    private Cors cors;
    private User user;

    /**
     * 初始化 RifyConfig 对象
     */
    public @PostConstruct void init() {
        instance = this;
    }

    public static RifyProperty instance() {
        return instance;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public static class Security {
        private boolean enabled = false;
        private List<String> matchers;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public List<String> getMatchers() {
            return matchers;
        }

        public void setMatchers(List<String> matchers) {
            this.matchers = matchers;
        }
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public static class Config {
        private boolean enableLocation;

        public boolean isEnableLocation() {
            return enableLocation;
        }

        public void setEnableLocation(boolean enableLocation) {
            this.enableLocation = enableLocation;
        }
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public static class Token {
        private String algorithm;
        private String encryptKey;
        private String issuer;
        private int expireTime;
        private int refreshExpireTime;
        private String headerKey;

        public String getAlgorithm() {
            return algorithm;
        }

        public String getEncryptKey() {
            return encryptKey;
        }

        public String getIssuer() {
            return issuer;
        }

        public int getExpireTime() {
            return expireTime;
        }

        public int getRefreshExpireTime() {
            return refreshExpireTime;
        }

        public String getHeaderKey() {
            return headerKey;
        }

        public void setAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }

        public void setEncryptKey(String encryptKey) {
            this.encryptKey = encryptKey;
        }

        public void setIssuer(String issuer) {
            this.issuer = issuer;
        }

        public void setExpireTime(int expireTime) {
            this.expireTime = expireTime;
        }

        public void setRefreshExpireTime(int refreshExpireTime) {
            this.refreshExpireTime = refreshExpireTime;
        }

        public void setHeaderKey(String headerKey) {
            this.headerKey = headerKey;
        }
    }

    public Cors getCors() {
        return cors;
    }

    public void setCors(Cors cors) {
        this.cors = cors;
    }

    public static class Cors {
        private List<String> allowedOrigins;
        private List<String> allowedMethods;

        public List<String> getAllowedOrigins() {
            return allowedOrigins;
        }

        public List<String> getAllowedMethods() {
            return allowedMethods;
        }

        public void setAllowedOrigins(List<String> allowedOrigins) {
            this.allowedOrigins = allowedOrigins;
        }

        public void setAllowedMethods(List<String> allowedMethods) {
            this.allowedMethods = allowedMethods;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class User {
        private Integer maxRetries;
        private Integer lockTime;

        public Integer getMaxRetries() {
            return maxRetries;
        }

        public Integer getLockTime() {
            return lockTime;
        }

        public void setMaxRetries(Integer maxRetries) {
            this.maxRetries = maxRetries;
        }

        /**
         * @param lockTime 锁定时间 单位分钟 {@link Integer}
         */
        public void setLockTime(Integer lockTime) {
            this.lockTime = lockTime;
        }
    }
}
