package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = CustomerEntity.ALL_SORTED, query = "SELECT c FROM CustomerEntity c ORDER BY c.customer ASC"),
        @NamedQuery(name = CustomerEntity.ALL, query = "SELECT c FROM CustomerEntity c ORDER BY c.id ASC"),
        @NamedQuery(name = CustomerEntity.DELETE, query = "DELETE FROM CustomerEntity c WHERE c.id=:id"),
        @NamedQuery(name = CustomerEntity.DELETE_ALL, query = "DELETE FROM CustomerEntity c"),
        @NamedQuery(name = CustomerEntity.GET, query = "SELECT c FROM CustomerEntity c WHERE c.id=:id"),
})

@Entity
@Table(name = "customer", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"customer"}, name = "customer_customer_idx")})
public class CustomerEntity extends AbstractBaseWithUserEntity {

    public static final String ALL_SORTED = "CustomerEntity.getAllSorted";
    public static final String ALL = "CustomerEntity.getAll";
    public static final String DELETE = "CustomerEntity.delete";
    public static final String DELETE_ALL = "CustomerEntity.deleteAll";
    public static final String GET = "CustomerEntity.get";
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("project ASC")
//    @JsonIgnore
    protected List<ProjectEntity> projectEntityList;
    @NotBlank
    @Size(min = 3, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "customer", nullable = false, length = 50, unique = true)
    private String customer;
    @Basic
    @Size(min = 3, max = 100)
    @SafeHtml(groups = {View.Web.class})
    @Column(name = "comments", nullable = true, length = 100)
    private String comments;

    public CustomerEntity() {
    }

    public CustomerEntity(String customer) {
        this(null, customer);
    }

    public CustomerEntity(Integer id, String customer) {
        super(id);
        this.customer = customer;
    }

    public CustomerEntity(Integer id, String customer, String comments) {
        super(id);
        this.customer = customer;
        this.comments = comments;
    }

    public List<ProjectEntity> getProjectEntityList() {
        return projectEntityList;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
