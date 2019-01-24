package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = ResultTaskEntity.ALL_SORTED, query = "SELECT r FROM ResultTaskEntity r ORDER BY r.result ASC"),
        @NamedQuery(name = ResultTaskEntity.ALL, query = "SELECT r FROM ResultTaskEntity r ORDER BY r.id ASC"),
        @NamedQuery(name = ResultTaskEntity.DELETE, query = "DELETE FROM ResultTaskEntity r WHERE r.id=:id"),
        @NamedQuery(name = ResultTaskEntity.DELETE_ALL, query = "DELETE FROM ResultTaskEntity r"),
        @NamedQuery(name = ResultTaskEntity.GET, query = "SELECT r FROM ResultTaskEntity r WHERE r.id=:id"),
})
@Entity
@Table(name = "result_task", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"result"}, name = "result_task_result_idx")})
public class ResultTaskEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "ResultTaskEntity.getAllSorted";
    public static final String ALL = "ResultTaskEntity.getAll";
    public static final String DELETE = "ResultTaskEntity.delete";
    public static final String DELETE_ALL = "ResultTaskEntity.deleteAll";
    public static final String GET = "ResultTaskEntity.get";

    @NotBlank
    @Size(min = 2, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "result", nullable = false, length = 50, unique = true)
    private String result;

    @NotBlank
    @Size(min = 2, max = 150)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "comments", nullable = false, length = 150)
    private String comments;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ResultTaskEntity() {

    }

    public ResultTaskEntity(String result) {
        this(null, result);
    }

    public ResultTaskEntity(Integer id, String result) {
        super(id);
        this.result = result;
    }
}