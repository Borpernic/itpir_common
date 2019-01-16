package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;
import ru.lab729.itpir.annotation.Phone;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigInteger;

@NamedQueries({
        @NamedQuery(name = ImplementerEntity.ALL_SORTED, query = "SELECT i FROM ImplementerEntity i ORDER BY i.implementer ASC"),
        @NamedQuery(name = ImplementerEntity.ALL, query = "SELECT i FROM ImplementerEntity i ORDER BY i.id ASC"),
        @NamedQuery(name = ImplementerEntity.DELETE, query = "DELETE FROM ImplementerEntity i WHERE i.id=:id"),
        @NamedQuery(name = ImplementerEntity.DELETE_ALL, query = "DELETE FROM ImplementerEntity i"),
        @NamedQuery(name = ImplementerEntity.GET, query = "SELECT i FROM ImplementerEntity i WHERE i.id=:id"),

})

@Entity
@Table(name = "implementer", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"implementer"}, name = "implementer_implementer_idx")})
public class ImplementerEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "ImplementerEntity.getAllSorted";
    public static final String ALL = "ImplementerEntity.getAll";
    public static final String DELETE = "ImplementerEntity.delete";
    public static final String DELETE_ALL = "ImplementerEntity.deleteAll";
    public static final String GET = "ImplementerEntity.get";

    @NotBlank
    @Size(min = 2, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "implementer", nullable = false, length = 50, unique = true)
    private String implementer;

    @Phone
    @Size(min = 12, max = 12)
    @NotBlank
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "phone", nullable = false, length = 12)
    private String phone;

    @Email
    @NotBlank
    @Size(max = 100)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "email", nullable = true, length = 30, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status", nullable = false)
    @NotNull(groups = View.Persist.class)
    private StatusImplementerEntity status;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type", nullable = false)
    @NotNull(groups = View.Persist.class)
    private TypeImplementerEntity type;

    @Column(name = "rating", nullable = false)
    @Range(min = 0, max = 100)
    @NotNull
    private BigInteger rating;

    @NotBlank
    @Size(min = 2, max = 150)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "comments", nullable = false, length = 150)
    private String comments;


    public String getImplementer() {
        return implementer;
    }

    public void setImplementer(String implementer) {
        this.implementer = implementer;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public StatusImplementerEntity getStatus() {
        return status;
    }

    public void setStatus(StatusImplementerEntity status) {
        this.status = status;
    }


    public TypeImplementerEntity getType() {
        return type;
    }

    public void setType(TypeImplementerEntity type) {
        this.type = type;
    }


    public BigInteger getRating() {
        return rating;
    }

    public void setRating(BigInteger rating) {
        this.rating = rating;
    }


    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ImplementerEntity() {


    }

    public ImplementerEntity(String implementer, String phone, String email, StatusImplementerEntity status,
                             TypeImplementerEntity type, BigInteger rating) {

        this(null, implementer, phone, email, status, type, rating);
    }

    public ImplementerEntity(Integer id, String implementer, String phone, String email, StatusImplementerEntity status,
                             TypeImplementerEntity type, BigInteger rating) {
        super(id);
        this.implementer = implementer;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.type = type;
        this.rating = rating;
        this.comments = comments;
    }
}
