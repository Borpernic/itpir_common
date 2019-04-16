package ru.lab729.itpir.model;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;
import ru.lab729.itpir.annotation.Phone;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NamedQueries({
        @NamedQuery(name = ContactsAdEntity.ALL_SORTED, query = "SELECT c FROM ContactsAdEntity c ORDER BY c.id ASC"),
        @NamedQuery(name = ContactsAdEntity.ALL_SURNAME_SORTED, query = "SELECT c FROM ContactsAdEntity c ORDER BY c.surname, c.name, c.middle_name ASC"),

        @NamedQuery(name = ContactsAdEntity.DELETE, query = "DELETE FROM ContactsAdEntity c WHERE c.id=:id"),
        @NamedQuery(name = ContactsAdEntity.DELETE_ALL, query = "DELETE FROM ContactsAdEntity c"),
        @NamedQuery(name = ContactsAdEntity.GET, query = "SELECT c FROM ContactsAdEntity c WHERE c.id=:id"),
})

@Entity
@Table(name = "contacts_ad", schema = "public", catalog = "itpirdb", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}, name = "contacts_ad_email_idx")})
public class ContactsAdEntity extends AbstractBaseWithUserEntity {

    public static final String ALL_SORTED = "ContactsAdEntity.getAllSorted";
    public static final String ALL_SURNAME_SORTED = "ContactsAdEntity.getAllSurnameSorted";
    public static final String DELETE = "ContactsAdEntity.delete";
    public static final String DELETE_ALL = "ContactsAdEntity.deleteAll";
    public static final String GET = "ContactsAdEntity.get";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "site_id", nullable = false)
    @NotNull(groups = View.Persist.class)
    private SiteEntity site;

    @Size(min = 3, max = 25)
    @SafeHtml(groups = {View.Web.class})
    @NotBlank
    @Basic
    @Column(name = "surname", nullable = false, length = 25)
    private String surname;

    @Size(min = 3, max = 25)
    @SafeHtml(groups = {View.Web.class})
    @NotBlank
    @Basic
    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Size(min = 3, max = 25)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "middle_name", nullable = true, length = 25)
    private String middle_name;

    @Size(min = 3, max = 25)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "position", nullable = true, length = 25)
    private String position;

    @Phone
    @Size(min = 11, max = 16)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "phone1", nullable = false, length = 16)
    private String phone1;


    @Size(min = 11, max = 16)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Phone
    @Column(name = "phone2", nullable = true, length = 16)
    private String phone2;

    @Email
    @Size(max = 25)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "email", nullable = true, length = 25, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status", nullable = false)
    @NotNull(groups = View.Persist.class)
    private StatusContactsEntity status;

    @Size(min = 3, max = 100)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "comments", nullable = true, length = 100)
    private String comments;

    @Basic
    @Column(name = "confirmed", nullable = false, columnDefinition = "boolean default false")
    private boolean confirmed = false;

    @Size(min = 3, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "city", nullable = true, length = 50)
    private String city;

    @Size(min = 3, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "street", nullable = true, length = 50)
    private String street;

    @Size(min = 3, max = 50)
    @SafeHtml(groups = {View.Web.class})
    @Basic
    @Column(name = "building", nullable = true, length = 50)
    private String building;

    public ContactsAdEntity() {
    }

    public ContactsAdEntity(SiteEntity site, String surname, String name, String phone1, String email,
                            StatusContactsEntity status, boolean confirmed) {
        this(null, site, surname, name, phone1, email, status, confirmed);
    }

    public ContactsAdEntity(Integer id, SiteEntity site, String surname, String name, String phone1, String email,
                            StatusContactsEntity status, boolean confirmed) {
        this.id = id;
        this.site = site;
        this.surname = surname;
        this.name = name;
        this.phone1 = phone1;
        this.email = email;
        this.status = status;
        this.confirmed = confirmed;
    }

    public ContactsAdEntity(Integer id, SiteEntity site, String surname, String name, String middle_name,
                            String position, String phone1, String phone2, String email, StatusContactsEntity status,
                            String comments, boolean confirmed, String city, String street, String building) {
        super(id);
        this.site = site;
        this.surname = surname;
        this.name = name;
        this.middle_name = middle_name;
        this.position = position;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.status = status;
        this.comments = comments;
        this.confirmed = confirmed;
        this.city = city;
        this.street = street;
        this.building = building;
    }

    public SiteEntity getSite() {
        return site;
    }

    public void setSite(SiteEntity site) {
        this.site = site;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddlename() {
        return middle_name;
    }

    public void setMiddlename(String middlename) {
        this.middle_name = middlename;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StatusContactsEntity getStatus() {
        return status;
    }

    public void setStatus(StatusContactsEntity status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
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
}
