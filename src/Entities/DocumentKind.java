package Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class DocumentKind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int fieldsCount;
    private String name;
    private String description;
    private Picture picture;

    private List<Field> fields;

    public DocumentKind()
    {

    }

    public DocumentKind(int fieldsCount, String name, String description, Picture picture) {
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
