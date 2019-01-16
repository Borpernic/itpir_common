package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = StatusActivityEntity.ALL_SORTED, query = "SELECT s FROM StatusActivityEntity s ORDER BY s.status ASC"),
        @NamedQuery(name = StatusActivityEntity.ALL, query = "SELECT s FROM StatusActivityEntity s ORDER BY s.id ASC"),
        @NamedQuery(name = StatusActivityEntity.DELETE, query = "DELETE FROM StatusActivityEntity s WHERE s.id=:id"),
        @NamedQuery(name = StatusActivityEntity.DELETE_ALL, query = "DELETE FROM StatusActivityEntity s"),
        @NamedQuery(name = StatusActivityEntity.GET, query = "SELECT s FROM StatusActivityEntity s WHERE s.id=:id"),
})

@Entity
@Table(name = "status_activity", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"status"}, name = "status_activity_status_idx")})
public class StatusActivityEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "StatusActivityEntity.getAllSorted";
    public static final String ALL = "StatusActivityEntity.getAll";
    public static final String DELETE = "StatusActivityEntity.delete";
    public static final String DELETE_ALL = "StatusActivityEntity.deleteAll";
    public static final String GET = "StatusActivityEntity.get";

    @NotBlank
    @Size(min = 2, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "status", nullable = false, length = 50, unique = true)
    private String status;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public StatusActivityEntity() {

    }

    public StatusActivityEntity(String status) {
        this(null, status);
    }

    public StatusActivityEntity(Integer id, String status) {
        super(id);
        this.status = status;
    }
}
