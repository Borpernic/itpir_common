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
        @NamedQuery(name = TaskEntity.ALL_SORTED, query = "SELECT t FROM TaskEntity t ORDER BY t.activity.id, t.dateTime DESC"),
        @NamedQuery(name = TaskEntity.ALL, query = "SELECT t FROM TaskEntity t ORDER BY t.id ASC"),
        @NamedQuery(name = TaskEntity.ALL_BY_ACTIVITY_SORTED, query = "SELECT t FROM TaskEntity t WHERE t.activity.id=?1 ORDER BY t.dateTime DESC"),
        @NamedQuery(name = TaskEntity.DELETE, query = "DELETE FROM TaskEntity t WHERE t.id=:id"),
        @NamedQuery(name = TaskEntity.DELETE_ALL, query = "DELETE FROM TaskEntity t"),
        @NamedQuery(name = TaskEntity.DELETE_BY_ACTIVITY_ALL, query = "DELETE FROM TaskEntity t where t.activity.id=?1"),
        @NamedQuery(name = TaskEntity.GET, query = "SELECT t FROM TaskEntity t WHERE t.id=:id"),
})

@Entity
@Table(name = "task", schema = "public", catalog = "itpirdb"/*, uniqueConstraints = {@UniqueConstraint(columnNames = {"activity", "type_task", "department"}, name = "task_activity_type_task_department_idx")}*/)
public class TaskEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "TaskEntity.getAllSorted";
    public static final String ALL = "TaskEntity.getAll";
    public static final String ALL_BY_ACTIVITY_SORTED = "TaskEntity.getAllByactivitySorted";
    public static final String DELETE = "TaskEntity.delete";
    public static final String DELETE_ALL = "TaskEntity.deleteAll";
    public static final String DELETE_BY_ACTIVITY_ALL = "TaskEntity.deleteByOsAll";
    public static final String GET = "TaskEntity.get";

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
    @JoinColumn(name = "type_task", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private TypeTaskEntity typeTask;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private DepartmentEntity department;

    @Column(name = "plane_date_time", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime planeDateTime;

    @Basic
    @Column(name = "right_on_time", nullable = true)
    private Boolean rightOnTime;

    @Basic
    @Column(name = "approve", nullable = true)
    private Boolean approve;

    @Column(name = "approve_date_time", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime approveDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "result_task", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private ResultTaskEntity resultTask;

    @Basic
    @Size(max = 100)
    @SafeHtml(groups = {View.Web.class})
    @NotBlank
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

    public TypeTaskEntity getTypeTask() {
        return typeTask;
    }

    public void setTypeTask(TypeTaskEntity typeTask) {
        this.typeTask = typeTask;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public LocalDateTime getPlaneDate() {
        return planeDateTime;
    }

    public void setPlaneDate(LocalDateTime planeDateTime) {
        this.planeDateTime = planeDateTime;
    }

    public Boolean getRightOnTime() {
        return rightOnTime;
    }

    public void setRightOnTime(Boolean rightOnTime) {
        this.rightOnTime = rightOnTime;
    }

    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    public LocalDateTime getApproveDate() {
        return approveDateTime;
    }

    public void setApproveDate(LocalDateTime approveDate) {
        this.approveDateTime = approveDateTime;
    }

    public ResultTaskEntity getResultTask() {
        return resultTask;
    }

    public void setResultTask(ResultTaskEntity resultTask) {
        this.resultTask = resultTask;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
