package Entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private DocumentKind documentKind;
    private User user;

    private List<UserDocumentField> userDocumentFields;

    public UserDocument()
    {

    }

    public UserDocument(DocumentKind documentKind, User user) {
        this.documentKind = documentKind;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserDocumentField> getUserDocumentFields() {
        return userDocumentFields;
    }

    public void setUserDocumentFields(List<UserDocumentField> userDocumentFields) {
        this.userDocumentFields = userDocumentFields;
    }
}
