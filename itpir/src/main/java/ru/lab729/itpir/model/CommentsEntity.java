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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;


@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = CommentsEntity.ALL_SORTED, query = "SELECT a FROM ActivityEntity a LEFT JOIN FETCH a.os ORDER BY a.os.site.operator.operator, a.os.site.number ASC"),
        @NamedQuery(name = CommentsEntity.ALL, query = "SELECT a FROM ActivityEntity a ORDER BY a.id ASC"),
        @NamedQuery(name = CommentsEntity.ALL_BY_OS, query = "SELECT a FROM ActivityEntity a WHERE a.os.id=?1 ORDER BY a.id ASC"),
        @NamedQuery(name = CommentsEntity.DELETE, query = "DELETE FROM ActivityEntity a WHERE a.id=:id"),
        @NamedQuery(name = CommentsEntity.DELETE_ALL, query = "DELETE FROM ActivityEntity a"),
        @NamedQuery(name = CommentsEntity.DELETE_BY_OS_ALL, query = "DELETE FROM ActivityEntity a where a.os.id=?1"),
        @NamedQuery(name = CommentsEntity.GET, query = "SELECT a FROM ActivityEntity a WHERE a.id=:id"),
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

}
