package DataBaseAcces.Entities;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "SUSER")
public class User {

    /**
     * Primary key для сущности
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    /**
     * e-mail адрес пользователя
     */
    @NotNull
    @Column(name = "EMAIL", nullable = false)
    private String email;

    /**
     * Пароль в зашифрованном виде
     */
    @NotNull
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    /**
     * Тип учётной записи
     */
    @NotNull
    @JoinColumn(name = "ID_TYPE", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private UserKind userKind;

    /**
     * Список настроек пользователя
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Settings> settings;

    public User() {

    }

    /**
     * Учётная запись пользователя
     *
     * @param email    e-mail адрес пользователя
     * @param password пароль в зашифрованном виде
     * @param userKind тип учётной записи
     */
    public User(@NotNull String email, @NotNull String password, @NotNull UserKind userKind) {
        this.email = email;
        this.password = password;
        this.userKind = userKind;
    }

}
