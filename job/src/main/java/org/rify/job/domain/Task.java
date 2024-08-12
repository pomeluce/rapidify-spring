package org.rify.job.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2024/8/9 21:10
 * @className : Task
 * @description : 定时任务信息实体类
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Task implements Serializable {
    private static final @Serial long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String target;
    private String group;
    private String cronExpression;
    private String misfirePolicy;
    private String status;

    public Task() {
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", target='" + target + '\'' +
                ", group='" + group + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", misfirePolicy='" + misfirePolicy + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}