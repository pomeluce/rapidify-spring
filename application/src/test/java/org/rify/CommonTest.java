package org.rify;

import org.junit.jupiter.api.Test;
import org.rify.common.config.RifyEnvironment;
import org.rify.common.core.domain.entity.HttpResult;
import org.rify.common.core.http.HttpRequest;
import org.rify.common.utils.IpAddrUtil;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/29上午11:58
 * @className : CommonTest
 * @description : common 模块测试类
 */
@SpringBootTest
public class CommonTest {

    public @Test void ipTest() {
        System.out.println(IpAddrUtil.getMultistageReverseProxyIp("192.168.1.100, 10.0.0.1, 203.0.113.5"));
    }

    public @Test void httpTest() {
        // Map<String, String> result = HttpUtils.GET("http://whois.pconline.com.cn/ipJson.jsp", "json=true");
        HttpRequest instance = HttpRequest.instance(
                "http://jsonplaceholder.typicode.com",
                5000L,
                new HashMap<>() {
                    {
                        put("Content-Type", "application/json");
                    }
                }
        );
        /*
            HttpResult result = instance.POST("/posts", new HashMap<>() {
                {
                    put("userId", "1");
                    put("title", "foo");
                    put("body", "bar");
                }
            });
        */
        // HttpResult put = instance.PUT("/posts/1", new HashMap<>() {
        //     {
        //
        //         put("userId", 1001);
        //         put("id", 1);
        //         put("title", "title is updated");
        //         put("body", "body is null");
        //     }
        // });
        // System.out.println(put);
        HttpResult result = CompletableFuture.supplyAsync(() -> instance.GET("/posts/1")).join();
        // HttpResult result = instance.GET("/posts/1");
        System.out.println("------------->");
        String code = result.statusCode() + result.message();
        System.out.println(code);
        System.out.println(result);
        // HttpResult result = instance.DELETE("/posts/1");
    }

    public @Test void propertyTest() {
        boolean[] arr = RifyEnvironment.instance.get("spring.security.enableds", boolean[].class);
        System.out.println(Arrays.toString(arr));
    }
}
