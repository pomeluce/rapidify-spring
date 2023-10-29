package org.rify.application.controller.auth;

import jakarta.annotation.Resource;
import org.rify.common.annotation.RestApiController;
import org.rify.common.core.controller.BaseController;
import org.rify.common.core.domain.entity.HttpEntity;
import org.rify.common.core.domain.model.CaptchaBody;
import org.rify.common.core.domain.model.LoginBody;
import org.rify.core.web.service.RifyLoginService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Random;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/27下午11:38
 * @className : LoginController
 * @description : 登录控制器
 */
@RestApiController("/auth")
public class LoginController extends BaseController {
    private @Resource RifyLoginService service;

    public @PostMapping("/login") HttpEntity<String, Object> login(@RequestBody LoginBody loginBody) {
        String token = service.login(loginBody.account(), loginBody.password());
        HttpEntity<String, Object> result = HttpEntity.instance(HttpStatus.OK.value());
        return result.put("用户登录成功", token);
    }

    public @PostMapping("/captcha") HttpEntity<CaptchaBody, Object> captcha(@RequestBody CaptchaBody captcha) {
        int length = captcha.length();
        // 获取宽度和高度计算起始坐标
        int maxX = captcha.width() - length * 3;
        int maxY = captcha.height() - length * 3;
        Random random = new Random();
        int startX = random.nextInt(maxX + 1) + length;
        int startY = random.nextInt(maxY + 1) + length;
        int radius = length / 5;
        CaptchaBody params = new CaptchaBody(captcha.width(), captcha.height(), startX, startY, length, radius, "/src/assets/images/dragVerify1.png");
        HttpEntity<CaptchaBody, Object> result = HttpEntity.instance(HttpStatus.OK.value());
        return result.put("验证码获取成功", params);
    }

    public @GetMapping("/test") void test() {
        log.info("------------------>");
    }
}
