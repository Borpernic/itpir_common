package ru.lab729.itpir.ddl;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;
import ru.lab729.itpir.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
//Арендодатель
@Entity
@Table(name = "ad", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "ad_name_idx")})
public class AdEntity extends AbstractBaseEntity {

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    @NotBlank
    @Size(min = 2, max = 120)
    @SafeHtml(groups = {View.Web.class})
    private String name;

    @Basic
    @Column(name = "address", nullable = true, length = -1)

    @Size(min = 2, max = 120)
    @SafeHtml(groups = {View.Web.class})
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "statusAd_Id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private StatusAdEntity status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public StatusAdEntity getStatus() {
        return status;
    }

    public void setStatus(StatusAdEntity status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdEntity adEntity = (AdEntity) o;
        return id == adEntity.id &&
                Objects.equals(name, adEntity.name) &&
                Objects.equals(address, adEntity.address) &&
                Objects.equals(status, adEntity.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, address, status);
    }
}
