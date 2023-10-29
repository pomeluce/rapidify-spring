package org.rify.common.core.domain.model;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/10/21下午4:43
 * @className : CaptchaBody
 * @description : 滑块验证码请求参数实体
 */
public record CaptchaBody(int width, int height, int startX, int startY, int length, int radius, String src) {
}
