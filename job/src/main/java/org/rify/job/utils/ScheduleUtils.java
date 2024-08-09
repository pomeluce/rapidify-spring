package org.rify.job.utils;

import org.rify.job.RifyJobExecution;
import org.rify.job.domain.ScheduleContext;
import org.quartz.*;
import org.rify.common.constants.ScheduleKey;
import org.rify.common.enums.ScheduleTaskExceptionCode;
import org.rify.common.exception.job.RifyScheduleException;
import org.rify.common.utils.StringUtils;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2024/8/9 21:08
 * @className : ScheduleUtils
 * @description : 任务调度器工具类
 */
public class ScheduleUtils {
    public static JobKey getJobKey(Long id, String group) {
        return JobKey.jobKey(StringUtils.format("{}_{}", ScheduleKey.TASK_KEY, id), group);
    }

    public static TriggerKey getTriggerKey(Long id, String group) {
        return TriggerKey.triggerKey(StringUtils.format("{}_{}", ScheduleKey.TASK_KEY, id), group);
    }

    /**
     * 创建任务
     *
     * @param scheduler 调度器 {@link  Scheduler}
     * @param context   任务信息 {@link  ScheduleContext}
     * @throws SchedulerException 抛出一个 {@link SchedulerException} 类型的异常
     */
    public static void create(Scheduler scheduler, ScheduleContext context) throws SchedulerException, RifyScheduleException {
        Long id = context.getId();
        String group = context.getGroup();
        JobKey jobKey = getJobKey(id, group);

        // 任务详情
        JobDetail detail = JobBuilder.newJob(RifyJobExecution.class).storeDurably().withIdentity(jobKey).build();

        // 表达式调度构建器
        CronScheduleBuilder cronScheduleBuilder = withMisfireHandling(CronScheduleBuilder.cronSchedule(context.getCronExpression()), context);

        // 使用 cron 表达式生成 trigger 对象
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(id, group)).withSchedule(cronScheduleBuilder).build();

        // 保存任务信息
        detail.getJobDataMap().put(ScheduleKey.CONTEXT_KEY, context);

        // 判断任务是否存在
        if (scheduler.checkExists(jobKey)) scheduler.deleteJob(jobKey);

        // 创建任务
        scheduler.scheduleJob(detail, trigger);

        // 判断是否暂停任务
        if (context.getStatus().equals(ScheduleKey.STATUS.PAUSE.value())) scheduler.pauseJob(jobKey);
    }

    /**
     * 暂停任务
     *
     * @param scheduler 调度器 {@link  Scheduler}
     * @param name      任务名称 {@link  ScheduleContext#getName()}
     */
    public static void pause(Scheduler scheduler, String name) {
    }

    /**
     * 恢复任务
     *
     * @param scheduler 调度器 {@link  Scheduler}
     * @param name      任务名称 {@link  ScheduleContext#getName()}
     */
    public static void resume(Scheduler scheduler, String name) {
    }

    /**
     * 删除任务
     *
     * @param scheduler 调度器 {@link  Scheduler}
     * @param name      任务名称 {@link  ScheduleContext#getName()}
     */
    public static void delete(Scheduler scheduler, String name) {
    }

    /**
     * 立即执行一次任务
     *
     * @param scheduler 调度器 {@link  Scheduler}
     * @param name      任务名称 {@link  ScheduleContext#getName()}
     */
    public static void once(Scheduler scheduler, String name) {
    }

    /**
     * 修改任务
     *
     * @param scheduler 调度器 {@link  Scheduler}
     * @param context   任务信息 {@link  ScheduleContext}
     */
    public static void modify(Scheduler scheduler, ScheduleContext context) {
    }

    public static CronScheduleBuilder withMisfireHandling(CronScheduleBuilder builder, ScheduleContext context) throws RifyScheduleException {
        try {
            return switch (ScheduleKey.MISFIRE.valueOf(context.getMisfirePolicy())) {
                case ScheduleKey.MISFIRE.DEFAULT -> builder;
                case ScheduleKey.MISFIRE.IGNORE_MISFIRES -> builder.withMisfireHandlingInstructionIgnoreMisfires();
                case ScheduleKey.MISFIRE.FIRE_AND_PROCEED -> builder.withMisfireHandlingInstructionFireAndProceed();
                case ScheduleKey.MISFIRE.DO_NOTHING -> builder.withMisfireHandlingInstructionDoNothing();
            };
        } catch (IllegalArgumentException e) {
            throw new RifyScheduleException(StringUtils.format("the schedule misfire policy '{}' cannot be used in cron schedule build", context.getMisfirePolicy()), ScheduleTaskExceptionCode.MISFIRE_ERROR, e);
        }
    }
}
