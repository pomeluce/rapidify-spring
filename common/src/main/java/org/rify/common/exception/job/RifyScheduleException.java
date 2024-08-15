package org.rify.common.exception.job;

import org.rify.common.enums.ScheduleExceptionCode;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2024/8/9 21:12
 * @className : RifyScheduleException
 * @description : 定时任务异常
 */
public class RifyScheduleException extends Exception {
    private final ScheduleExceptionCode key;

    public RifyScheduleException(String message, ScheduleExceptionCode key) {
        super(message);
        this.key = key;
    }

    public RifyScheduleException(String message, ScheduleExceptionCode key, Exception exception) {
        super(message, exception);
        this.key = key;
    }

    public ScheduleExceptionCode getKey() {
        return key;
    }
}
