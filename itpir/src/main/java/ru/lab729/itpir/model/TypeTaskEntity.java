package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = TypeTaskEntity.ALL_SORTED, query = "SELECT t FROM TypeTaskEntity t ORDER BY t.type ASC"),
        @NamedQuery(name = TypeTaskEntity.ALL, query = "SELECT t FROM TypeTaskEntity t ORDER BY t.id ASC"),
        @NamedQuery(name = TypeTaskEntity.DELETE, query = "DELETE FROM TypeTaskEntity t WHERE t.id=:id"),
        @NamedQuery(name = TypeTaskEntity.DELETE_ALL, query = "DELETE FROM TypeTaskEntity t"),
        @NamedQuery(name = TypeTaskEntity.GET, query = "SELECT t FROM TypeTaskEntity t WHERE t.id=:id"),
})

@Entity
@Table(name = "type_task", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"type"}, name = "type_task_type_idx")})
public class TypeTaskEntity extends AbstractBaseWithUserEntity {

    public static final String ALL_SORTED = "TypeTaskEntity.getAllSorted";
    public static final String ALL = "TypeTaskEntity.getAll";
    public static final String DELETE = "TypeTaskEntity.delete";
    public static final String DELETE_ALL = "TypeTaskEntity.deleteAll";
    public static final String GET = "TypeTaskEntity.get";
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "typeTask")
//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("dateTime DESC")
//    @JsonIgnore
    protected List<TaskEntity> taskEntities;
    @NotBlank
    @Size(min = 2, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "type", nullable = false, length = 50, unique = true)
    private String type;

    @Size(min = 2, max = 100)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "comments", nullable = false, length = 100)
    private String comments;

    public TypeTaskEntity() {

    }

    public TypeTaskEntity(String type) {
        this(null, type);
    }

    public TypeTaskEntity(Integer id, String type) {
        super(id);
        this.type = type;
    }

    public TypeTaskEntity(Integer id, String type, String comments) {
        this(id, type);
        this.comments = comments;
    }

    public List<TaskEntity> getTaskEntities() {
        return taskEntities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
