package ru.lab729.itpir.ddl;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "band", schema = "public", catalog = "itpirdb")
public class BandEntity {
    private int id;
    private String band;
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
    @Column(name = "band", nullable = false, length = -1)
    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
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
        BandEntity that = (BandEntity) o;
        return id == that.id &&
                Objects.equals(band, that.band) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, band, comments);
    }
}
