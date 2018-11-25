package Entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "FIELD")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @Column(name = "ORDER", nullable = false)
    private int order;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "ID_DOCUMENT_KIND", nullable = false)
    private DocumentKind documentKind;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "ID_TYPE", nullable = false)
    private FieldType fieldType;

    public Field()
    {

    }
    public Field(@NotNull String name, @NotNull int order, @NotNull DocumentKind documentKind, @NotNull FieldType fieldType) {
        this.name = name;
        this.documentKind = documentKind;
        this.fieldType = fieldType;
        this.order = order;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public DocumentKind getDocumentKind() {
        return documentKind;
    }

    public void setDocumentKind(DocumentKind documentKind) {
        this.documentKind = documentKind;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }
}
