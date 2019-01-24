package ru.lab729.itpir.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;
import ru.lab729.itpir.View;
import ru.lab729.itpir.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = CommentsEntity.ALL_SORTED, query = "SELECT c FROM CommentsEntity c LEFT JOIN FETCH c.os ORDER BY c.os.site.operator.operator ASC , c.os.site.number ASC, c.dateTime DESC "),
        @NamedQuery(name = CommentsEntity.ALL, query = "SELECT c FROM CommentsEntity c ORDER BY c.id ASC"),
        @NamedQuery(name = CommentsEntity.ALL_BY_OS, query = "SELECT c FROM CommentsEntity c WHERE c.os.id=?1 ORDER BY c.dateTime DESC"),
        @NamedQuery(name = CommentsEntity.DELETE, query = "DELETE FROM CommentsEntity c WHERE c.id=:id"),
        @NamedQuery(name = CommentsEntity.DELETE_ALL, query = "DELETE FROM CommentsEntity c"),
        @NamedQuery(name = CommentsEntity.DELETE_BY_OS_ALL, query = "DELETE FROM CommentsEntity c where c.os.id=?1"),
        @NamedQuery(name = CommentsEntity.GET, query = "SELECT c FROM CommentsEntity c WHERE c.id=:id"),
})

@Entity
@Table(name = "comments", schema = "public", catalog = "itpirdb")
public class CommentsEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "CommentsEntity.getAllSorted";
    public static final String ALL = "CommentsEntity.getAll";
    public static final String ALL_BY_OS = "CommentsEntity.getAllByOsSorted";
    public static final String DELETE = "CommentsEntity.delete";
    public static final String DELETE_ALL = "CommentsEntity.deleteAll";
    public static final String DELETE_BY_OS_ALL = "CommentsEntity.deleteByOsAll";
    public static final String GET = "CommentsEntity.get";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "os", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private OsEntity os;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "implementer", nullable = false)
    @NotNull(groups = View.Persist.class)
    private ImplementerEntity implementer;

    @Column(name = "date_time", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime dateTime;

    @Basic
    @Size(max = 150)
    @SafeHtml(groups = {View.Web.class})
    @NotBlank
    @Column(name = "comments", nullable = false, length = 150)
    private String comments;

    public CommentsEntity(OsEntity os, ImplementerEntity implementer, LocalDateTime dateTime, String comments) {
        this(null, os, implementer, dateTime, comments);
    }

    public CommentsEntity(Integer id, OsEntity os, ImplementerEntity implementer, LocalDateTime dateTime, String comments) {
        super(id);
        this.os = os;
        this.implementer = implementer;
        this.dateTime = dateTime;
        this.comments = comments;
    }

    public CommentsEntity() {

    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public OsEntity getOs() {
        return os;
    }

    public void setOs(OsEntity os) {
        this.os = os;
    }

    public ImplementerEntity getImplementer() {
        return implementer;
    }

    public void setImplementer(ImplementerEntity implementer) {
        this.implementer = implementer;
    }
}
