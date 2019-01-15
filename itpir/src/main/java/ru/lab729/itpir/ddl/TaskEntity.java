package ru.lab729.itpir.ddl;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "task", schema = "public", catalog = "itpirdb")
public class TaskEntity {
    private int id;
    private int activity;
    private LocalDateTime dateTime;
    private int typeTask;
    private int department;
    private LocalDateTime planeDateTime;
    private Boolean rightOnTime;
    private Boolean approve;
    private LocalDateTime approveDateTime;
    private int resultTask;
    private String comments;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "activity", nullable = false)
    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    @Basic
    @Column(name = "date_time", nullable = false)
    public LocalDateTime getDate() {
        return dateTime;
    }

    public void setDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Basic
    @Column(name = "type_task", nullable = false)
    public int getTypeTask() {
        return typeTask;
    }

    public void setTypeTask(int typeTask) {
        this.typeTask = typeTask;
    }

    @Basic
    @Column(name = "department", nullable = false)
    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    @Basic
    @Column(name = "plane_date_time", nullable = false)
    public LocalDateTime getPlaneDate() {
        return planeDateTime;
    }

    public void setPlaneDate(LocalDateTime planeDateTime) {
        this.planeDateTime = planeDateTime;
    }

    @Basic
    @Column(name = "right_on_time", nullable = true)
    public Boolean getRightOnTime() {
        return rightOnTime;
    }

    public void setRightOnTime(Boolean rightOnTime) {
        this.rightOnTime = rightOnTime;
    }

    @Basic
    @Column(name = "approve", nullable = true)
    public Boolean getApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    @Basic
    @Column(name = "approve_date_time", nullable = true)
    public LocalDateTime getApproveDate() {
        return approveDateTime;
    }

    public void setApproveDate(LocalDateTime approveDate) {
        this.approveDateTime = approveDateTime;
    }

    @Basic
    @Column(name = "result_task", nullable = false)
    public int getResultTask() {
        return resultTask;
    }

    public void setResultTask(int resultTask) {
        this.resultTask = resultTask;
    }

    @Basic
    @Column(name = "comments", nullable = true, length = -1)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
