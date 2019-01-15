package ru.lab729.itpir.ddl;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "activity", schema = "public", catalog = "itpirdb")
public class ActivityEntity {


    private int id;
    private int os;
    private int implementer;
    private int typeActivity;
    private LocalDateTime dateTime;
    private LocalDateTime planeDateTime;
    private BigInteger rating;
    private Boolean accept;
    private LocalDateTime acceptDateTime;
    private int statusActivity;
    private LocalDateTime dateChangeStatus;
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
    @Column(name = "os", nullable = false)
    public int getOs() {
        return os;
    }

    public void setOs(int os) {
        this.os = os;
    }

    @Basic
    @Column(name = "implementer", nullable = false)
    public int getImplementer() {
        return implementer;
    }

    public void setImplementer(int implementer) {
        this.implementer = implementer;
    }

    @Basic
    @Column(name = "type_activity", nullable = false)
    public int getTypeActivity() {
        return typeActivity;
    }

    public void setTypeActivity(int typeActivity) {
        this.typeActivity = typeActivity;
    }

    @Basic
    @Column(name = "date_time", nullable = false)
    public LocalDateTime getDate() {
        return dateTime;
    }

    public void setDate(LocalDateTime date) {
        this.dateTime = dateTime;
    }

    @Basic
    @Column(name = "plane_date_time", nullable = false)
    public LocalDateTime getPlaneDate() {
        return planeDateTime;
    }

    public void setPlaneDate(LocalDateTime planeplaneDateTime) {
        this.planeDateTime = planeDateTime;
    }

    @Basic
    @Column(name = "rating", nullable = false, precision = 0)
    public BigInteger getRating() {
        return rating;
    }

    public void setRating(BigInteger rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "accept", nullable = true)
    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    @Basic
    @Column(name = "accept_date_time", nullable = true)
    public LocalDateTime getAcceptDate() {
        return acceptDateTime;
    }

    public void setAcceptDate(LocalDateTime acceptDate) {
        this.acceptDateTime = acceptDateTime;
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
    @Column(name = "date_time_change_status", nullable = true)
    public LocalDateTime getDateChangeStatus() {
        return dateChangeStatus;
    }

    public void setDateChangeStatus(LocalDateTime dateChangeStatus) {
        this.dateChangeStatus = dateChangeStatus;
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
