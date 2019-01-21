package Entities;

import lombok.Data;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
@Data
@Entity
@Table(name = "FIELD_TYPE")
public class FieldType {

    /** Primary key длясущности */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    /** название типа поля */
    @Column(name = "NAME", nullable = false, unique = true)
    @NotNull
    private String name;

    public FieldType()
    {

    }

    /**
     * Тип поля документа
     * @param name название типа
     */
    public FieldType(@NotNull String name) {
        this.name = name;
    }
}
