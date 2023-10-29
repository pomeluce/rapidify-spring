package org.rify.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/9/27下午11:23
 * @className : RifyUser
 * @description : 用户实体类
 */
@TableName(value = "rify_user")
public class RifyUser implements Serializable {
    private @TableId Integer id;
    private String account;
    private String password;
    private Boolean status;
    private Integer age;
    private String email;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;

    public RifyUser() {
    }

    public RifyUser(Integer id, String account, String password, Boolean status, Integer age, String email, String createBy, LocalDateTime createTime, String updateBy, LocalDateTime updateTime) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.status = status;
        this.age = age;
        this.email = email;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RifyUser rifyUser)) return false;

        if (!Objects.equals(id, rifyUser.id)) return false;
        if (!Objects.equals(account, rifyUser.account)) return false;
        if (!Objects.equals(password, rifyUser.password)) return false;
        if (!Objects.equals(status, rifyUser.status)) return false;
        if (!Objects.equals(age, rifyUser.age)) return false;
        if (!Objects.equals(email, rifyUser.email)) return false;
        if (!Objects.equals(createBy, rifyUser.createBy)) return false;
        if (!Objects.equals(createTime, rifyUser.createTime)) return false;
        if (!Objects.equals(updateBy, rifyUser.updateBy)) return false;
        return Objects.equals(updateTime, rifyUser.updateTime);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RifyUser{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", creatBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
