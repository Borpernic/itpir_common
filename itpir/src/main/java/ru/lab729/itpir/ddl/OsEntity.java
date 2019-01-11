package ru.lab729.itpir.ddl;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "os", schema = "public", catalog = "itpirdb")
public class OsEntity {
    private int id;
    private Timestamp date;
    private int site;
    private int internalNumber;
    private int curator;
    private int band;
    private int typeOs;
    private int typeBs;
    private int typeAms;
    private int typeAfs;
    private Boolean sourceData;
    private Boolean sourceRd;
    private Boolean rns;
    private Boolean f1A;
    private Boolean survey;
    private Boolean ssr;
    private Boolean tssr;
    private Boolean rd;
    private Boolean impldoc;
    private Boolean signedll;
    private int statusOs;
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
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "site", nullable = false)
    public int getSite() {
        return site;
    }

    public void setSite(int site) {
        this.site = site;
    }

    @Basic
    @Column(name = "internal_number", nullable = false)
    public int getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(int internalNumber) {
        this.internalNumber = internalNumber;
    }

    @Basic
    @Column(name = "curator", nullable = false)
    public int getCurator() {
        return curator;
    }

    public void setCurator(int curator) {
        this.curator = curator;
    }

    @Basic
    @Column(name = "band", nullable = false)
    public int getBand() {
        return band;
    }

    public void setBand(int band) {
        this.band = band;
    }

    @Basic
    @Column(name = "type_os", nullable = false)
    public int getTypeOs() {
        return typeOs;
    }

    public void setTypeOs(int typeOs) {
        this.typeOs = typeOs;
    }

    @Basic
    @Column(name = "type_bs", nullable = false)
    public int getTypeBs() {
        return typeBs;
    }

    public void setTypeBs(int typeBs) {
        this.typeBs = typeBs;
    }

    @Basic
    @Column(name = "type_ams", nullable = false)
    public int getTypeAms() {
        return typeAms;
    }

    public void setTypeAms(int typeAms) {
        this.typeAms = typeAms;
    }

    @Basic
    @Column(name = "type_afs", nullable = false)
    public int getTypeAfs() {
        return typeAfs;
    }

    public void setTypeAfs(int typeAfs) {
        this.typeAfs = typeAfs;
    }

    @Basic
    @Column(name = "source_data", nullable = true)
    public Boolean getSourceData() {
        return sourceData;
    }

    public void setSourceData(Boolean sourceData) {
        this.sourceData = sourceData;
    }

    @Basic
    @Column(name = "source_rd", nullable = true)
    public Boolean getSourceRd() {
        return sourceRd;
    }

    public void setSourceRd(Boolean sourceRd) {
        this.sourceRd = sourceRd;
    }

    @Basic
    @Column(name = "rns", nullable = true)
    public Boolean getRns() {
        return rns;
    }

    public void setRns(Boolean rns) {
        this.rns = rns;
    }

    @Basic
    @Column(name = "f1a", nullable = true)
    public Boolean getF1A() {
        return f1A;
    }

    public void setF1A(Boolean f1A) {
        this.f1A = f1A;
    }

    @Basic
    @Column(name = "survey", nullable = true)
    public Boolean getSurvey() {
        return survey;
    }

    public void setSurvey(Boolean survey) {
        this.survey = survey;
    }

    @Basic
    @Column(name = "ssr", nullable = true)
    public Boolean getSsr() {
        return ssr;
    }

    public void setSsr(Boolean ssr) {
        this.ssr = ssr;
    }

    @Basic
    @Column(name = "tssr", nullable = true)
    public Boolean getTssr() {
        return tssr;
    }

    public void setTssr(Boolean tssr) {
        this.tssr = tssr;
    }

    @Basic
    @Column(name = "rd", nullable = true)
    public Boolean getRd() {
        return rd;
    }

    public void setRd(Boolean rd) {
        this.rd = rd;
    }

    @Basic
    @Column(name = "impldoc", nullable = true)
    public Boolean getImpldoc() {
        return impldoc;
    }

    public void setImpldoc(Boolean impldoc) {
        this.impldoc = impldoc;
    }

    @Basic
    @Column(name = "signedll", nullable = true)
    public Boolean getSignedll() {
        return signedll;
    }

    public void setSignedll(Boolean signedll) {
        this.signedll = signedll;
    }

    @Basic
    @Column(name = "status_os", nullable = false)
    public int getStatusOs() {
        return statusOs;
    }

    public void setStatusOs(int statusOs) {
        this.statusOs = statusOs;
    }

    @Basic
    @Column(name = "comments", nullable = false, length = -1)
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
        OsEntity osEntity = (OsEntity) o;
        return id == osEntity.id &&
                site == osEntity.site &&
                internalNumber == osEntity.internalNumber &&
                curator == osEntity.curator &&
                band == osEntity.band &&
                typeOs == osEntity.typeOs &&
                typeBs == osEntity.typeBs &&
                typeAms == osEntity.typeAms &&
                typeAfs == osEntity.typeAfs &&
                statusOs == osEntity.statusOs &&
                Objects.equals(date, osEntity.date) &&
                Objects.equals(sourceData, osEntity.sourceData) &&
                Objects.equals(sourceRd, osEntity.sourceRd) &&
                Objects.equals(rns, osEntity.rns) &&
                Objects.equals(f1A, osEntity.f1A) &&
                Objects.equals(survey, osEntity.survey) &&
                Objects.equals(ssr, osEntity.ssr) &&
                Objects.equals(tssr, osEntity.tssr) &&
                Objects.equals(rd, osEntity.rd) &&
                Objects.equals(impldoc, osEntity.impldoc) &&
                Objects.equals(signedll, osEntity.signedll) &&
                Objects.equals(comments, osEntity.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, date, site, internalNumber, curator, band, typeOs, typeBs, typeAms, typeAfs, sourceData, sourceRd, rns, f1A, survey, ssr, tssr, rd, impldoc, signedll, statusOs, comments);
    }
}
