package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = PmEntity.ALL_SORTED, query = "SELECT p FROM PmEntity p ORDER BY p.pm ASC"),
        @NamedQuery(name = PmEntity.ALL, query = "SELECT p FROM PmEntity p ORDER BY p.id ASC"),
        @NamedQuery(name = PmEntity.DELETE, query = "DELETE FROM PmEntity p WHERE p.id=:id"),
        @NamedQuery(name = PmEntity.DELETE_ALL, query = "DELETE FROM PmEntity p"),
        @NamedQuery(name = PmEntity.GET, query = "SELECT p FROM PmEntity p WHERE p.id=:id"),
})

@Entity
@Table(name = "pm", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"pm"}, name = "pm_pm_idx")})
public class PmEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "PmEntity.getAllSorted";
    public static final String ALL = "PmEntity.getAll";
    public static final String DELETE = "PmEntity.delete";
    public static final String DELETE_ALL = "PmEntity.deleteAll";
    public static final String GET = "PmEntity.get";
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pm")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("project ASC")
//    @JsonIgnore
    protected List<ProjectEntity> projectEntityList;
    @Basic
    @NotBlank
    @Size(min = 3, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Column(name = "pm", nullable = false, length = 50, unique = true)
    private String pm;
    @Basic
    @Size(min = 3, max = 100)
    @SafeHtml(groups = {View.Web.class})
    @Column(name = "comments", nullable = true, length = 100)
    private String comments;

    public PmEntity() {

    }

    public PmEntity(String pm) {
        this(null, pm);
    }

    public PmEntity(Integer id, String pm) {
        super(id);
        this.pm = pm;
    }

    public List<ProjectEntity> getProjectEntityList() {
        return projectEntityList;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
