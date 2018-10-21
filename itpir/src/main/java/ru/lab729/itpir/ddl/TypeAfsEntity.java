package ru.lab729.itpir.ddl;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;
import ru.lab729.itpir.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "type_AFS", uniqueConstraints = {@UniqueConstraint(columnNames = "AFS_Type", name = "type_AFS_afsType_idx")})
public class TypeAfsEntity extends AbstractBaseEntity {

    @Basic
    @Column(name = "AFS_Type", nullable = false, length = -1)
    @NotBlank
    @Size(min = 2, max = 50)
    @SafeHtml(groups = {View.Web.class})
    private String afsType;


    public String getAfsType() {
        return afsType;
    }

    public void setAfsType(String afsType) {
        this.afsType = afsType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeAfsEntity that = (TypeAfsEntity) o;
        return id == that.id &&
                Objects.equals(afsType, that.afsType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, afsType);
    }
}
