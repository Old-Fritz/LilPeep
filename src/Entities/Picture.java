package Entities;

import javax.validation.constraints.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "PICTURE")
public class Picture {
    /** Primary key для сущности */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    /** URL картинки */
    @Column(name = "URL", nullable = false, unique = true)
    @NotNull
    private String url;

    public Picture()
    {

    }

    /**
     * Картинка для документа
     * @param url URL картинки
     */
    public Picture(@NotNull String url) {
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
