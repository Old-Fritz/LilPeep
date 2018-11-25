package Entities;

import javax.validation.constraints.NotNull;

import javax.persistence.*;

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

    public UserKind()
    {

    }

    /**
     * Возможные типы пользователей
     * @param name тип учётной записи, может быть администратором, владельцем сайта или обычным пользователем
     */
    public UserKind(@NotNull String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
