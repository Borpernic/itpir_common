package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = TypeAmsEntity.ALL_SORTED, query = "SELECT t FROM TypeAmsEntity t ORDER BY t.type ASC"),
        @NamedQuery(name = TypeAmsEntity.ALL, query = "SELECT t FROM TypeAmsEntity t ORDER BY t.id ASC"),
        @NamedQuery(name = TypeAmsEntity.DELETE, query = "DELETE FROM TypeAmsEntity t WHERE t.id=:id"),
        @NamedQuery(name = TypeAmsEntity.DELETE_ALL, query = "DELETE FROM TypeAmsEntity t"),
        @NamedQuery(name = TypeAmsEntity.GET, query = "SELECT t FROM TypeAmsEntity t WHERE t.id=:id"),
})

@Entity
@Table(name = "type_ams", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"type"}, name = "type_AMS_type_idx")})
public class TypeAmsEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "TypeAmsEntity.getAllSorted";
    public static final String ALL = "TypeAmsEntity.getAll";
    public static final String DELETE = "TypeAmsEntity.delete";
    public static final String DELETE_ALL = "TypeAmsEntity.deleteAll";
    public static final String GET = "TypeAmsEntity.get";
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typeAms")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("id DESC")
//    @JsonIgnore
    protected List<OsEntity> osEntities;
    @Basic
    @NotBlank
    @Size(min = 3, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Column(name = "type", nullable = false, length = 50, unique = true)
    private String type;

    public TypeAmsEntity() {

    }

    public TypeAmsEntity(String type) {
        this(null, type);
    }

    public TypeAmsEntity(Integer id, String type) {
        super(id);
        this.type = type;
    }

    public List<OsEntity> getOsEntities() {
        return osEntities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
