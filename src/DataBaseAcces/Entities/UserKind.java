package DataBaseAcces.Entities;

import lombok.Data;

import javax.validation.constraints.NotNull;

import javax.persistence.*;

/** Primary key для сущности */
@Data
@Entity
@Table(name = "USER_KIND")
public class UserKind {

    /** Primary key для сущности */
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** Тип учётной записи, может быть администратором, владельцем сайта или обычным пользователем*/
    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @Column(name = "OPENSSO_REALM", nullable = false)
    private String openSSORealm;

    @NotNull
    @Column(name = "URL_PATTERN", nullable = false)
    private String urlPattern;

    public UserKind()
    {

    }

    /**
     * Возможные типы пользователей
     * @param name тип учётной записи, может быть администратором, владельцем сайта или обычным пользователем
     * @param openSSORealm название Realm в OpenAM
     * @param urlPattern паттерн страниц пользователя в приложении
     */
    public UserKind(@NotNull String name, @NotNull String openSSORealm, @NotNull String urlPattern) {
        this.name = name;
        this.openSSORealm = openSSORealm;
        this.urlPattern = urlPattern;
    }
}
