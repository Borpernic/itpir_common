package ru.lab729.itpir.ddl;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;
import ru.lab729.itpir.View;
import ru.lab729.itpir.model.AbstractBaseEntity;
import ru.lab729.itpir.util.DateTimeUtil;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity

@Table(name = "activity", uniqueConstraints = {@UniqueConstraint(columnNames = {"os_id", "activity_type_id"}, name = "meals_unique_user_datetime_idx")})
public class ActivityEntity extends AbstractBaseEntity {


    @Column(name = "section_id", nullable = false)
    private Integer sectionId;

    @Column(name = "os_id", nullable = false)
    private Integer osId;

    @Column(name = "executor_id", nullable = false)
    private Integer executorId;

    @Column(name = "date", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime date = LocalDateTime.now();

    @Basic
    @Column(name = "plane_date", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime planeDate;

    @Basic
    @Column(name = "activity_type_id", nullable = false)
    private Integer activityTypeId;

    @Basic
    @Column(name = "activity_status_id", nullable = false)
    private Integer activityStatusId;

    @Basic
    @Column(name = "comments", nullable = true, length = -1)
    @SafeHtml(groups = {View.Web.class})
    private String comments;

/*    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }*/


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityEntity that = (ActivityEntity) o;
        return id == that.id &&
                Objects.equals(osId, that.osId) &&
                Objects.equals(executorId, that.executorId) &&
                Objects.equals(date, that.date) &&
                Objects.equals(planeDate, that.planeDate) &&
                Objects.equals(activityTypeId, that.activityTypeId) &&
                Objects.equals(activityStatusId, that.activityStatusId) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, osId, executorId, date, planeDate, activityTypeId, activityStatusId, comments);
    }
}
