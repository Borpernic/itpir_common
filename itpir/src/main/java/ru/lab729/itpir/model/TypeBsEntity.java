package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = TypeBsEntity.ALL_SORTED, query = "SELECT t FROM TypeBsEntity t ORDER BY t.type ASC"),
        @NamedQuery(name = TypeBsEntity.ALL, query = "SELECT t FROM TypeBsEntity t ORDER BY t.id ASC"),
        @NamedQuery(name = TypeBsEntity.DELETE, query = "DELETE FROM TypeBsEntity t WHERE t.id=:id"),
        @NamedQuery(name = TypeBsEntity.DELETE_ALL, query = "DELETE FROM TypeBsEntity t"),
        @NamedQuery(name = TypeBsEntity.GET, query = "SELECT t FROM TypeBsEntity t WHERE t.id=:id"),
})

@Entity
@Table(name = "type_bs", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"type"}, name = "type_BS_type_idx")})
public class TypeBsEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "TypeBsEntity.getAllSorted";
    public static final String ALL = "TypeBsEntity.getAll";
    public static final String DELETE = "TypeBsEntity.delete";
    public static final String DELETE_ALL = "TypeBsEntity.deleteAll";
    public static final String GET = "TypeBsEntity.get";

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typeBs")
//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("id DESC")
//    @JsonIgnore
    protected List<OsEntity> osEntities;


    public TypeBsEntity() {

    }

    public TypeBsEntity(String type) {
        this(null, type);
    }

    public TypeBsEntity(Integer id, String type) {
        super(id);
        this.type = type;
    }
}
