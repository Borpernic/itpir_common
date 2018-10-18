package ru.lab729.itpir.ddl;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "type_development", schema = "public", catalog = "itpirdb")
public class TypeDevelopmentEntity {
    private int id;
    private String developmentType;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "development_type", nullable = false, length = -1)
    public String getDevelopmentType() {
        return developmentType;
    }

    public void setDevelopmentType(String developmentType) {
        this.developmentType = developmentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeDevelopmentEntity that = (TypeDevelopmentEntity) o;
        return id == that.id &&
                Objects.equals(developmentType, that.developmentType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, developmentType);
    }
}
