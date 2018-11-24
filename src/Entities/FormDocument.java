package Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class FormDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private DocumentKind documentKind;
    private UserForm userForm;

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
