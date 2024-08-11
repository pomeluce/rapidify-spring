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
import org.rify.server.system.domain.enums.RifyUserStatus;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/2 11:23
 * @className : RifyUser
 * @description : 用户实体类
 */
@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rify_user")
public class RifyUser implements Serializable {
    private static final @Serial long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;
    private @Column(unique = true) String account;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    @JdbcType(value = PostgreSQLEnumJdbcType.class)
    private RifyUserStatus status;
    private String role;
    @Type(ListArrayType.class)
    @Column(name = "permissions", columnDefinition = "varchar array")
    private List<String> permissions;
    private String createBy;
    private @CreationTimestamp Timestamp createTime;
    private String updateBy;
    private @UpdateTimestamp Timestamp updateTime;

    public RifyUser() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RifyUser rifyUser = (RifyUser) o;
        return Objects.equals(id, rifyUser.id) && Objects.equals(account, rifyUser.account) && Objects.equals(password, rifyUser.password) && Objects.equals(email, rifyUser.email) && Objects.equals(status, rifyUser.status) && Objects.equals(role, rifyUser.role) && Objects.equals(permissions, rifyUser.permissions) && Objects.equals(createBy, rifyUser.createBy) && Objects.equals(createTime, rifyUser.createTime) && Objects.equals(updateBy, rifyUser.updateBy) && Objects.equals(updateTime, rifyUser.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, password, email, status, role, permissions, createBy, createTime, updateBy, updateTime);
    }

    @Override
    public String toString() {
        return "RifyUser{" +
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
