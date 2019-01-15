package ru.lab729.itpir.ddl;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "date_change_status", schema = "public", catalog = "itpirdb")
public class DateChangeStatusEntity {
    private int id;
    private int activity;
    private LocalDateTime dateTime;
    private int statusActivity;
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
    @Column(name = "status_activity", nullable = false)
    public int getStatusActivity() {
        return statusActivity;
    }

    public void setStatusActivity(int statusActivity) {
        this.statusActivity = statusActivity;
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
