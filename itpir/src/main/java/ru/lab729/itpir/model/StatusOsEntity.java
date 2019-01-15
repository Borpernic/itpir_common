package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@NamedQueries({
        @NamedQuery(name = StatusOsEntity.ALL_SORTED, query = "SELECT s FROM StatusOsEntity s ORDER BY s.status ASC"),
        @NamedQuery(name = StatusOsEntity.ALL, query = "SELECT s FROM StatusOsEntity s ORDER BY s.id ASC"),
        @NamedQuery(name = StatusOsEntity.DELETE, query = "DELETE FROM StatusOsEntity s WHERE s.id=:id"),
        @NamedQuery(name = StatusOsEntity.DELETE_ALL, query = "DELETE FROM StatusOsEntity s"),
        @NamedQuery(name = StatusOsEntity.GET, query = "SELECT s FROM StatusOsEntity s WHERE s.id=:id"),
})

@Entity
@Table(name = "status_os", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"status"}, name = "status_os_status_idx")})
public class StatusOsEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "StatusOsEntity.getAllSorted";
    public static final String ALL = "StatusOsEntity.getAll";
    public static final String DELETE = "StatusOsEntity.delete";
    public static final String DELETE_ALL = "StatusOsEntity.deleteAll";
    public static final String GET = "StatusOsEntity.get";

    @Basic
    @NotBlank
    @Size(min = 3, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Column(name = "status", nullable = false, length = 50, unique = true)
    private String status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "statusOs")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("id DESC")
//    @JsonIgnore
    protected List<OsEntity> osEntities;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StatusOsEntity() {

    }

    public StatusOsEntity(String status) {
        this(null, status);
    }

    public StatusOsEntity(Integer id, String status) {
        super(id);
        this.status = status;
    }
}
