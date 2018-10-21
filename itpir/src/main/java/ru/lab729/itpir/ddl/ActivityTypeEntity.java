package ru.lab729.itpir.ddl;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;
import ru.lab729.itpir.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "activity_type",  uniqueConstraints = {@UniqueConstraint(columnNames = {"activity"}, name = "activity_type_activity_idx")})
public class ActivityTypeEntity extends AbstractBaseEntity {


    @Basic
    @Column(name = "activity", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    @SafeHtml(groups = {View.Web.class})
    private String activity;

    @Basic
    @Column(name = "comments", nullable = false)
    @Size(min = 2, max = 120)
    @SafeHtml(groups = {View.Web.class})
    private String comments;


    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }


    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityTypeEntity that = (ActivityTypeEntity) o;
        return id == that.id &&
                Objects.equals(activity, that.activity) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, activity, comments);
    }
}
