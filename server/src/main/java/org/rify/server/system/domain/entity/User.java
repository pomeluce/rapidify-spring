package org.rify.server.system.domain.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.rify.server.system.domain.enums.UserStatus;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/2 11:23
 * @className : User
 * @description : 用户实体类
 */
@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rify_user")
public class User implements Serializable {
    private static final @Serial long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;
    private @Column(unique = true) String account;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    @JdbcType(value = PostgreSQLEnumJdbcType.class)
    private UserStatus status;
    private String role;
    @Type(ListArrayType.class)
    @Column(name = "permissions", columnDefinition = "varchar array")
    private List<String> permissions;
    private String createBy;
    private @CreationTimestamp Timestamp createTime;
    private String updateBy;
    private @UpdateTimestamp Timestamp updateTime;

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(account, user.account) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(status, user.status) && Objects.equals(role, user.role) && Objects.equals(permissions, user.permissions) && Objects.equals(createBy, user.createBy) && Objects.equals(createTime, user.createTime) && Objects.equals(updateBy, user.updateBy) && Objects.equals(updateTime, user.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, password, email, status, role, permissions, createBy, createTime, updateBy, updateTime);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", role='" + role + '\'' +
                ", permissions=" + permissions +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
