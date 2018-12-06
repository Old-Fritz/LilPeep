package Entities;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER_DOCUMENT")
public class UserDocument {

    /** Primary key для сущности */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** ID типа документа */
    @NotNull
    @JoinColumn(name = "ID_DOCUMENT_KIND", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private DocumentKind documentKind;

    /** ID пользователя */
    @NotNull
    @JoinColumn(name = "ID_USER", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    /** Список полей документа пользователя */
    @OneToMany(mappedBy = "userDocument", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<UserDocumentField> userDocumentFields;

    public UserDocument()
    {

    }

    /**
     * Документ пользователя
     * @param documentKind тип документа
     * @param user ID пользователя
     */
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
