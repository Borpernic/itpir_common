package ru.lab729.itpir.ddl;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;
import ru.lab729.itpir.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "type_AMS", uniqueConstraints = {@UniqueConstraint(columnNames = "AMS_Type", name = "type_AMS_amsType_idx")})
public class TypeAmsEntity extends AbstractBaseEntity {

    @Basic
    @Column(name = "AMS_Type", nullable = false, length = -1)
    @NotBlank
    @Size(min = 2, max = 50)
    @SafeHtml(groups = {View.Web.class})
    private String amsType;


    public String getAmsType() {
        return amsType;
    }

    public void setAmsType(String amsType) {
        this.amsType = amsType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeAmsEntity that = (TypeAmsEntity) o;
        return id == that.id &&
                Objects.equals(amsType, that.amsType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, amsType);
    }
}
