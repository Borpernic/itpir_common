package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;
import ru.lab729.itpir.annotation.Phone;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = CuratorEntity.ALL_SORTED, query = "SELECT c FROM CuratorEntity c ORDER BY c.curator ASC"),
        @NamedQuery(name = CuratorEntity.ALL_BY_OPERATOR_SORTED, query = "SELECT c FROM CuratorEntity c WHERE c.operator.id=?1 ORDER BY c.curator ASC"),
        @NamedQuery(name = CuratorEntity.ALL, query = "SELECT c FROM CuratorEntity c ORDER BY c.id ASC"),
        @NamedQuery(name = CuratorEntity.DELETE, query = "DELETE FROM CuratorEntity c WHERE c.id=:id"),
        @NamedQuery(name = CuratorEntity.DELETE_ALL, query = "DELETE FROM CuratorEntity c"),
        @NamedQuery(name = CuratorEntity.GET, query = "SELECT c FROM CuratorEntity c WHERE c.id=:id"),

})

@Entity
@Table(name = "curator", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"curator"}, name = "curator_curator_idx")})
public class CuratorEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "CuratorEntity.getAllSorted";
    public static final String ALL_BY_OPERATOR_SORTED = "CuratorEntity.getAllByOperatorSorted";
    public static final String ALL = "CuratorEntity.getAll";
    public static final String DELETE = "CuratorEntity.delete";
    public static final String DELETE_ALL = "CuratorEntity.deleteAll";
    public static final String GET = "CuratorEntity.get";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "operator", nullable = false)
    @NotNull(groups = View.Persist.class)
    private OperatorEntity operator;


    @Basic
    @NotBlank
    @Size(min = 3, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Column(name = "curator", nullable = true, length = 50, unique = true)
    private String curator;

    @Phone
    @Size(min = 12, max = 12)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "phone", nullable = true, length = 12)
    private String phone;

    @Email
    @Size(max = 100)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "email", nullable = true, length = 100)
    private String email;

    @Size(min = 3, max = 100)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "comments", nullable = true, length = 100)
    private String comments;


    public OperatorEntity getOperator() {
        return operator;
    }

    public void setOperator(OperatorEntity operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "curator", nullable = false, length = -1)
    public String getCurator() {
        return curator;
    }

    public void setCurator(String curator) {
        this.curator = curator;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = -1)
    public String getPhone() {
        return phone;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curator")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("id DESC")
//    @JsonIgnore
    protected List<OsEntity> osEntities;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public CuratorEntity() {

    }

    public CuratorEntity(OperatorEntity operator, String curator) {
        this(null, operator, curator);
    }

    public CuratorEntity(Integer id, OperatorEntity operator, String curator) {
        super(id);
        this.operator = operator;
        this.curator = curator;
    }
}
