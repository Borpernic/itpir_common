package ru.lab729.itpir.ddlold;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;
import ru.lab729.itpir.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "executor", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "executor_name_idx")})
public class ExecutorEntity extends AbstractBaseEntity {

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    @NotBlank
    @Size(min = 2, max = 120)
    @SafeHtml(groups = {View.Web.class})
    private String name;

    @Basic
    @Column(name = "phone", nullable = true, length = -1)
    @Size(min = 2, max = 120)
    @SafeHtml(groups = {View.Web.class})
    private String phone;

    @Basic
    @Column(name = "email", nullable = true, length = -1)
    @Email
    @Size(min = 2, max = 120)
    @SafeHtml(groups = {View.Web.class})
    private String email;

    @Basic
    @Column(name = "comments", nullable = true, length = -1)
    @Size(min = 2, max = 120)
    @SafeHtml(groups = {View.Web.class})
    private String comments;

    public ExecutorEntity() {
    }

    @Basic
    @Column(name = "rating", nullable = true)
    private Integer rating;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private StatusExecutorEntity status;

    public StatusExecutorEntity getStatus() {
        return status;
    }

    public void setStatus(StatusExecutorEntity status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExecutorEntity that = (ExecutorEntity) o;
        return id == that.id &&
                //status == that.status &&
                Objects.equals(name, that.name) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(comments, that.comments) &&
                Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, phone, email, comments, rating /*, statusId*/);
    }
}
