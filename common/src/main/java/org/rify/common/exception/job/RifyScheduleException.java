package org.rify.common.exception.job;

import org.rify.common.enums.ScheduleTaskExceptionCode;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2024/8/9 21:12
 * @className : RifyScheduleException
 * @description : 定时任务异常
 */
public class RifyScheduleException extends Exception {
    private final ScheduleTaskExceptionCode key;

    public RifyScheduleException(String message, ScheduleTaskExceptionCode key) {
        super(message);
        this.key = key;
    }

    public RifyScheduleException(String message, ScheduleTaskExceptionCode key, Exception exception) {
        super(message, exception);
        this.key = key;
    }

    public ScheduleTaskExceptionCode getKey() {
        return key;
    }
}
