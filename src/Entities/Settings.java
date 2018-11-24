package Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String value;
    private User user;
    private SettingsType type;

    public Settings()
    {

    }

    public Settings(String value, User user, SettingsType type) {
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
