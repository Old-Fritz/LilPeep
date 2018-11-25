package Entities;

import javax.validation.constraints.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "SETTINGS_TYPE")
public class SettingsType {
    /** Primary key для сущности */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    /** Название типа настройки */
    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    public SettingsType()
    {

    }

    /**
     * Тип настройки
     * @param name название типа настройки
     */
    public SettingsType(@NotNull String name) {
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
