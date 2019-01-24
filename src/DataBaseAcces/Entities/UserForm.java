package DataBaseAcces.Entities;

import lombok.Data;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "USER_FORM")
public class UserForm {
    /** Primary key для сущности */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** Количество документов */
    @NotNull
    @Column(name = "DOCUMENT_COUNT", nullable = false)
    private int documentCount;

    /** Название формы */
    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    /** URL формы */
    @NotNull
    @Column(name = "URL", nullable = false)
    private String url;

    /** Порядковый номер */
    @NotNull
    @Column(name = "OBJ_ORDER", nullable = false)
    private int order;

    /** Пользователь */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USER", nullable = false)
    private User user;

    /** Список форм ползователя */
    @OneToMany(mappedBy = "userForm", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<FormDocument> formDocuments;

    public UserForm()
    {

    }

    /**
     * Форма пользователя
     * @param documentCount количество документов
     * @param name название формы
     * @param url URL формы
     * @param order порядковый номер
     * @param user пользователь
     */
    public UserForm(@NotNull int documentCount, @NotNull String name, @NotNull String url, @NotNull int order, @NotNull User user) {
        this.documentCount = documentCount;
        this.name = name;
        this.url = url;
        this.user = user;
        this.order = order;
    }
}
