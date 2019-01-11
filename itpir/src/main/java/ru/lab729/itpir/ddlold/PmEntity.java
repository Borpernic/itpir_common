package ru.lab729.itpir.ddlold;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PM", schema = "public", catalog = "itpirdb")
public class PmEntity {
    private int id;
    private String pm;
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
    @Column(name = "PM", nullable = false, length = -1)
    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
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
        PmEntity pmEntity = (PmEntity) o;
        return id == pmEntity.id &&
                Objects.equals(pm, pmEntity.pm) &&
                Objects.equals(comments, pmEntity.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, pm, comments);
    }
}
