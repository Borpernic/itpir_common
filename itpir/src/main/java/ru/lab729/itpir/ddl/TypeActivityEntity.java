package ru.lab729.itpir.ddl;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "type_activity", schema = "public", catalog = "itpirdb")
public class TypeActivityEntity {
    private int id;
    private String type;
    private boolean sourceData;
    private boolean sourceRd;
    private boolean rns;
    private boolean f1A;
    private boolean survey;
    private boolean ssr;
    private boolean tssr;
    private boolean rd;
    private boolean impldoc;
    private boolean signedll;
    private String comments;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = false, length = -1)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "source_data", nullable = false)
    public boolean isSourceData() {
        return sourceData;
    }

    public void setSourceData(boolean sourceData) {
        this.sourceData = sourceData;
    }

    @Basic
    @Column(name = "source_rd", nullable = false)
    public boolean isSourceRd() {
        return sourceRd;
    }

    public void setSourceRd(boolean sourceRd) {
        this.sourceRd = sourceRd;
    }

    @Basic
    @Column(name = "rns", nullable = false)
    public boolean isRns() {
        return rns;
    }

    public void setRns(boolean rns) {
        this.rns = rns;
    }

    @Basic
    @Column(name = "f1a", nullable = false)
    public boolean isF1A() {
        return f1A;
    }

    public void setF1A(boolean f1A) {
        this.f1A = f1A;
    }

    @Basic
    @Column(name = "survey", nullable = false)
    public boolean isSurvey() {
        return survey;
    }

    public void setSurvey(boolean survey) {
        this.survey = survey;
    }

    @Basic
    @Column(name = "ssr", nullable = false)
    public boolean isSsr() {
        return ssr;
    }

    public void setSsr(boolean ssr) {
        this.ssr = ssr;
    }

    @Basic
    @Column(name = "tssr", nullable = false)
    public boolean isTssr() {
        return tssr;
    }

    public void setTssr(boolean tssr) {
        this.tssr = tssr;
    }

    @Basic
    @Column(name = "rd", nullable = false)
    public boolean isRd() {
        return rd;
    }

    public void setRd(boolean rd) {
        this.rd = rd;
    }

    @Basic
    @Column(name = "impldoc", nullable = false)
    public boolean isImpldoc() {
        return impldoc;
    }

    public void setImpldoc(boolean impldoc) {
        this.impldoc = impldoc;
    }

    @Basic
    @Column(name = "signedll", nullable = false)
    public boolean isSignedll() {
        return signedll;
    }

    public void setSignedll(boolean signedll) {
        this.signedll = signedll;
    }

    @Basic
    @Column(name = "comments", nullable = true, length = -1)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeActivityEntity that = (TypeActivityEntity) o;
        return id == that.id &&
                sourceData == that.sourceData &&
                sourceRd == that.sourceRd &&
                rns == that.rns &&
                f1A == that.f1A &&
                survey == that.survey &&
                ssr == that.ssr &&
                tssr == that.tssr &&
                rd == that.rd &&
                impldoc == that.impldoc &&
                signedll == that.signedll &&
                Objects.equals(type, that.type) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, sourceData, sourceRd, rns, f1A, survey, ssr, tssr, rd, impldoc, signedll, comments);
    }
}
