package ru.lab729.itpir.ddl;

import org.hibernate.validator.constraints.SafeHtml;
import ru.lab729.itpir.View;
import ru.lab729.itpir.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "activity_section",  uniqueConstraints = {@UniqueConstraint(columnNames = {"section"}, name = "activity_section_section_idx")})
public class ActivitySectionEntity extends AbstractBaseEntity {


    @Basic
    @Column(name = "section", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    @SafeHtml(groups = {View.Web.class})
    private String section;

    @Basic
    @Column(name = "comments", nullable = false)
    @Size(min = 2, max = 120)
    @SafeHtml(groups = {View.Web.class})
    private String comments;


    public String getSection() {
        return section;
    }

    public void setSection(String activity) {
        this.section = activity;
    }


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
        ActivitySectionEntity that = (ActivitySectionEntity) o;
        return id == that.id &&
                Objects.equals(section, that.section) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, section, comments);
    }
}
