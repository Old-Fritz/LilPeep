package DataBaseAcces.Entities;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.List;

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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_DOCUMENT_KIND")
    private DocumentKind documentKind;

    /** Тип поля */
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_TYPE")
    private FieldType fieldType;

    @OneToMany(mappedBy = "field", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<FormDocumentField> formDocumentFields;

    @OneToMany(mappedBy = "field", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<FormDocumentField> userDocumentFields;


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
