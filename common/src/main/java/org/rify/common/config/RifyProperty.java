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
    private Token token;
    private Cors cors;

    /**
     * 初始化 RifyConfig 对象
     */
    public @PostConstruct void init() {
        instance = this;
    }

    public static RifyProperty instance() {
        return instance;
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
}
