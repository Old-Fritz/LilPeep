package Entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SUSER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @NotNull
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NotNull
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @NotNull
    @JoinColumn(name = "ID_TYPE", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private UserKind userKind;

    @NotNull
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Settings> settings;

    public User()
    {

    }

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
