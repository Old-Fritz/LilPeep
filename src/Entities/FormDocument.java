package Entities;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.List;

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
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_DOCUMENT_KIND", nullable = false)
    private DocumentKind documentKind;

    /** Форма владельца */
    @NotNull
    @JoinColumn(name = "ID_FORM", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private UserForm userForm;

    /** Список полей документа в форме */
    @OneToMany(mappedBy = "formDocument", fetch =  FetchType.LAZY,cascade = CascadeType.ALL)
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
    }

    public long getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public DocumentKind getDocumentKind() {
        return documentKind;
    }

    public void setDocumentKind(DocumentKind documentKind) {
        this.documentKind = documentKind;
    }

    public UserForm getUserForm() {
        return userForm;
    }

    public void setUserForm(UserForm userForm) {
        this.userForm = userForm;
    }

    public List<FormDocumentField> getFormDocumentFields() {
        return formDocumentFields;
    }

    public void setFormDocumentFields(List<FormDocumentField> formDocumentFields) {
        this.formDocumentFields = formDocumentFields;
    }
}
