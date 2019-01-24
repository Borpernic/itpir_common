package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@NamedQueries({
        @NamedQuery(name = TzpEntity.ALL_SORTED, query = "SELECT t FROM TzpEntity t ORDER BY t.tzp ASC"),
        @NamedQuery(name = TzpEntity.ALL, query = "SELECT t FROM TzpEntity t ORDER BY t.id ASC"),
        @NamedQuery(name = TzpEntity.DELETE, query = "DELETE FROM TzpEntity t WHERE t.id=:id"),
        @NamedQuery(name = TzpEntity.DELETE_ALL, query = "DELETE FROM TzpEntity t"),
        @NamedQuery(name = TzpEntity.GET, query = "SELECT t FROM TzpEntity t WHERE t.id=:id"),
        @NamedQuery(name = TzpEntity.GET_BY_TYPE_IMPLEMENTER, query = "SELECT t FROM TzpEntity t WHERE t.typeImplementer=:typeImplementer"),
})

@Entity
@Table(name = "tzp", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"tzp"}, name = "tzp_tzp_idx")})
public class TzpEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "TzpEntity.getAllSorted";
    public static final String ALL = "TzpEntity.getAll";
    public static final String DELETE = "TzpEntity.delete";
    public static final String DELETE_ALL = "TzpEntity.deleteAll";
    public static final String GET = "TzpEntity.get";
    public static final String GET_BY_TYPE_IMPLEMENTER = "TzpEntity.getByTypeImplementer";

    @NotBlank
    @Size(min = 2, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "tzp", nullable = false, length = 50, unique = true)
    private String tzp;

    @NotBlank
    @Size(min = 2, max = 15)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "razmernost", nullable = false, length = 15)
    private String razmernost;

    @Column(name = "price", nullable = false)
    @Range(min = 1, max = 1000000)
    @NotNull
    private BigInteger price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_os", nullable = false)
    @NotNull(groups = View.Persist.class)
    private TypeOsEntity typeOs;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_implementer", nullable = false)
    @NotNull(groups = View.Persist.class)
    private TypeImplementerEntity typeImplementer;

    @NotBlank
    @Size(min = 2, max = 150)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "comments", nullable = false, length = 150)
    private String comments;

    public TzpEntity() {

    }

    public TzpEntity(String tzp, String razmernost, BigInteger price, TypeOsEntity typeOs, TypeImplementerEntity typeImplementer) {
        this(null, tzp, razmernost, price, typeOs, typeImplementer);

    }

    public TzpEntity(Integer id, String tzp, String razmernost, BigInteger price, TypeOsEntity typeOs, TypeImplementerEntity typeImplementer) {
        super(id);
        this.tzp = tzp;
        this.razmernost = razmernost;
        this.price = price;
        this.typeOs = typeOs;
        this.typeImplementer = typeImplementer;
        this.comments = comments;
    }

    public String getTzp() {
        return tzp;
    }

    public void setTzp(String tzp) {
        this.tzp = tzp;
    }

    public String getRazmernost() {
        return razmernost;
    }

    public void setRazmernost(String razmernost) {
        this.razmernost = razmernost;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public TypeOsEntity getTypeOs() {
        return typeOs;
    }

    public void setTypeOs(TypeOsEntity typeOs) {
        this.typeOs = typeOs;
    }

    public TypeImplementerEntity getTypeImplementer() {
        return typeImplementer;
    }

    public void setTypeImplementer(TypeImplementerEntity typeImplementer) {
        this.typeImplementer = typeImplementer;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
