package org.rify.core.web.service;

import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.Resource;
import org.rify.common.config.RifyConfig;
import org.rify.common.core.domain.model.LoginUser;
import org.rify.common.core.redis.RedisClient;
import org.rify.common.enums.CacheKey;
import org.rify.common.utils.GenIdUtils;
import org.rify.common.utils.IpAddrUtils;
import org.rify.common.utils.LocationUtils;
import org.rify.common.utils.ServletUtils;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/28下午5:22
 * @className : RifyTokenService
 * @description : Token 服务处理
 */
@Service
public class RifyTokenService {
    private @Resource RifyConfig rifyConfig;
    private @Resource RedisClient redisClient;
    private final long MILLIS = 1000L;

    /**
     * 创建 token 令牌
     *
     * @param claims 自定义属性 {@link Map}
     * @return 返回一个 string 类型的 token 令牌
     */
    public String createToken(Map<String, Object> claims) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                // 设置唯一编号
                .setId(GenIdUtils.timestamp().toString())
                // 设置主题
                .setSubject("rify-create-token")
                // 签发日期
                .setIssuedAt(new Date(currentTimeMillis))
                // 设置签发者
                .setIssuer(rifyConfig.getIssuer())
                // 设置过期时间
                .setExpiration(new Date(currentTimeMillis + rifyConfig.getExpireTime() * MILLIS))
                // 自定义属性
                .addClaims(claims)
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(generalKey(), SignatureAlgorithm.HS256).compact();
    }

    /**
     * 创建 token 令牌
     *
     * @param user 用户信息 {@link LoginUser}
     * @return 返回一个 string 类型的 token 令牌
     */
    public String accessToken(LoginUser user) {
        // 获取 token 唯一标识
        String uid = GenIdUtils.randomUUID();
        user.setUid(uid);
        // 设置用户代理信息
        setUserAgent(user);

        Map<String, Object> claims = new HashMap<>();
        // 设置 claims 信息
        claims.put(CacheKey.ACCESS_TOKEN_KEY.value(), uid);
        // 获取 token 令牌
        String token = createToken(claims);
        // 设置过期时间
        setExpiredTime(token, System.currentTimeMillis() + rifyConfig.getExpireTime() * MILLIS);
        // 设置刷新时间
        setRefreshTime(token, System.currentTimeMillis() + rifyConfig.getRefreshExpireTime() * MILLIS);
        // 返回 token 信息
        return token;
    }

    /**
     * 设置 token 的过期时间
     *
     * @param token       token 信息 {@link String}
     * @param expiredTime 过期时间 {@link  Long}
     */
    public void setExpiredTime(String token, Long expiredTime) {
        redisClient.hset(token, "expiredTime", expiredTime);
    }

    /**
     * 设置 token 的刷新时间
     *
     * @param token       token 信息 {@link String}
     * @param refreshTime 刷新时间 {@link  Long}
     */
    public void setRefreshTime(String token, Long refreshTime) {
        redisClient.hset(token, "refreshTime", refreshTime);
    }

    /**
     * 设置用户代理信息
     *
     * @param user 登录用户信息 {@link LoginUser}
     */
    public void setUserAgent(LoginUser user) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpAddrUtils.getIpAddress(ServletUtils.getRequest());
        user.setIp(ip);
        user.setLocation(LocationUtils.getRelativeLocation(ip));
        user.setBrowser(userAgent.getBrowser().getName() + " " + userAgent.getBrowserVersion());
        user.setOs(userAgent.getOperatingSystem().getName());
    }

    /**
     * 由字符串生成加密 key
     *
     * @return SecretKey
     */
    public SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(rifyConfig.getTokenEncryptKey().getBytes());
        /*
        使用 len 的第一个 len 字节构造来自给定字节数组的 key, 从 offset 开始。
        构成密钥的字节是 key[offset] 和 key[offset + len - 1] 之间的字节
        参数
            key - 密钥的密钥材料, 将复制以 offset 开头的数组的第一个 len 字节, 以防止后续修改
            offset - 密钥材料开始的 key 中的偏移量
            len - 密钥材料的长度
            algorithm - 与给定密钥材料关联的密钥算法的名称, AES 是一种对称加密算法
         */
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256");
    }
}
