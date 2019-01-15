package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = ProjectEntity.ALL_SORTED, query = "SELECT p FROM ProjectEntity p ORDER BY p.project ASC"),
        @NamedQuery(name = ProjectEntity.ALL_PM_SORTED, query = "SELECT p FROM ProjectEntity p JOIN FETCH PmEntity pm WHERE pm.id=?1 ORDER BY pm.pm ASC"),
        @NamedQuery(name = ProjectEntity.ALL_CUSTOMER_SORTED, query = "SELECT p FROM ProjectEntity p JOIN FETCH CustomerEntity c WHERE c.id=?1 ORDER BY c.customer ASC"),

        @NamedQuery(name = ProjectEntity.DELETE, query = "DELETE FROM ProjectEntity p WHERE p.id=:id"),
        @NamedQuery(name = ProjectEntity.DELETE_ALL_BY_PM, query = "DELETE FROM ProjectEntity p WHERE p.pm.id=?1"),
        @NamedQuery(name = ProjectEntity.DELETE_ALL_BY_CUSTOMER, query = "DELETE FROM ProjectEntity p WHERE p.customer.id=?1"),
        @NamedQuery(name = ProjectEntity.DELETE_ALL, query = "DELETE FROM ProjectEntity p"),
        @NamedQuery(name = ProjectEntity.GET, query = "SELECT p FROM ProjectEntity p WHERE p.id=:id"),

})

@Entity
@Table(name = "project", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"project"}, name = "project_project_idx")})
public class ProjectEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "ProjectEntity.getAllSorted";
    public static final String ALL_PM_SORTED = "ProjectEntity.getAllPmSorted";
    public static final String ALL_CUSTOMER_SORTED = "ProjectEntity.getAllCustomerSorted";
    public static final String DELETE = "ProjectEntity.delete";
    public static final String DELETE_ALL_BY_PM = "ProjectEntity.deleteAllByPm";
    public static final String DELETE_ALL_BY_CUSTOMER = "ProjectEntity.deleteAllByCustomer";
    public static final String DELETE_ALL = "ProjectEntity.deleteAll";
    public static final String GET = "ProjectEntity.get";


    @Basic
    @Size(min = 3, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @NotBlank
    @Column(name = "project", nullable = false, length = 50, unique = true)
    private String project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pm", nullable = false)
    @NotNull(groups = View.Persist.class)
    private PmEntity pm;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer", nullable = false)
    @NotNull(groups = View.Persist.class)
    private CustomerEntity customer;

    @Basic
    @Size(min = 3, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Column(name = "comments", nullable = false, length = 50)
    private String comments;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }


    public PmEntity getPm() {
        return pm;
    }

    public void setPm(PmEntity pm) {
        this.pm = pm;
    }


    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }


    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("number ASC")
//    @JsonIgnore
    protected List<InternalNumberEntity> internalNumberEntities;

    public ProjectEntity() {

    }

    public ProjectEntity(String project, PmEntity pm, CustomerEntity customer) {
        this(null, project, pm, customer);
    }

    public ProjectEntity(Integer id, String project, PmEntity pm, CustomerEntity customer) {
        super(id);
        this.project = project;
        this.pm = pm;
        this.customer = customer;
    }
}
