package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = DepartmentEntity.ALL_SORTED, query = "SELECT d FROM DepartmentEntity d ORDER BY d.department ASC"),
        @NamedQuery(name = DepartmentEntity.ALL, query = "SELECT d FROM DepartmentEntity d ORDER BY d.id ASC"),
        @NamedQuery(name = DepartmentEntity.DELETE, query = "DELETE FROM DepartmentEntity d WHERE d.id=:id"),
        @NamedQuery(name = DepartmentEntity.DELETE_ALL, query = "DELETE FROM DepartmentEntity d"),
        @NamedQuery(name = DepartmentEntity.GET, query = "SELECT d FROM DepartmentEntity d WHERE d.id=:id"),
})
@Entity
@Table(name = "department", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"department"}, name = "department_department_idx")})
public class DepartmentEntity  extends AbstractBaseEntity {

    public static final String ALL_SORTED = "DepartmentEntity.getAllSorted";
    public static final String ALL = "DepartmentEntity.getAll";
    public static final String DELETE = "DepartmentEntity.delete";
    public static final String DELETE_ALL = "DepartmentEntity.deleteAll";
    public static final String GET = "DepartmentEntity.get";

    @NotBlank
    @Size(min = 2, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "department", nullable = false, length = 50, unique = true)
    private String department;


    @NotBlank
    @Size(min = 2, max = 150)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "comments", nullable = false, length = 150)
    private String comments;


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public DepartmentEntity() {

    }

    public DepartmentEntity( String department) {
        this(null, department);
    }

    public DepartmentEntity(Integer id,  String department) {
        super(id);
        this.department = department;
    }
}
