package Entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DOCUMENT_KIND")
public class DocumentKind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @NotNull
    @Column(name = "FIELD_COUNT", nullable = false)
    private int fieldsCount;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PICTURE", nullable = false)
    private Picture picture;

    @NotNull
    @OneToMany(mappedBy = "documentKind", fetch = FetchType.LAZY)
    private List<Field> fields;

    public DocumentKind()
    {

    }

    public DocumentKind(@NotNull int fieldsCount, @NotNull String name, @NotNull String description, @NotNull Picture picture) {
        this.fieldsCount = fieldsCount;
        this.name = name;
        this.description = description;
        this.picture = picture;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFieldsCount() {
        return fieldsCount;
    }

    public void setFieldsCount(int fieldsCount) {
        this.fieldsCount = fieldsCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
