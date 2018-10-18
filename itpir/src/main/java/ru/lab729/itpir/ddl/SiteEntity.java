package ru.lab729.itpir.ddl;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.lab729.itpir.View;
import ru.lab729.itpir.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Objects;

@Entity

//@Table(name = "meals",)
@Table(name = "site",  uniqueConstraints = {@UniqueConstraint(columnNames = {"site_number", "operator_id"}, name = "site_site_number_operator_id_key")})
public class SiteEntity extends AbstractBaseEntity {

    @Basic
    @Column(name = "site_number", nullable = false, length = 10)
    private String siteNumber;

    @Basic
    @Column(name = "site_name", nullable = false, length = 50)
    private String siteName;

    @Basic
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "operator_Id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)


   // @Column(name = "operator_id", nullable = false)
    private OperatorEntity operatorId;

    @Basic
    @Column(name = "ad_id", nullable = false)
    private int adId;


    @Basic
    @Column(name = "site_date", nullable = false)
    private Date siteDate;

    @Basic
    @Column(name = "comments", nullable = true, length = -1)
    private String comments;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SiteEntity that = (SiteEntity) o;
        return operatorId == that.operatorId &&
                adId == that.adId &&
                id == that.id &&
                Objects.equals(siteNumber, that.siteNumber) &&
                Objects.equals(siteName, that.siteName) &&
                Objects.equals(siteDate, that.siteDate) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(siteNumber, operatorId, adId, siteName, siteDate, comments, id);
    }
}
