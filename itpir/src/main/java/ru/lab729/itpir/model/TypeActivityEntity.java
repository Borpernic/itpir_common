package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = TypeActivityEntity.ALL_SORTED, query = "SELECT t FROM TypeActivityEntity t ORDER BY t.type ASC"),
        @NamedQuery(name = TypeActivityEntity.ALL, query = "SELECT t FROM TypeActivityEntity t ORDER BY t.id ASC"),
        @NamedQuery(name = TypeActivityEntity.DELETE, query = "DELETE FROM TypeActivityEntity t WHERE t.id=:id"),
        @NamedQuery(name = TypeActivityEntity.DELETE_ALL, query = "DELETE FROM TypeActivityEntity t"),
        @NamedQuery(name = TypeActivityEntity.GET, query = "SELECT t FROM TypeActivityEntity t WHERE t.id=:id"),
})

@Entity
@Table(name = "type_activity", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"type"}, name = "type_activity_type_idx")})
public class TypeActivityEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "TypeActivityEntity.getAllSorted";
    public static final String ALL = "TypeActivityEntity.getAll";
    public static final String DELETE = "TypeActivityEntity.delete";
    public static final String DELETE_ALL = "TypeActivityEntity.deleteAll";
    public static final String GET = "TypeActivityEntity.get";

    @NotBlank
    @NotNull
    @Size(min = 2, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "type", nullable = false, length = 50, unique = true)
    private String type;

    @Basic
    @Column(name = "source_data", nullable = false)
    @NotNull
    private boolean sourceData;
    @Basic
    @Column(name = "source_Rd", nullable = false)
    @NotNull
    private boolean sourceRd;
    @Basic
    @Column(name = "rns", nullable = false)
    @NotNull
    private boolean rns;
    @Basic
    @Column(name = "f1a", nullable = false)
    @NotNull
    private boolean f1A;
    @Basic
    @Column(name = "survey", nullable = false)
    @NotNull
    private boolean survey;
    @Basic
    @Column(name = "ssr", nullable = false)
    @NotNull
    private boolean ssr;
    @Basic
    @Column(name = "tssr", nullable = false)
    @NotNull
    private boolean tssr;
    @Basic
    @Column(name = "rd", nullable = false)
    @NotNull
    private boolean rd;
    @Basic
    @Column(name = "impldoc", nullable = false)
    @NotNull
    private boolean impldoc;
    @Basic
    @Column(name = "signedll", nullable = false)
    @NotNull
    private boolean signedll;

    @Basic
    @Size(max = 150)
    @SafeHtml(groups = {View.Web.class})
    @NotBlank
    @Column(name = "comments", nullable = false, length = 150)
    private String comments;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSourceData() {
        return sourceData;
    }

    public void setSourceData(boolean sourceData) {
        this.sourceData = sourceData;
    }

    public boolean isSourceRd() {
        return sourceRd;
    }

    public void setSourceRd(boolean sourceRd) {
        this.sourceRd = sourceRd;
    }

    public boolean isRns() {
        return rns;
    }

    public void setRns(boolean rns) {
        this.rns = rns;
    }

    public boolean isF1A() {
        return f1A;
    }

    public void setF1A(boolean f1A) {
        this.f1A = f1A;
    }

    public boolean isSurvey() {
        return survey;
    }

    public void setSurvey(boolean survey) {
        this.survey = survey;
    }

    public boolean isSsr() {
        return ssr;
    }

    public void setSsr(boolean ssr) {
        this.ssr = ssr;
    }

    public boolean isTssr() {
        return tssr;
    }

    public void setTssr(boolean tssr) {
        this.tssr = tssr;
    }

    public boolean isRd() {
        return rd;
    }

    public void setRd(boolean rd) {
        this.rd = rd;
    }

    public boolean isImpldoc() {
        return impldoc;
    }

    public void setImpldoc(boolean impldoc) {
        this.impldoc = impldoc;
    }

    public boolean isSignedll() {
        return signedll;
    }

    public void setSignedll(boolean signedll) {
        this.signedll = signedll;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public TypeActivityEntity() {

    }

    public TypeActivityEntity(String type, boolean sourceData, boolean sourceRd, boolean rns, boolean f1A,
                              boolean survey, boolean ssr, boolean tssr, boolean rd, boolean impldoc, boolean signedll) {
        this(null, type, sourceData, sourceRd, rns, f1A, survey, ssr, tssr, rd, impldoc, signedll);
    }

    public TypeActivityEntity(Integer id, String type, boolean sourceData, boolean sourceRd, boolean rns, boolean f1A,
                              boolean survey, boolean ssr, boolean tssr, boolean rd, boolean impldoc, boolean signedll) {
        super(id);
        this.type = type;
        this.sourceData = sourceData;
        this.sourceRd = sourceRd;
        this.rns = rns;
        this.f1A = f1A;
        this.survey = survey;
        this.ssr = ssr;
        this.tssr = tssr;
        this.rd = rd;
        this.impldoc = impldoc;
        this.signedll = signedll;
    }
}
