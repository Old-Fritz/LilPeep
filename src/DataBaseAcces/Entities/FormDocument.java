package DataBaseAcces.Entities;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name = "FORM_DOCUMENT")
public class FormDocument {

    /** Primary key для сущности */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    /** Порядковый номер */
    @NotNull
    @Column(name = "OBJ_ORDER", nullable = false)
    private int order;

    /** Тип документа */
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_DOCUMENT_KIND")
    private DocumentKind documentKind;

    /** Форма владельца */
    @JoinColumn(name = "ID_FORM")
    @ManyToOne(fetch = FetchType.EAGER)
    private UserForm userForm;

    /** Список полей документа в форме */
    @OneToMany(mappedBy = "formDocument", fetch =  FetchType.EAGER,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<FormDocumentField> formDocumentFields;


    public FormDocument()
    {

    }

    /**
     * Форма документа
     * @param order порядковый номер
     * @param documentKind тип документа
     * @param userForm форма владельца
     */
    public FormDocument(@NotNull int order, @NotNull DocumentKind documentKind, @NotNull UserForm userForm) {
        this.order = order;
        this.documentKind = documentKind;
        this.userForm = userForm;
        this.formDocumentFields = new ArrayList<>();
    }
}
