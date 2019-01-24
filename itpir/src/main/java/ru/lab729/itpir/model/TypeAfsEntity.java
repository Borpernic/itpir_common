package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = TypeAfsEntity.ALL_SORTED, query = "SELECT t FROM TypeAfsEntity t ORDER BY t.type ASC"),
        @NamedQuery(name = TypeAfsEntity.ALL, query = "SELECT t FROM TypeAfsEntity t ORDER BY t.id ASC"),
        @NamedQuery(name = TypeAfsEntity.DELETE, query = "DELETE FROM TypeAfsEntity t WHERE t.id=:id"),
        @NamedQuery(name = TypeAfsEntity.DELETE_ALL, query = "DELETE FROM TypeAfsEntity t"),
        @NamedQuery(name = TypeAfsEntity.GET, query = "SELECT t FROM TypeAfsEntity t WHERE t.id=:id"),
})

@Entity
@Table(name = "type_afs", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"type"}, name = "type_AFS_type_idx")})
public class TypeAfsEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "TypeAfsEntity.getAllSorted";
    public static final String ALL = "TypeAfsEntity.getAll";
    public static final String DELETE = "TypeAfsEntity.delete";
    public static final String DELETE_ALL = "TypeAfsEntity.deleteAll";
    public static final String GET = "TypeAfsEntity.get";
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typeAfs")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("id DESC")
//    @JsonIgnore
    protected List<OsEntity> osEntities;
    @Basic
    @NotBlank
    @Size(min = 3, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Column(name = "type", nullable = false, length = 50, unique = true)
    private String type;

    public TypeAfsEntity() {

    }

    public TypeAfsEntity(String type) {
        this(null, type);
    }

    public TypeAfsEntity(Integer id, String type) {
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
