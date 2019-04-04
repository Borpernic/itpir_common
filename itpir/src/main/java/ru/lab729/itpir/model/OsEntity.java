package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;
import ru.lab729.itpir.View;
import ru.lab729.itpir.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = OsEntity.ALL_OPERATOR_NUMBER_SORTED, query = "SELECT o FROM OsEntity o JOIN FETCH SiteEntity s ORDER BY s.id, s.number ASC"),
        @NamedQuery(name = OsEntity.ALL, query = "SELECT o FROM OsEntity o ORDER BY o.id ASC"),
        @NamedQuery(name = OsEntity.ALL_BY_OPERATOR_SORTED, query = "SELECT o FROM OsEntity o JOIN FETCH SiteEntity s JOIN FETCH OperatorEntity op WHERE op.id=?1 ORDER BY s.number ASC"),

        @NamedQuery(name = OsEntity.DELETE, query = "DELETE FROM OsEntity o WHERE o.id=:id"),
        @NamedQuery(name = OsEntity.DELETE_ALL_BY_SITE, query = "DELETE FROM OsEntity o WHERE o.site.id=?1"),
        @NamedQuery(name = OsEntity.DELETE_ALL, query = "DELETE FROM OsEntity o"),
        @NamedQuery(name = OsEntity.GET, query = "SELECT o FROM OsEntity o WHERE o.id=:id"),
})

@Entity
@Table(name = "os", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"site", "internal_number"}, name = "os_site_internal_number_id_key")})
public class OsEntity extends AbstractBaseWithUserEntity {

    public static final String ALL_OPERATOR_NUMBER_SORTED = "OsEntity.getAllOperatorNumberSorted";
    public static final String ALL = "OsEntity.getAll";
    public static final String ALL_BY_OPERATOR_SORTED = "OsEntity.getAllByOperatorSorted";
    public static final String DELETE = "OsEntity.delete";
    public static final String DELETE_ALL_BY_SITE = "OsEntity.deleteBySite";
    public static final String DELETE_ALL = "OsEntity.deleteAll";
    public static final String GET = "OsEntity.get";
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "os")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("dateTime DESC")
//    @JsonIgnore
    protected List<ActivityEntity> activityEntities;
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    @Column(name = "date_time", nullable = false, columnDefinition = "timestamp default now()")
    private LocalDateTime dateTime = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "site", nullable = false)
    @NotNull(groups = View.Persist.class)
    private SiteEntity site;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "internal_number", nullable = false)
    @NotNull(groups = View.Persist.class)
    private InternalNumberEntity internalNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curator", nullable = false)
    @NotNull(groups = View.Persist.class)
    private CuratorEntity curator;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "band", nullable = false)
    @NotNull(groups = View.Persist.class)
    private BandEntity band;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_os", nullable = false)
    @NotNull(groups = View.Persist.class)
    private TypeOsEntity typeOs;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_bs", nullable = false)
    @NotNull(groups = View.Persist.class)
    private TypeBsEntity typeBs;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_ams", nullable = false)
    @NotNull(groups = View.Persist.class)
    private TypeAmsEntity typeAms;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_afs", nullable = false)
    @NotNull(groups = View.Persist.class)
    private TypeAfsEntity typeAfs;
    @Basic
    @Column(name = "source_data", nullable = true, columnDefinition = "boolean default false")
    private Boolean sourceData = false;
    @Basic
    @Column(name = "source_rd", nullable = true, columnDefinition = "boolean default false")
    private Boolean sourceRd = false;
    @Basic
    @Column(name = "rns", nullable = true, columnDefinition = "boolean default false")
    private Boolean rns = false;
    @Basic
    @Column(name = "f1a", nullable = true, columnDefinition = "boolean default false")
    private Boolean f1A = false;
    @Basic
    @Column(name = "survey", nullable = true, columnDefinition = "boolean default false")
    private Boolean survey = false;
    @Basic
    @Column(name = "ssr", nullable = true, columnDefinition = "boolean default false")
    private Boolean ssr = false;
    @Basic
    @Column(name = "tssr", nullable = true, columnDefinition = "boolean default false")
    private Boolean tssr = false;
    @Basic
    @Column(name = "rd", nullable = true, columnDefinition = "boolean default false")
    private Boolean rd = false;
    @Basic
    @Column(name = "impldoc", nullable = true, columnDefinition = "boolean default false")
    private Boolean impldoc = false;
    @Basic
    @Column(name = "signedll", nullable = true, columnDefinition = "boolean default false")
    private Boolean signedll = false;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_os", nullable = false)
    @NotNull(groups = View.Persist.class)
    private StatusOsEntity statusOs;
    @Basic
    @Size(max = 100)
    @SafeHtml(groups = {View.Web.class})
    @NotBlank
    @Column(name = "comments", nullable = false, length = 150)
    private String comments;

    public List<ActivityEntity> getActivityEntities() {
        return activityEntities;
    }

    public LocalDateTime getDate() {
        return dateTime;
    }

    public void setDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public SiteEntity getSite() {
        return site;
    }

    public void setSite(SiteEntity site) {
        this.site = site;
    }

    public InternalNumberEntity getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(InternalNumberEntity internalNumber) {
        this.internalNumber = internalNumber;
    }

    public CuratorEntity getCurator() {
        return curator;
    }

    public void setCurator(CuratorEntity curator) {
        this.curator = curator;
    }

    public BandEntity getBand() {
        return band;
    }

    public void setBand(BandEntity band) {
        this.band = band;
    }

    public TypeOsEntity getTypeOs() {
        return typeOs;
    }

    public void setTypeOs(TypeOsEntity typeOs) {
        this.typeOs = typeOs;
    }

    public TypeBsEntity getTypeBs() {
        return typeBs;
    }

    public void setTypeBs(TypeBsEntity typeBs) {
        this.typeBs = typeBs;
    }

    public TypeAmsEntity getTypeAms() {
        return typeAms;
    }

    public void setTypeAms(TypeAmsEntity typeAms) {
        this.typeAms = typeAms;
    }

    public TypeAfsEntity getTypeAfs() {
        return typeAfs;
    }

    public void setTypeAfs(TypeAfsEntity typeAfs) {
        this.typeAfs = typeAfs;
    }

    public Boolean getSourceData() {
        return sourceData;
    }

    public void setSourceData(Boolean sourceData) {
        this.sourceData = sourceData;
    }

    public Boolean getSourceRd() {
        return sourceRd;
    }

    public void setSourceRd(Boolean sourceRd) {
        this.sourceRd = sourceRd;
    }

    public Boolean getRns() {
        return rns;
    }

    public void setRns(Boolean rns) {
        this.rns = rns;
    }

    public Boolean getF1A() {
        return f1A;
    }

    public void setF1A(Boolean f1A) {
        this.f1A = f1A;
    }

    public Boolean getSurvey() {
        return survey;
    }

    public void setSurvey(Boolean survey) {
        this.survey = survey;
    }

    public Boolean getSsr() {
        return ssr;
    }

    public void setSsr(Boolean ssr) {
        this.ssr = ssr;
    }

    public Boolean getTssr() {
        return tssr;
    }

    public void setTssr(Boolean tssr) {
        this.tssr = tssr;
    }

    public Boolean getRd() {
        return rd;
    }

    public void setRd(Boolean rd) {
        this.rd = rd;
    }

    public Boolean getImpldoc() {
        return impldoc;
    }

    public void setImpldoc(Boolean impldoc) {
        this.impldoc = impldoc;
    }

    public Boolean getSignedll() {
        return signedll;
    }

    public void setSignedll(Boolean signedll) {
        this.signedll = signedll;
    }

    public StatusOsEntity getStatusOs() {
        return statusOs;
    }

    public void setStatusOs(StatusOsEntity statusOs) {
        this.statusOs = statusOs;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
