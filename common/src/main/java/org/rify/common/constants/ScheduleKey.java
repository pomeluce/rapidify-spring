package org.rify.common.constants;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2024/8/9 21:09
 * @className : ScheduleKey
 * @description : 定时任务枚举
 */
public class ScheduleKey {
    /**
     * 任务名称
     */
    public static final String TASK_KEY = "TASK_KEY";
    public static final String CONTEXT_KEY = "CONTEXT_KEY";

    /* 补偿机制触发方式 */
    public enum MISFIRE {
        /**
         * 默认
         */
        DEFAULT("0"),
        /**
         * 错过的全部补偿, 然后正常触发
         */
        IGNORE_MISFIRES("1"),
        /**
         * 错过的全部合并成一次并立即触发补偿一次
         */
        FIRE_AND_PROCEED("2"),
        /**
         * 错过的不再执行
         */
        DO_NOTHING("3");

        private final String value;

        MISFIRE(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    public enum STATUS {
        /**
         * 正常
         */
        NORMAL("0"),
        /**
         * 暂停
         */
        PAUSE("1");

        private final String value;

        STATUS(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
