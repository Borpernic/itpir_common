package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;


@NamedQueries({
        @NamedQuery(name = TypeOsEntity.ALL_SORTED, query = "SELECT t FROM TypeOsEntity t ORDER BY t.type ASC"),
        @NamedQuery(name = TypeOsEntity.ALL, query = "SELECT t FROM TypeOsEntity t ORDER BY t.id ASC"),
        @NamedQuery(name = TypeOsEntity.DELETE, query = "DELETE FROM TypeOsEntity t WHERE t.id=:id"),
        @NamedQuery(name = TypeOsEntity.DELETE_ALL, query = "DELETE FROM TypeOsEntity t"),
        @NamedQuery(name = TypeOsEntity.GET, query = "SELECT t FROM TypeOsEntity t WHERE t.id=:id"),
})

@Entity
@Table(name = "type_os", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"type"}, name = "type_os_type_idx")})
public class TypeOsEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "TypeOsEntity.getAllSorted";
    public static final String ALL = "TypeOsEntity.getAll";
    public static final String DELETE = "TypeOsEntity.delete";
    public static final String DELETE_ALL = "TypeOsEntity.deleteAll";
    public static final String GET = "TypeOsEntity.get";

    @Basic
    @NotBlank
    @Size(min = 3, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Column(name = "type", nullable = false, length = 50, unique = true)
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TypeOsEntity() {

    }

    public TypeOsEntity(String type) {
        this(null, type);
    }

    public TypeOsEntity(Integer id, String type) {
        super(id);
        this.type = type;
    }
}
