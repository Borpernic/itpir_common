package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = TypeImplementerEntity.ALL_SORTED, query = "SELECT t FROM TypeImplementerEntity t ORDER BY t.type ASC"),
        @NamedQuery(name = TypeImplementerEntity.ALL, query = "SELECT t FROM TypeImplementerEntity t ORDER BY t.id ASC"),
        @NamedQuery(name = TypeImplementerEntity.DELETE, query = "DELETE FROM TypeImplementerEntity t WHERE t.id=:id"),
        @NamedQuery(name = TypeImplementerEntity.DELETE_ALL, query = "DELETE FROM TypeImplementerEntity t"),
        @NamedQuery(name = TypeImplementerEntity.GET, query = "SELECT t FROM TypeImplementerEntity t WHERE t.id=:id"),
        @NamedQuery(name = TypeImplementerEntity.GET_BY_COMMENTS, query = "SELECT t FROM TypeImplementerEntity t WHERE t.comments=:comments"),
})

@Entity
@Table(name = "type_implementer", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"type"}, name = "type_implementer_type_idx")})
public class TypeImplementerEntity extends AbstractBaseWithUserEntity {

    public static final String ALL_SORTED = "TypeImplementerEntity.getAllSorted";
    public static final String ALL = "TypeImplementerEntity.getAll";
    public static final String DELETE = "TypeImplementerEntity.delete";
    public static final String DELETE_ALL = "TypeImplementerEntity.deleteAll";
    public static final String GET = "TypeImplementerEntity.get";
    public static final String GET_BY_COMMENTS = "TypeImplementerEntity.getByComments";

    @NotBlank
    @Size(min = 2, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "type", nullable = false, length = 50, unique = true)
    private String type;

    //@NotBlank
    @Size(min = 2, max = 100)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "comments", nullable = true, length = 100)
    private String comments;

    public TypeImplementerEntity() {

    }

    public TypeImplementerEntity(String type) {
        this(null, type);
    }

    public TypeImplementerEntity(Integer id, String type) {
        super(id);
        this.type = type;
    }

    public TypeImplementerEntity(Integer id, String type, String comments) {
        this(id, type);
        this.comments = comments;
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
