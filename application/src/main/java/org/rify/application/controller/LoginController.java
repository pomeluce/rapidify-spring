package org.rify.application.controller;

import jakarta.annotation.Resource;
import org.rify.common.core.controller.BaseController;
import org.rify.common.core.domain.entity.HttpEntity;
import org.rify.common.core.domain.model.RifyUser;
import org.rify.core.web.service.RifyLoginService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/27下午11:38
 * @className : LoginController
 * @description : 登录控制器
 */
@RestController
public class LoginController extends BaseController {
    private @Resource RifyLoginService service;

    public @PostMapping("/login") HttpEntity<String, Object> login(@RequestBody RifyUser user) {
        String token = service.login(user);
        HttpEntity<String, Object> result = HttpEntity.instance(HttpStatus.OK.value());
        return result.put("登录成功", token);
    }
}
