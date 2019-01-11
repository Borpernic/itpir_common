package ru.lab729.itpir.ddl;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;
import ru.lab729.itpir.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = RegionEntity.ALL_SORTED, query = "SELECT r FROM RegionEntity r ORDER BY r.name ASC"),
        @NamedQuery(name = RegionEntity.ALL, query = "SELECT r FROM RegionEntity r ORDER BY r.id ASC"),
        @NamedQuery(name = RegionEntity.DELETE, query = "DELETE FROM RegionEntity r WHERE r.id=:id"),
        @NamedQuery(name = RegionEntity.DELETE_ALL, query = "DELETE FROM RegionEntity r"),
        @NamedQuery(name = RegionEntity.GET, query = "SELECT o FROM RegionEntity r WHERE r.id=:id"),
        @NamedQuery(name = RegionEntity.GET_BY_COMMENTS, query = "SELECT o FROM RegionEntity r WHERE r.comments=:comments"),
})


@Entity
@Table(name = "region", schema = "public", catalog = "itpirdb" , uniqueConstraints = {@UniqueConstraint(columnNames = {"id"}, name = "region_region_idx")})
public class RegionEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "RegionEntity.getAllSorted";
    public static final String ALL = "RegionEntity.getAll";
    public static final String DELETE = "RegionEntity.delete";
    public static final String DELETE_ALL = "RegionEntity.deleteAll";
    public static final String GET = "RegionEntity.get";
    public static final String GET_BY_COMMENTS = "RegionEntity.getByComments";


    @NotBlank
    @Size(min = 2, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "region", nullable = false, length = 50)
    private String region;

    @NotBlank
    @Size(min = 2, max = 150)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "comments", nullable = false, length = 150)
    private String comments;



    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegionEntity that = (RegionEntity) o;
        return id == that.id &&
                Objects.equals(region, that.region) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, region, comments);
    }*/

    public RegionEntity() {
    }

    public RegionEntity(String region,  String comments) {
        this.region = region;
        this.comments = comments;
    }

    public RegionEntity(Integer id,  String region,  String comments) {
        super(id);
        this.region = region;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "RegionEntity{" +
                "region='" + region + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
