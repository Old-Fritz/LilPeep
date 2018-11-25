package Entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER_DOCUMENT")
public class UserDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @JoinColumn(name = "ID_DOCUMENT_KIND", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private DocumentKind documentKind;

    @NotNull
    @JoinColumn(name = "ID_USER", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @NotNull
    @OneToMany(mappedBy = "userDocument", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<UserDocumentField> userDocumentFields;

    public UserDocument()
    {

    }

    public UserDocument(@NotNull DocumentKind documentKind, @NotNull User user) {
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
