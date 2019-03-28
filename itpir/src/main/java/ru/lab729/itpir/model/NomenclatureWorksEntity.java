package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = NomenclatureWorksEntity.ALL_SORTED, query = "SELECT n FROM NomenclatureWorksEntity n ORDER BY n.works ASC"),
        @NamedQuery(name = NomenclatureWorksEntity.ALL, query = "SELECT n FROM NomenclatureWorksEntity n ORDER BY n.id ASC"),
        @NamedQuery(name = NomenclatureWorksEntity.DELETE, query = "DELETE FROM NomenclatureWorksEntity n WHERE n.id=:id"),
        @NamedQuery(name = NomenclatureWorksEntity.DELETE_ALL, query = "DELETE FROM NomenclatureWorksEntity n"),
        @NamedQuery(name = NomenclatureWorksEntity.GET, query = "SELECT n FROM NomenclatureWorksEntity n WHERE n.id=:id"),
        @NamedQuery(name = NomenclatureWorksEntity.GET_BY_COMMENTS, query = "SELECT n FROM NomenclatureWorksEntity n WHERE n.comments=:comments"),
})

@Entity
@Table(name = "nomenclature_works", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"works"}, name = "nomenclature_works_works_idx")})
public class NomenclatureWorksEntity extends AbstractBaseWithUserEntity {

    public static final String ALL_SORTED = "NomenclatureWorksEntity.getAllSorted";
    public static final String ALL = "NomenclatureWorksEntity.getAll";
    public static final String DELETE = "NomenclatureWorksEntity.delete";
    public static final String DELETE_ALL = "NomenclatureWorksEntity.deleteAll";
    public static final String GET = "NomenclatureWorksEntity.get";
    public static final String GET_BY_COMMENTS = "NomenclatureWorksEntity.getByComments";

    @NotBlank
    @Size(min = 2, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "works", nullable = false, length = 50, unique = true)
    private String works;

    // @NotBlank
    @Size(min = 2, max = 100)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "comments", nullable = true, length = 150)
    private String comments;

    public NomenclatureWorksEntity() {

    }

    public NomenclatureWorksEntity(String works) {
        this(null, works);
    }

    public NomenclatureWorksEntity(Integer id, String works) {
        super(id);
        this.works = works;
    }

    public NomenclatureWorksEntity(Integer id, String works, String comments) {
        this(id, works);
        this.comments = comments;
    }

    @Basic
    @Column(name = "works", nullable = false, length = -1, unique = true)
    public String getWorks() {
        return works;
    }

    public void setWorks(String works) {
        this.works = works;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
