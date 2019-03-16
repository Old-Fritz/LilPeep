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
@Table(name = "USER_DOCUMENT")
public class UserDocument {

    /** Primary key для сущности */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** ID типа документа */
    @JoinColumn(name = "ID_DOCUMENT_KIND")
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DocumentKind documentKind;

    /** ID пользователя */
    @NotNull
    @JoinColumn(name = "ID_USER", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    /** Список полей документа пользователя */
    @OneToMany(mappedBy = "userDocument", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
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
        this.userDocumentFields = new ArrayList<>();
    }

}
