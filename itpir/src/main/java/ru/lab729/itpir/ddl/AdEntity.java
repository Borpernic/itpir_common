package ru.lab729.itpir.ddl;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ad", schema = "public", catalog = "itpirdb")
public class AdEntity {
    private int id;
    private String name;
    private String address;
    private Integer status;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "adress", nullable = true, length = -1)
    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
