package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = OperatorEntity.ALL_SORTED, query = "SELECT o FROM OperatorEntity o ORDER BY o.operator ASC"),
        @NamedQuery(name = OperatorEntity.ALL, query = "SELECT o FROM OperatorEntity o ORDER BY o.id ASC"),
        @NamedQuery(name = OperatorEntity.DELETE, query = "DELETE FROM OperatorEntity o WHERE o.id=:id"),
        @NamedQuery(name = OperatorEntity.DELETE_ALL, query = "DELETE FROM OperatorEntity o"),
        @NamedQuery(name = OperatorEntity.GET, query = "SELECT o FROM OperatorEntity o WHERE o.id=:id"),
        @NamedQuery(name = OperatorEntity.GET_BY_COMMENTS, query = "SELECT o FROM OperatorEntity o WHERE o.comments=:comments"),
})
@Entity
@Table(name = "operator", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"operator"}, name = "operator_operator_idx")})
public class OperatorEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "operator.getAllSorted";
    public static final String ALL = "operator.getAll";
    public static final String DELETE = "operator.delete";
    public static final String DELETE_ALL = "operator.deleteAll";
    public static final String GET = "operator.get";
    public static final String GET_BY_COMMENTS = "operator.getByComments";


    @NotBlank
    @Size(min = 2, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "operator", nullable = false, length = 50, unique = true)
    private String operator;

    @NotBlank
    @Size(min = 2, max = 150)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "comments", nullable = false, length = 150)
    private String comments;



    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperatorEntity that = (OperatorEntity) o;
        return id == that.id &&
                Objects.equals(operator, that.operator) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, operator, comments);
    }*/

    public OperatorEntity() {
    }

    public OperatorEntity(String operator, String comments) {
        this(null, operator, comments);
    }

    public OperatorEntity(Integer id, String operator, String comments) {
        super(id);
        this.operator = operator;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "OperatorEntity{" +
                "operator='" + operator + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
