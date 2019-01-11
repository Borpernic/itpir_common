package ru.lab729.itpir.ddl;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "activity", schema = "public", catalog = "itpirdb")
public class ActivityEntity {


    private int id;
    private int os;
    private int implementer;
    private int typeActivity;
    private Timestamp date;
    private Timestamp planeDate;
    private BigInteger rating;
    private Boolean accept;
    private Timestamp acceptDate;
    private int statusActivity;
    private Timestamp dateChangeStatus;
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
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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
    @Column(name = "accept_date", nullable = true)
    public Timestamp getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Timestamp acceptDate) {
        this.acceptDate = acceptDate;
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
    @Column(name = "date_change_status", nullable = true)
    public Timestamp getDateChangeStatus() {
        return dateChangeStatus;
    }

    public void setDateChangeStatus(Timestamp dateChangeStatus) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityEntity that = (ActivityEntity) o;
        return id == that.id &&
                os == that.os &&
                implementer == that.implementer &&
                typeActivity == that.typeActivity &&
                statusActivity == that.statusActivity &&
                Objects.equals(date, that.date) &&
                Objects.equals(planeDate, that.planeDate) &&
                Objects.equals(rating, that.rating) &&
                Objects.equals(accept, that.accept) &&
                Objects.equals(acceptDate, that.acceptDate) &&
                Objects.equals(dateChangeStatus, that.dateChangeStatus) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, os, implementer, typeActivity, date, planeDate, rating, accept, acceptDate, statusActivity, dateChangeStatus, comments);
    }
}
