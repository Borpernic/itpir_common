package ru.lab729.itpir.ddl;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tzp", schema = "public", catalog = "itpirdb")
public class TzpEntity {
    private int id;
    private String title;
    private Integer price;
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
    @Column(name = "title", nullable = true, length = -1)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "price", nullable = true)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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
        TzpEntity tzpEntity = (TzpEntity) o;
        return id == tzpEntity.id &&
                Objects.equals(title, tzpEntity.title) &&
                Objects.equals(price, tzpEntity.price) &&
                Objects.equals(comments, tzpEntity.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, price, comments);
    }
}
