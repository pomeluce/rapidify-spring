package org.rify.job.controller;

import org.rify.job.domain.ScheduleContext;
import org.rify.common.annotation.RestApiController;
import org.rify.common.core.controller.BaseController;
import org.rify.common.core.domain.HttpEntity;
import org.rify.common.utils.CronUtils;
import org.rify.common.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2024/8/9 21:19
 * @className : ScheduleTaskController
 * @description : 定时任务控制器
 */
@RestApiController("/schedule")
public class ScheduleTaskController  extends BaseController {

    public @PostMapping("/add") HttpEntity<String, Object> create(@RequestBody ScheduleContext context) {
        HttpEntity<String, Object> result = HttpEntity.instance(HttpStatus.OK.value());
        // 判断 cron 表达式是否有效
        if (CronUtils.isValidExpression(context.getCronExpression())) {
            return error(StringUtils.format("添加定时任务 '{}' 失败, cron 表达式不正确", context.getName()));
        }
        return null;
    }
}
