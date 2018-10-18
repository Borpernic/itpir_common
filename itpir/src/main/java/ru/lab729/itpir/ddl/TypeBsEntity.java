package ru.lab729.itpir.ddl;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "type_BS", schema = "public", catalog = "itpirdb")
public class TypeBsEntity {
    private int id;
    private String bsType;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "BS_type", nullable = false, length = -1)
    public String getBsType() {
        return bsType;
    }

    public void setBsType(String bsType) {
        this.bsType = bsType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeBsEntity that = (TypeBsEntity) o;
        return id == that.id &&
                Objects.equals(bsType, that.bsType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, bsType);
    }
}
