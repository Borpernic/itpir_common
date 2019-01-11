package ru.lab729.itpir.ddl;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;
import ru.lab729.itpir.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "site", schema = "public", catalog = "itpirdb")
public class SiteEntity extends AbstractBaseEntity {

    public static final String ALL_SORTED = "RegionEntity.getAllSorted";
    public static final String ALL = "RegionEntity.getAll";
    public static final String DELETE = "RegionEntity.delete";
    public static final String DELETE_ALL = "RegionEntity.deleteAll";
    public static final String GET = "RegionEntity.get";
    public static final String GET_BY_COMMENTS = "RegionEntity.getByComments";

    @Basic
    @Size(max = 5)
    @SafeHtml(groups = {View.Web.class})
    @NotBlank
    @Column(name = "number", nullable = false)
    private String number;

    @Basic
    @Size(max = 50)
    @SafeHtml(groups = {View.Web.class})
    @NotBlank
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Basic
    @Column(name = "operator", nullable = false)
    private int operator;

    @Basic
    @Column(name = "region", nullable = false)
    private int region;

    @Basic
    @Column(name = "date", nullable = false, columnDefinition = "timestamp default now()")
    private Date date= new Date();

    @Basic
    @Size(max = 20)
    @SafeHtml(groups = {View.Web.class})
    @NotBlank
    @Column(name = "city", nullable = false, length = 20)
    private String city;

    @Basic
    @Size(max = 50)
    @SafeHtml(groups = {View.Web.class})
    @NotBlank
    @Column(name = "street", nullable = false, length = 50)
    private String street;

    @Basic
    @Size(max = 5)
    @SafeHtml(groups = {View.Web.class})
    @NotBlank
    @Column(name = "building", nullable = false, length = 5)
    private String building;

    @Basic
    @Size(max = 150)
    @SafeHtml(groups = {View.Web.class})
    @NotBlank
    @Column(name = "comments", nullable = false, length = 150)
    private String comments;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    @Basic
    @Column(name = "comments", nullable = false, length = -1)
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
        SiteEntity that = (SiteEntity) o;
        return id == that.id &&
                operator == that.operator &&
                region == that.region &&
                Objects.equals(number, that.number) &&
                Objects.equals(name, that.name) &&
                Objects.equals(date, that.date) &&
                Objects.equals(city, that.city) &&
                Objects.equals(street, that.street) &&
                Objects.equals(building, that.building) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, number, name, operator, region, date, city, street, building, comments);
    }*/
}
