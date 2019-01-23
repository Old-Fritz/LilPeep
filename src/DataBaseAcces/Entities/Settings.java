package DataBaseAcces.Entities;

import lombok.Data;

import javax.validation.constraints.NotNull;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SETTINGS")
public class Settings {
    /** Primary key для сущности */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    /** Значение настройки */
    @NotNull
    @Column(name = "VALUE", nullable = false)
    private String value;

    /** Порядковый номер */
    @NotNull
    @Column(name = "OBJ_ORDER", nullable = false)
    private int order;

    /** ID пользователя */
    @NotNull
    @JoinColumn(name = "ID_USER", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    /** ID типа настройки */
    @NotNull
    @JoinColumn(name = "ID_SETTINGS_TYPE", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private SettingsType type;

    public Settings()
    {

    }

    /**
     * Пункт в настройках
     * @param value значение параметра
     * @param order порядковый номер
     * @param user ID пользователя
     * @param type ID типа настройки
     */
    public Settings(@NotNull String value, @NotNull int order, @NotNull User user, @NotNull SettingsType type) {
        this.value = value;
        this.order = order;
        this.user = user;
        this.type = type;
    }
}
