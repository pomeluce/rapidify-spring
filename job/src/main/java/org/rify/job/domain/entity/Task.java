package org.rify.job.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.rify.common.core.domain.BaseEntity;
import org.rify.job.domain.enums.TaskMisfire;
import org.rify.job.domain.enums.TaskStatus;

import java.io.Serial;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2024/8/9 21:10
 * @className : Task
 * @description : 定时任务信息实体类
 */
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "rify_task")
public class Task extends BaseEntity {
    private static final @Serial long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;
    private String name;
    private String target;
    private String groupId;
    private String cronExpression;
    @JdbcType(value = PostgreSQLEnumJdbcType.class)
    private @Enumerated(EnumType.STRING) TaskMisfire misfirePolicy;
    @JdbcType(value = PostgreSQLEnumJdbcType.class)
    private @Enumerated(EnumType.STRING) TaskStatus status;

    public Task() {
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", target='" + target + '\'' +
                ", groupId='" + groupId + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", misfirePolicy=" + misfirePolicy +
                ", status=" + status +
                "} " + super.toString();
    }
}