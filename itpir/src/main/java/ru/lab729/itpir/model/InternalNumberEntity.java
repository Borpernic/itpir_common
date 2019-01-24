package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = InternalNumberEntity.ALL_SORTED, query = "SELECT i FROM InternalNumberEntity i ORDER BY i.number ASC"),
        @NamedQuery(name = InternalNumberEntity.ALL, query = "SELECT i FROM InternalNumberEntity i ORDER BY i.id ASC"),
        @NamedQuery(name = InternalNumberEntity.ALL_PROJECT_SORTED, query = "SELECT i FROM InternalNumberEntity i JOIN FETCH ProjectEntity p WHERE p.id=?1 ORDER BY i.number ASC"),
        @NamedQuery(name = InternalNumberEntity.DELETE, query = "DELETE FROM InternalNumberEntity i WHERE i.id=:id"),
        @NamedQuery(name = InternalNumberEntity.DELETE_ALL, query = "DELETE FROM InternalNumberEntity i"),
        @NamedQuery(name = InternalNumberEntity.DELETE_ALL_BY_PROJECT, query = "DELETE FROM InternalNumberEntity i WHERE i.project.id=?1"),
        @NamedQuery(name = InternalNumberEntity.GET, query = "SELECT i FROM InternalNumberEntity i WHERE i.id=:id"),
})

@Entity
@Table(name = "internal_number", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"number"}, name = "internal_number_number_idx")})
public class InternalNumberEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "InternalNumberEntity.getAllSorted";
    public static final String ALL = "InternalNumberEntity.getAll";
    public static final String ALL_PROJECT_SORTED = "InternalNumberEntity.getAllProjectSorted";
    public static final String DELETE = "InternalNumberEntity.delete";
    public static final String DELETE_ALL = "InternalNumberEntity.deleteAll";
    public static final String DELETE_ALL_BY_PROJECT = "InternalNumberEntity.deleteAllByProject";
    public static final String GET = "InternalNumberEntity.get";
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "internalNumber")
//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("id DESC")
//    @JsonIgnore
    protected List<OsEntity> osEntities;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project", nullable = false)
    @NotNull(groups = View.Persist.class)
    private ProjectEntity project;
    @Basic
    @Size(max = 20)
    @SafeHtml(groups = {View.Web.class})
    @NotBlank
    @Column(name = "number", nullable = false, length = 20, unique = true)
    private String number;
    @Basic
    @Size(max = 150)
    @SafeHtml(groups = {View.Web.class})
    @Column(name = "comments", nullable = false, length = 150)
    private String comments;

    public InternalNumberEntity() {

    }

    public InternalNumberEntity(ProjectEntity project, String number) {
        this(null, project, number);
    }

    public InternalNumberEntity(Integer id, ProjectEntity project, String number) {
        super(id);
        this.project = project;
        this.number = number;
    }

    public List<OsEntity> getOsEntities() {
        return osEntities;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}



