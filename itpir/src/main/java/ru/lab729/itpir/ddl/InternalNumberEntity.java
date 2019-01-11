package ru.lab729.itpir.ddl;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "internal_number", schema = "public", catalog = "itpirdb")
public class InternalNumberEntity {
    private int id;
    private int project;
    private String number;
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
    @Column(name = "project", nullable = false)
    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    @Basic
    @Column(name = "number", nullable = false, length = -1)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
        InternalNumberEntity that = (InternalNumberEntity) o;
        return id == that.id &&
                project == that.project &&
                Objects.equals(number, that.number) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, project, number, comments);
    }
}
