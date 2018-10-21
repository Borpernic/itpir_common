package ru.lab729.itpir.ddl;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;
import ru.lab729.itpir.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "type_BS", uniqueConstraints = {@UniqueConstraint(columnNames = "BS_Type", name = "type_BS_bsType_idx")})
public class TypeBsEntity  extends AbstractBaseEntity {

    @Basic
    @Column(name = "BS_Type", nullable = false, length = -1)
    @NotBlank
    @Size(min = 2, max = 50)
    @SafeHtml(groups = {View.Web.class})
    private String bsType;


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
