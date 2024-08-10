package org.rify.server.system.domain.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.rify.server.system.domain.enums.RifyUserStatus;

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
@Entity
@Table(name = "rify_user")
public class RifyUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RifyUserStatus getStatus() {
        return status;
    }

    public void setStatus(RifyUserStatus status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
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
