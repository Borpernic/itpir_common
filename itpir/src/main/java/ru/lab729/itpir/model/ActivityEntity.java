package ru.lab729.itpir.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;
import ru.lab729.itpir.View;
import ru.lab729.itpir.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = ActivityEntity.ALL_SORTED, query = "SELECT a FROM ActivityEntity a LEFT JOIN FETCH a.os ORDER BY a.os.site.operator.operator, a.os.site.number ASC"),
        @NamedQuery(name = ActivityEntity.ALL, query = "SELECT a FROM ActivityEntity a ORDER BY a.id ASC"),
        @NamedQuery(name = ActivityEntity.ALL_BY_OS, query = "SELECT a FROM ActivityEntity a WHERE a.os.id=?1 ORDER BY a.id ASC"),
        @NamedQuery(name = ActivityEntity.DELETE, query = "DELETE FROM ActivityEntity a WHERE a.id=:id"),
        @NamedQuery(name = ActivityEntity.DELETE_ALL, query = "DELETE FROM ActivityEntity a"),
        @NamedQuery(name = ActivityEntity.DELETE_BY_OS_ALL, query = "DELETE FROM ActivityEntity a where a.os.id=?1"),
        @NamedQuery(name = ActivityEntity.GET, query = "SELECT a FROM ActivityEntity a WHERE a.id=:id"),
})

@Entity
@Table(name = "activity", schema = "public", catalog = "itpirdb",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"os", "type_activity", "implementer"}, name = "activity_idx")})
public class ActivityEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "ActivityEntity.getAllSorted";
    public static final String ALL = "ActivityEntity.getAll";
    public static final String ALL_BY_OS = "ActivityEntity.getAllByOsSorted";
    public static final String DELETE = "ActivityEntity.delete";
    public static final String DELETE_ALL = "ActivityEntity.deleteAll";
    public static final String DELETE_BY_OS_ALL = "ActivityEntity.deleteByOsAll";
    public static final String GET = "ActivityEntity.get";

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "activity")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("id DESC")
//    @JsonIgnore
    protected List<TaskEntity> taskEntities;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "activity")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("id DESC")
//    @JsonIgnore
    protected List<DateChangeStatusEntity> dateChangeStatusEntities;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "os", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private OsEntity os;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "implementer", nullable = false)
    @NotNull(groups = View.Persist.class)
    private ImplementerEntity implementer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_activity", nullable = false)
    @NotNull(groups = View.Persist.class)
    private TypeActivityEntity typeActivity;

    @Column(name = "date_time", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime dateTime;

    @Column(name = "plane_date_time", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime planeDateTime;

    @Column(name = "rating", nullable = false)
    @Range(min = 0, max = 100)
    @NotNull
    private BigInteger rating;

    private Boolean accept;

    @Column(name = "accept_date_time", nullable = true)
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime acceptDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_activity", nullable = false)
    @NotNull(groups = View.Persist.class)
    private StatusActivityEntity statusActivity;

    @Column(name = "date_time_change_status", nullable = true)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime dateChangeStatus;

    @Basic
    @Size(max = 100)
    @SafeHtml(groups = {View.Web.class})
    @NotBlank
    @Column(name = "comments", nullable = false, length = 150)
    private String comments;

    public ActivityEntity() {
    }

    public ActivityEntity(Integer id, OsEntity os, ImplementerEntity implementer, TypeActivityEntity typeActivity,
                          LocalDateTime dateTime, LocalDateTime planeDateTime, BigInteger rating, StatusActivityEntity statusActivity) {
        super(id);
        this.os = os;
        this.implementer = implementer;
        this.typeActivity = typeActivity;
        this.dateTime = dateTime;
        this.planeDateTime = planeDateTime;
        this.rating = rating;
        this.statusActivity = statusActivity;
    }

    public ActivityEntity(OsEntity os, ImplementerEntity implementer, TypeActivityEntity typeActivity,
                          LocalDateTime dateTime, LocalDateTime planeDateTime, BigInteger rating, StatusActivityEntity statusActivity) {
        this(null, os, implementer, typeActivity, dateTime, planeDateTime, rating, statusActivity);

    }

    public List<TaskEntity> getTaskEntities() {
        return taskEntities;
    }

    public OsEntity getOs() {
        return os;
    }

    public void setOs(OsEntity os) {
        this.os = os;
    }

    public ImplementerEntity getImplementer() {
        return implementer;
    }

    public void setImplementer(ImplementerEntity implementer) {
        this.implementer = implementer;
    }

    public TypeActivityEntity getTypeActivity() {
        return typeActivity;
    }

    public void setTypeActivity(TypeActivityEntity typeActivity) {
        this.typeActivity = typeActivity;
    }

    public LocalDateTime getDate() {
        return dateTime;
    }

    public void setDate(LocalDateTime date) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getPlaneDate() {
        return planeDateTime;
    }

    public void setPlaneDate(LocalDateTime planeplaneDateTime) {
        this.planeDateTime = planeDateTime;
    }

    public BigInteger getRating() {
        return rating;
    }

    public void setRating(BigInteger rating) {
        this.rating = rating;
    }

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public LocalDateTime getAcceptDate() {
        return acceptDateTime;
    }

    public void setAcceptDate(LocalDateTime acceptDate) {
        this.acceptDateTime = acceptDateTime;
    }

    public StatusActivityEntity getStatusActivity() {
        return statusActivity;
    }

    public void setStatusActivity(StatusActivityEntity statusActivity) {
        this.statusActivity = statusActivity;
    }

    public LocalDateTime getDateChangeStatus() {
        return dateChangeStatus;
    }

    public void setDateChangeStatus(LocalDateTime dateChangeStatus) {
        this.dateChangeStatus = dateChangeStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<DateChangeStatusEntity> getDateChangeStatusEntities() {
        return dateChangeStatusEntities;
    }
}

