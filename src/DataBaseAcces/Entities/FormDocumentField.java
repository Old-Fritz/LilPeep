package DataBaseAcces.Entities;

import lombok.Data;

import javax.validation.constraints.NotNull;

import javax.persistence.*;

@Data
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
    @JoinColumn(name = "ID_DOCUMENT", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private FormDocument formDocument;

    /** Поле документа */
    @NotNull
    @JoinColumn(name = "FIELD", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
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
}
