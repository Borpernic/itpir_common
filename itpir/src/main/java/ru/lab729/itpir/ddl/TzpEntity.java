package ru.lab729.itpir.ddl;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "tzp", schema = "public", catalog = "itpirdb")
public class TzpEntity {
    private int id;
    private String tzp;
    private String razmernost;
    private BigInteger price;
    private int typeOs;
    private int typeImplementer;
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
    @Column(name = "tzp", nullable = false, length = -1)
    public String getTzp() {
        return tzp;
    }

    public void setTzp(String tzp) {
        this.tzp = tzp;
    }

    @Basic
    @Column(name = "razmernost", nullable = false, length = -1)
    public String getRazmernost() {
        return razmernost;
    }

    public void setRazmernost(String razmernost) {
        this.razmernost = razmernost;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    @Basic
    @Column(name = "type_os", nullable = false)
    public int getTypeOs() {
        return typeOs;
    }

    public void setTypeOs(int typeOs) {
        this.typeOs = typeOs;
    }

    @Basic
    @Column(name = "type_implementer", nullable = false)
    public int getTypeImplementer() {
        return typeImplementer;
    }

    public void setTypeImplementer(int typeImplementer) {
        this.typeImplementer = typeImplementer;
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
                typeOs == tzpEntity.typeOs &&
                typeImplementer == tzpEntity.typeImplementer &&
                Objects.equals(tzp, tzpEntity.tzp) &&
                Objects.equals(razmernost, tzpEntity.razmernost) &&
                Objects.equals(price, tzpEntity.price) &&
                Objects.equals(comments, tzpEntity.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, tzp, razmernost, price, typeOs, typeImplementer, comments);
    }
}
