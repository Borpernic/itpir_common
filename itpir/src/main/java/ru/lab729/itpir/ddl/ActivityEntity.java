package ru.lab729.itpir.ddl;

import ru.lab729.itpir.model.AbstractBaseEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
//@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "meals_unique_user_datetime_idx")})
@Table(name = "activity", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "meals_unique_user_datetime_idx")})
public class ActivityEntity extends AbstractBaseEntity {
    //private int id;

    @Basic
    @Column(name = "os_id", nullable = true)
    private Integer osId;

    @Basic
    @Column(name = "implementer_id", nullable = true)
    private Integer implementerId;

    @Basic
    @Column(name = "date", nullable = true)
    private Date date;

    @Basic
    @Column(name = "plane_date", nullable = true)
    private Date planeDate;

    @Basic
    @Column(name = "activity_type_id", nullable = true)
    private Integer activityTypeId;

    @Basic
    @Column(name = "activity_status_id", nullable = true)
    private Integer activityStatusId;

    @Basic
    @Column(name = "comments", nullable = true, length = -1)
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
                Objects.equals(implementerId, that.implementerId) &&
                Objects.equals(date, that.date) &&
                Objects.equals(planeDate, that.planeDate) &&
                Objects.equals(activityTypeId, that.activityTypeId) &&
                Objects.equals(activityStatusId, that.activityStatusId) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, osId, implementerId, date, planeDate, activityTypeId, activityStatusId, comments);
    }
}
