package org.rify.job.domain;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2024/8/9 21:10
 * @className : ScheduleContext
 * @description : 定时任务信息实体类
 */
public class ScheduleContext implements Serializable {
    private static final @Serial long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String target;
    private String group;
    private String cronExpression;
    private String misfirePolicy;
    private String status;

    public ScheduleContext() {
    }

    public ScheduleContext(String cronExpression, String group, Long id, String misfirePolicy, String name, String status, String target) {
        this.cronExpression = cronExpression;
        this.group = group;
        this.id = id;
        this.misfirePolicy = misfirePolicy;
        this.name = name;
        this.status = status;
        this.target = target;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMisfirePolicy() {
        return misfirePolicy;
    }

    public void setMisfirePolicy(String misfirePolicy) {
        this.misfirePolicy = misfirePolicy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "ScheduleContext{" +
                "cronExpression='" + cronExpression + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", target='" + target + '\'' +
                ", group='" + group + '\'' +
                ", misfirePolicy='" + misfirePolicy + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}