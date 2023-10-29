package org.rify.application.controller.user;

import org.rify.common.annotation.RestApiController;
import org.rify.common.core.domain.entity.HttpEntity;
import org.rify.common.core.domain.entity.RifyUser;
import org.rify.common.core.domain.model.LoginUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/10/21下午5:25
 * @className : UserController
 * @description : 用户请求控制器
 */
@RestApiController("/user")
public class UserController {

    public @GetMapping("/current") HttpEntity<RifyUser, Object> currentUser() {
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpEntity<RifyUser, Object> result = HttpEntity.instance(HttpStatus.OK.value());
        return result.put("当前登录用户", user.getUser()).put(new HashMap<>() {{
            put("role", "");
        }});
    }
}
