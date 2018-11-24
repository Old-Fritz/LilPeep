package Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FormDocumentField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private FormDocument formDocument;
    private Field field;

    public FormDocumentField()
    {

    }

    public FormDocumentField(FormDocument formDocument, Field field) {
        this.formDocument = formDocument;
        this.field = field;
    }

    public long getId() {
        return id;
    }

    public FormDocument getFormDocument() {
        return formDocument;
    }

    public void setFormDocument(FormDocument formDocument) {
        this.formDocument = formDocument;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
