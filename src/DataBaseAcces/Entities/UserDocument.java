package DataBaseAcces.Entities;

import lombok.Data;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.List;

@Data
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

}
