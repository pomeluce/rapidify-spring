package org.rify.common.core.domain.model;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/10/22下午1:14
 * @className : LoginBody
 * @description : 登录请求参数实体
 */
public record LoginBody(String uid, String account, String password, String code) {
}
