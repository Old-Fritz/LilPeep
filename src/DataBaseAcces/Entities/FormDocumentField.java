package DataBaseAcces.Entities;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @JoinColumn(name = "ID_DOCUMENT")
    @ManyToOne(fetch = FetchType.EAGER)
    private FormDocument formDocument;

    /** Поле документа */
    @JoinColumn(name = "FIELD")
    @OnDelete(action = OnDeleteAction.CASCADE)
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
}
