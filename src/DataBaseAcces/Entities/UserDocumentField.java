package DataBaseAcces.Entities;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotNull;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER_DOCUMENT_FIELD")
public class UserDocumentField {

    /** Primary key для сущности */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false)
    private long id;

    /** Значение поля */
    @Column(name = "VALUE",nullable = false)
    @NotNull
    private String value;

    /** ID поля */
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_FIELD")
    private Field field;

    /** Документ пользователя */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USER_DOCUMENT",nullable = false)
    @NotNull
    private UserDocument userDocument;

    public UserDocumentField()
    {

    }

    /**
     * Поле документа пользователя
     * @param value значение поля
     * @param field ID поля
     * @param userDocument документ пользователя
     */
    public UserDocumentField(@NotNull String value, @NotNull Field field, @NotNull UserDocument userDocument) {
        this.value = value;
        this.field = field;
        this.userDocument = userDocument;
    }
}
