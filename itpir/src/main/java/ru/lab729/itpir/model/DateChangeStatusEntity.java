package ru.lab729.itpir.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;
import ru.lab729.itpir.View;
import ru.lab729.itpir.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = DateChangeStatusEntity.ALL_SORTED, query = "SELECT d FROM DateChangeStatusEntity d ORDER BY d.activity.id ASC"),
        @NamedQuery(name = DateChangeStatusEntity.ALL, query = "SELECT d FROM DateChangeStatusEntity d ORDER BY d.id ASC"),
        @NamedQuery(name = DateChangeStatusEntity.DELETE, query = "DELETE FROM DateChangeStatusEntity d WHERE d.id=:id"),
        @NamedQuery(name = DateChangeStatusEntity.DELETE_ALL, query = "DELETE FROM DateChangeStatusEntity d"),
        @NamedQuery(name = DateChangeStatusEntity.GET, query = "SELECT d FROM DateChangeStatusEntity d WHERE d.id=:id"),
})

@Entity
@Table(name = "date_change_status", schema = "public", catalog = "itpirdb")
public class DateChangeStatusEntity extends AbstractBaseEntity {
    public static final String ALL_SORTED = "DateChangeStatusEntity.getAllSorted";
    public static final String ALL = "DateChangeStatusEntity.getAll";
    public static final String DELETE = "DateChangeStatusEntity.delete";
    public static final String DELETE_ALL = "DateChangeStatusEntity.deleteAll";
    public static final String GET = "DateChangeStatusEntity.get";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "activity", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private ActivityEntity activity;

    @Column(name = "date_time", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_activity", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private StatusActivityEntity statusActivity;

    @Basic
    @Size(max = 100)
    @SafeHtml(groups = {View.Web.class})
    @NotBlank
    @Column(name = "comments", nullable = false, length = 150)
    private String comments;


    public ActivityEntity getActivity() {
        return activity;
    }

    public void setActivity(ActivityEntity activity) {
        this.activity = activity;
    }

    public LocalDateTime getDate() {
        return dateTime;
    }

    public void setDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public StatusActivityEntity getStatusActivity() {
        return statusActivity;
    }

    public void setStatusActivity(StatusActivityEntity statusActivity) {
        this.statusActivity = statusActivity;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
