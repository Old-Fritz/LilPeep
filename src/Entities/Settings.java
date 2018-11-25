package Entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "SETTINGS")
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @NotNull
    @Column(name = "VALUE", nullable = false)
    private String value;

    @NotNull
    @Column(name = "ID_USER", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @NotNull
    @Column(name = "ID_SETTINGS_TYPE", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private SettingsType type;

    public Settings()
    {

    }

    public Settings(@NotNull String value, @NotNull User user, @NotNull SettingsType type) {
        this.value = value;
        this.user = user;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SettingsType getType() {
        return type;
    }

    public void setType(SettingsType type) {
        this.type = type;
    }
}
