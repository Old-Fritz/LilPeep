package Entities;

import javax.validation.constraints.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "FORM_DOCUMENT_FIELD")
public class FormDocumentField {
    /** Primary key для сущности */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** Форма документа */
    @NotNull
    @JoinColumn(name = "ID_FORM", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private FormDocument formDocument;

    /** Поле документа */
    @NotNull
    @JoinColumn(name = "FIELD", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Field field;

    public FormDocumentField()
    {

    }

    /**
     * Поле документа в форме
     * @param formDocument форма документа
     * @param field поле документа
     */
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
