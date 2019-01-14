package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = BandEntity.ALL_SORTED, query = "SELECT b FROM BandEntity b ORDER BY b.band ASC"),
        @NamedQuery(name = BandEntity.ALL, query = "SELECT b FROM BandEntity b ORDER BY b.id ASC"),
        @NamedQuery(name = BandEntity.DELETE, query = "DELETE FROM BandEntity b WHERE b.id=:id"),
        @NamedQuery(name = BandEntity.DELETE_ALL, query = "DELETE FROM BandEntity b"),
        @NamedQuery(name = BandEntity.GET, query = "SELECT b FROM BandEntity b WHERE b.id=:id"),
})

@Entity
@Table(name = "band", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"band"}, name = "band_band_idx")})
public class BandEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "BandEntity.getAllSorted";
    public static final String ALL = "BandEntity.getAll";
    public static final String DELETE = "BandEntity.delete";
    public static final String DELETE_ALL = "BandEntity.deleteAll";
    public static final String GET = "BandEntity.get";

    @Basic
    @NotBlank
    @Size(min = 3, max = 100)
    @SafeHtml(groups = {View.Web.class})
    @Column(name = "band", nullable = false, length = 100, unique = true)
    private String band;

    @Size(min = 3, max = 100)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "comments", nullable = true, length = 100)
    private String comments;


    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }


    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public BandEntity() {

    }

    public BandEntity(String band) {
        this(null, band);
    }

    public BandEntity(Integer id, String band) {
        super(id);
        this.band = band;
    }
}
