package ru.lab729.itpir.ddl;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "task", schema = "public", catalog = "itpirdb")
public class TaskEntity {
    private int id;
    private int activity;
    private Timestamp date;
    private int typeTask;
    private int department;
    private Timestamp planeDate;
    private Boolean rightOnTime;
    private Boolean approve;
    private Timestamp approveDate;
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
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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
    @Column(name = "plane_date", nullable = false)
    public Timestamp getPlaneDate() {
        return planeDate;
    }

    public void setPlaneDate(Timestamp planeDate) {
        this.planeDate = planeDate;
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
    @Column(name = "approve_date", nullable = true)
    public Timestamp getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Timestamp approveDate) {
        this.approveDate = approveDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskEntity that = (TaskEntity) o;
        return id == that.id &&
                activity == that.activity &&
                typeTask == that.typeTask &&
                department == that.department &&
                resultTask == that.resultTask &&
                Objects.equals(date, that.date) &&
                Objects.equals(planeDate, that.planeDate) &&
                Objects.equals(rightOnTime, that.rightOnTime) &&
                Objects.equals(approve, that.approve) &&
                Objects.equals(approveDate, that.approveDate) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, activity, date, typeTask, department, planeDate, rightOnTime, approve, approveDate, resultTask, comments);
    }
}
