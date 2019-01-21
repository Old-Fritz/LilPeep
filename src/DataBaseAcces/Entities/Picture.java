package DataBaseAcces.Entities;

import lombok.Data;

import javax.validation.constraints.NotNull;

import javax.persistence.*;

@Data
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

}
