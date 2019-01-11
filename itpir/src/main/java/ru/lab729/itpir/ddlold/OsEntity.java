package ru.lab729.itpir.ddlold;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "os", schema = "public", catalog = "itpirdb")
public class OsEntity {
    private int id;
    private int projectId;
    private int orderId;
    private int pmId;
    private int osTypeId;
    private int servicesTypeId;
    private String band;
    private Integer tu;
    private Integer rns;
    private Integer forma1A;
    private Integer access;
    private Date orderDate;
    private Integer survey;
    private Integer ssr;
    private Integer tssr;
    private Integer rd;
    private Integer executiveDocumentation;
    private Date surveyDatePlan;
    private Date ssrDatePlan;
    private Date tssrDatePlan;
    private Date rdDatePlan;
    private Date idDatePlan;
    private Integer status;
    private String comments;

    @Basic
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "project_id", nullable = false)
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "order_id", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "PM_id", nullable = false)
    public int getPmId() {
        return pmId;
    }

    public void setPmId(int pmId) {
        this.pmId = pmId;
    }

    @Basic
    @Column(name = "os_type_id", nullable = false)
    public int getOsTypeId() {
        return osTypeId;
    }

    public void setOsTypeId(int osTypeId) {
        this.osTypeId = osTypeId;
    }

    @Basic
    @Column(name = "services_type_id", nullable = false)
    public int getServicesTypeId() {
        return servicesTypeId;
    }

    public void setServicesTypeId(int servicesTypeId) {
        this.servicesTypeId = servicesTypeId;
    }

    @Basic
    @Column(name = "band", nullable = true, length = -1)
    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    @Basic
    @Column(name = "TU", nullable = true)
    public Integer getTu() {
        return tu;
    }

    public void setTu(Integer tu) {
        this.tu = tu;
    }

    @Basic
    @Column(name = "RNS", nullable = true)
    public Integer getRns() {
        return rns;
    }

    public void setRns(Integer rns) {
        this.rns = rns;
    }

    @Basic
    @Column(name = "forma1a", nullable = true)
    public Integer getForma1A() {
        return forma1A;
    }

    public void setForma1A(Integer forma1A) {
        this.forma1A = forma1A;
    }

    @Basic
    @Column(name = "access", nullable = true)
    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    @Basic
    @Column(name = "order_date", nullable = false)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "survey", nullable = true)
    public Integer getSurvey() {
        return survey;
    }

    public void setSurvey(Integer survey) {
        this.survey = survey;
    }

    @Basic
    @Column(name = "SSR", nullable = true)
    public Integer getSsr() {
        return ssr;
    }

    public void setSsr(Integer ssr) {
        this.ssr = ssr;
    }

    @Basic
    @Column(name = "TSSR", nullable = true)
    public Integer getTssr() {
        return tssr;
    }

    public void setTssr(Integer tssr) {
        this.tssr = tssr;
    }

    @Basic
    @Column(name = "RD", nullable = true)
    public Integer getRd() {
        return rd;
    }

    public void setRd(Integer rd) {
        this.rd = rd;
    }

    @Basic
    @Column(name = "executive_documentation", nullable = true)
    public Integer getExecutiveDocumentation() {
        return executiveDocumentation;
    }

    public void setExecutiveDocumentation(Integer executiveDocumentation) {
        this.executiveDocumentation = executiveDocumentation;
    }

    @Basic
    @Column(name = "survey_date_plan", nullable = true)
    public Date getSurveyDatePlan() {
        return surveyDatePlan;
    }

    public void setSurveyDatePlan(Date surveyDatePlan) {
        this.surveyDatePlan = surveyDatePlan;
    }

    @Basic
    @Column(name = "SSR_date_plan", nullable = true)
    public Date getSsrDatePlan() {
        return ssrDatePlan;
    }

    public void setSsrDatePlan(Date ssrDatePlan) {
        this.ssrDatePlan = ssrDatePlan;
    }

    @Basic
    @Column(name = "TSSR_date_plan", nullable = true)
    public Date getTssrDatePlan() {
        return tssrDatePlan;
    }

    public void setTssrDatePlan(Date tssrDatePlan) {
        this.tssrDatePlan = tssrDatePlan;
    }

    @Basic
    @Column(name = "RD_date_plan", nullable = true)
    public Date getRdDatePlan() {
        return rdDatePlan;
    }

    public void setRdDatePlan(Date rdDatePlan) {
        this.rdDatePlan = rdDatePlan;
    }

    @Basic
    @Column(name = "ID_date_plan", nullable = true)
    public Date getIdDatePlan() {
        return idDatePlan;
    }

    public void setIdDatePlan(Date idDatePlan) {
        this.idDatePlan = idDatePlan;
    }

    @Basic
    @Column(name = "Status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        OsEntity osEntity = (OsEntity) o;
        return id == osEntity.id &&
                projectId == osEntity.projectId &&
                orderId == osEntity.orderId &&
                pmId == osEntity.pmId &&
                osTypeId == osEntity.osTypeId &&
                servicesTypeId == osEntity.servicesTypeId &&
                Objects.equals(band, osEntity.band) &&
                Objects.equals(tu, osEntity.tu) &&
                Objects.equals(rns, osEntity.rns) &&
                Objects.equals(forma1A, osEntity.forma1A) &&
                Objects.equals(access, osEntity.access) &&
                Objects.equals(orderDate, osEntity.orderDate) &&
                Objects.equals(survey, osEntity.survey) &&
                Objects.equals(ssr, osEntity.ssr) &&
                Objects.equals(tssr, osEntity.tssr) &&
                Objects.equals(rd, osEntity.rd) &&
                Objects.equals(executiveDocumentation, osEntity.executiveDocumentation) &&
                Objects.equals(surveyDatePlan, osEntity.surveyDatePlan) &&
                Objects.equals(ssrDatePlan, osEntity.ssrDatePlan) &&
                Objects.equals(tssrDatePlan, osEntity.tssrDatePlan) &&
                Objects.equals(rdDatePlan, osEntity.rdDatePlan) &&
                Objects.equals(idDatePlan, osEntity.idDatePlan) &&
                Objects.equals(status, osEntity.status) &&
                Objects.equals(comments, osEntity.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, projectId, orderId, pmId, osTypeId, servicesTypeId, band, tu, rns, forma1A, access, orderDate, survey, ssr, tssr, rd, executiveDocumentation, surveyDatePlan, ssrDatePlan, tssrDatePlan, rdDatePlan, idDatePlan, status, comments);
    }
}
