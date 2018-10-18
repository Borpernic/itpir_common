package ru.lab729.itpir.ddl;

import ru.lab729.itpir.model.AbstractBaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "operator")
public class OperatorEntity extends AbstractBaseEntity {

    private String name;
    private String comments;


    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        OperatorEntity that = (OperatorEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, comments);
    }
}
