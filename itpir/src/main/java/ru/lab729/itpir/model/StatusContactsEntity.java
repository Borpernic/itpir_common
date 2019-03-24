package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NamedQueries({
        @NamedQuery(name = StatusContactsEntity.ALL_SORTED, query = "SELECT s FROM StatusContactsEntity s ORDER BY s.status ASC"),
        @NamedQuery(name = StatusContactsEntity.ALL, query = "SELECT s FROM StatusContactsEntity s ORDER BY s.id ASC"),
        @NamedQuery(name = StatusContactsEntity.DELETE, query = "DELETE FROM StatusContactsEntity s WHERE s.id=:id"),
        @NamedQuery(name = StatusContactsEntity.DELETE_ALL, query = "DELETE FROM StatusContactsEntity s"),
        @NamedQuery(name = StatusContactsEntity.GET, query = "SELECT s FROM StatusContactsEntity s WHERE s.id=:id"),
})

@Entity
@Table(name = "status_contacts", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"status"}, name = "status_contacts_status_idx")})
public class StatusContactsEntity extends AbstractBaseWithUserEntity {

    public static final String ALL_SORTED = "StatusContactsEntity.getAllSorted";
    public static final String ALL = "StatusContactsEntity.getAll";
    public static final String DELETE = "StatusContactsEntity.delete";
    public static final String DELETE_ALL = "StatusContactsEntity.deleteAll";
    public static final String GET = "StatusContactsEntity.get";

    @NotBlank
    @Size(min = 3, max = 15)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "status", nullable = false, length = 15, unique = true)
    private String status;

    public StatusContactsEntity() {
    }

    public StatusContactsEntity(String status) {
        this(null, status);
    }

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusContactsEntity that = (StatusContactsEntity) o;
        return id == that.id &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, status);
    }*/

    public StatusContactsEntity(Integer id, String status) {
        super(id);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
