package Entities;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SUSER")
public class User {

    /** Primary key для сущности */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    /** e-mail адрес пользователя */
    @NotNull
    @Column(name = "EMAIL", nullable = false)
    private String email;

    /** Пароль в зашифрованном виде */
    @NotNull
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    /** Тип учётной записи */
    @NotNull
    @JoinColumn(name = "ID_TYPE", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private UserKind userKind;

    /** Список настроек пользователя */
    @NotNull
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Settings> settings;

    public User()
    {

    }

    /**
     * Учётная запись пользователя
     * @param email e-mail адрес пользователя
     * @param password пароль в зашифрованном виде
     * @param userKind тип учётной записи
     */
    public User(@NotNull String email, @NotNull String password, @NotNull UserKind userKind) {
        this.email = email;
        this.password = password;
        this.userKind = userKind;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserKind getUserKind() {
        return userKind;
    }

    public void setUserKind(UserKind userKind) {
        this.userKind = userKind;
    }

    public List<Settings> getSettings() {
        return settings;
    }

    public void setSettings(List<Settings> settings) {
        this.settings = settings;
    }
}
