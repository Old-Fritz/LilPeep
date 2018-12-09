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

    @NotNull
    @Column(name="IS_CHECKED", nullable = false)
    private boolean isChecked;

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
     * @param isChecked присутствие элемента
     */
    public FormDocumentField(@NotNull FormDocument formDocument, @NotNull Field field, @NotNull boolean isChecked) {
        this.formDocument = formDocument;
        this.field = field;
        this.isChecked = isChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
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
