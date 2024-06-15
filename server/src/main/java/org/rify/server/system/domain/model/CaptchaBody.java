package org.rify.server.system.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/10/21下午4:43
 * @className : CaptchaBody
 * @description : 滑块验证码请求参数实体
 */
@Schema(description = "滑块验证码请求参数")
public record CaptchaBody(
        @Schema(description = "背景宽度", defaultValue = "300") int width,
        @Schema(description = "背景高度", defaultValue = "200") int height,
        @Schema(description = "起始 x 坐标") int startX,
        @Schema(description = "起始 y 坐标") int startY,
        @Schema(description = "滑块长度", defaultValue = "32") int length,
        @Schema(description = "滑块圆角半径") int radius,
        @Schema(description = "滑块图片") String src
) {
}
