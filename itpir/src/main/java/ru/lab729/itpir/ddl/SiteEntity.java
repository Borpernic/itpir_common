package ru.lab729.itpir.ddl;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;
import ru.lab729.itpir.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.*;
import java.util.Objects;

@Entity

//@Table(name = "meals",)
@Table(name = "site", uniqueConstraints = {@UniqueConstraint(columnNames = {"site_number", "operator_id"}, name = "site_site_number_operator_id_key")})
public class SiteEntity extends AbstractBaseEntity {

    @Basic
    @Column(name = "site_number", nullable = false, length = 10)
    @NotBlank
    @Size(min = 2, max = 10)
    @SafeHtml(groups = {View.Web.class})
    private String siteNumber;

    @Basic
    @Column(name = "site_name", nullable = false, length = 50)
    @NotBlank
    @Size(min = 2, max = 50)
    @SafeHtml(groups = {View.Web.class})
    private String siteName;

    //  @Basic
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "operator_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    // @Column(name = "operator_id", nullable = false)
    private OperatorEntity operator;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ad_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    //@Column(name = "ad_id", nullable = false)
    private AdEntity ad;


    @Column(name = "site_date", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Size(min = 2, max = 120)
    @SafeHtml(groups = {View.Web.class})
    private Date siteDate = new java.util.Date();

    @Basic
    @Column(name = "comments", nullable = true, length = -1)
    @Size(min = 2, max = 50)
    @SafeHtml(groups = {View.Web.class})
    private String comments;

    public SiteEntity() {
    }

    public SiteEntity(Integer id, String siteNumber, String siteName, Date siteDate, String comments) {
        super(id);
        this.siteNumber = siteNumber;
        this.siteName = siteName;
        this.siteDate = siteDate;
        this.comments = comments;
    }

    public SiteEntity(String siteNumber, String siteName, Date siteDate, String comments) {
        this(null, siteNumber, siteName, siteDate, comments);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SiteEntity that = (SiteEntity) o;
        return operator.getId() == that.getId() &&
                ad.getId() == that.getId() &&
                id == that.id &&
                Objects.equals(siteNumber, that.siteNumber) &&
                Objects.equals(siteName, that.siteName) &&
                Objects.equals(siteDate, that.siteDate) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(siteNumber, operator.getId(), ad, siteName, siteDate, comments, id);
    }

    public String getSiteNumber() {
        return siteNumber;
    }

    public void setSiteNumber(String siteNumber) {
        this.siteNumber = siteNumber;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public OperatorEntity getOperator() {
        return operator;
    }

    public void setOperator(OperatorEntity operator) {
        this.operator = operator;
    }

    public AdEntity getAd() {
        return ad;
    }

    public void setAd(AdEntity ad) {
        this.ad = ad;
    }

    public Date getSiteDate() {
        return siteDate;
    }

    public void setSiteDate(Date siteDate) {
        this.siteDate = siteDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
