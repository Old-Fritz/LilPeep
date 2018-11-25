package Entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FORM_DOCUMENT")
public class FormDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_DOCUMENT_KIND", nullable = false)
    private DocumentKind documentKind;

    @NotNull
    @JoinColumn(name = "ID_FORM", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private UserForm userForm;

    @NotNull
    @OneToMany(mappedBy = "formDocument", fetch =  FetchType.LAZY)
    private List<FormDocumentField> formDocumentFields;

    public FormDocument()
    {

    }

    public FormDocument(DocumentKind documentKind, UserForm userForm) {
        this.documentKind = documentKind;
        this.userForm = userForm;
    }

    public long getId() {
        return id;
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
