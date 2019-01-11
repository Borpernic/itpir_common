package ru.lab729.itpir.ddl;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "date_change_status", schema = "public", catalog = "itpirdb")
public class DateChangeStatusEntity {
    private int id;
    private int activity;
    private Timestamp date;
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
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateChangeStatusEntity that = (DateChangeStatusEntity) o;
        return id == that.id &&
                activity == that.activity &&
                statusActivity == that.statusActivity &&
                Objects.equals(date, that.date) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, activity, date, statusActivity, comments);
    }
}
