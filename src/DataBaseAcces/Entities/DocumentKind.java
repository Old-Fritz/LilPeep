package DataBaseAcces.Entities;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name = "DOCUMENT_KIND")
public class DocumentKind {

    /** Primary key для сущности*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    /** Количество полей в документе */
    @NotNull
    @Column(name = "FIELD_COUNT", nullable = false)
    private int fieldsCount;

    /** Название документа */
    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    /** Описание документа */
    @NotNull
    @Column(name = "DESCRIPTION", nullable = false, columnDefinition = "TEXT")
    private String description;

    /** Порядковый номер документа */
    @NotNull
    @Column(name = "OBJ_ORDER", nullable = false)
    private int order;

    /** ID URL картинки документа */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PICTURE")
    private Picture picture;

    /** Поля документа */
    @OneToMany(mappedBy = "documentKind", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Field> fields;

    public DocumentKind()
    {

    }

    /**
     * Тип документа
     * @param fieldsCount количество полей документа
     * @param name название документа
     * @param description описание документа
     * @param order порядковый номер документа
     * @param picture ID URL картинки документа
     */
    public DocumentKind(@NotNull int fieldsCount, @NotNull String name, @NotNull String description, @NotNull int order, Picture picture) {
        this.fieldsCount = fieldsCount;
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.order = order;
        this.fields = new ArrayList<>();
    }


}
