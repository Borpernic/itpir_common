package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = StatusImplementerEntity.ALL_SORTED, query = "SELECT s FROM StatusImplementerEntity s ORDER BY s.status ASC"),
        @NamedQuery(name = StatusImplementerEntity.ALL, query = "SELECT s FROM StatusImplementerEntity s ORDER BY s.id ASC"),
        @NamedQuery(name = StatusImplementerEntity.DELETE, query = "DELETE FROM StatusImplementerEntity s WHERE s.id=:id"),
        @NamedQuery(name = StatusImplementerEntity.DELETE_ALL, query = "DELETE FROM StatusImplementerEntity s"),
        @NamedQuery(name = StatusImplementerEntity.GET, query = "SELECT s FROM StatusImplementerEntity s WHERE s.id=:id"),
        @NamedQuery(name = StatusImplementerEntity.GET_BY_COMMENTS, query = "SELECT s FROM StatusImplementerEntity s WHERE s.comments=:comments"),
})

@Entity
@Table(name = "status_implementer", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"status"}, name = "status_implementer_status_idx")})
public class StatusImplementerEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "StatusImplementerEntity.getAllSorted";
    public static final String ALL = "StatusImplementerEntity.getAll";
    public static final String DELETE = "StatusImplementerEntity.delete";
    public static final String DELETE_ALL = "StatusImplementerEntity.deleteAll";
    public static final String GET = "StatusImplementerEntity.get";
    public static final String GET_BY_COMMENTS = "StatusImplementerEntity.getByComments";

    @NotBlank
    @Size(min = 2, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "status", nullable = false, length = 50, unique = true)
    private String status;

    @NotBlank
    @Size(min = 2, max = 150)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "comments", nullable = false, length = 150)
    private String comments;

    public StatusImplementerEntity() {

    }

    public StatusImplementerEntity(Integer id, String status) {
        super(id);
        this.status = status;
    }

    public StatusImplementerEntity(String comments) {
        this(null, comments);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
