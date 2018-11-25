package Entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "FORM_DOCUMENT_FIELD")
public class FormDocumentField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @JoinColumn(name = "ID_FORM", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private FormDocument formDocument;

    @NotNull
    @JoinColumn(name = "FIELD", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Field field;

    public FormDocumentField()
    {

    }

    public FormDocumentField(@NotNull FormDocument formDocument, @NotNull Field field) {
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
