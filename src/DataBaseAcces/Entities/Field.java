package DataBaseAcces.Entities;

import lombok.Data;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
@Data
@Entity
@Table(name = "FIELD")
public class Field {
    /** Primary key для сущности */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    /** Название поля */
    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    /** Порядок поля в документе */
    @NotNull
    @Column(name = "OBJ_ORDER", nullable = false)
    private int order;

    /** Тип документа */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_DOCUMENT_KIND", nullable = false)
    private DocumentKind documentKind;

    /** Тип поля */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_TYPE", nullable = false)
    private FieldType fieldType;

    public Field()
    {

    }

    /**
     * Поле документа
     * @param name название поля
     * @param order порядок поля
     * @param documentKind тип документа
     * @param fieldType тип поля
     */
    public Field(@NotNull String name, @NotNull int order, @NotNull DocumentKind documentKind, @NotNull FieldType fieldType) {
        this.name = name;
        this.documentKind = documentKind;
        this.fieldType = fieldType;
        this.order = order;
    }
}
