package Entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "USER_DOCUMENT_FIELD")
public class UserDocumentField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false)
    private long id;

    @Column(name = "VALUE",nullable = false)
    @NotNull
    private String value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_FIELD",nullable = false)
    @NotNull
    private Field field;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USER_DOCUMENT",nullable = false)
    @NotNull
    private UserDocument userDocument;

    public UserDocumentField()
    {

    }

    public UserDocumentField(@NotNull String value, @NotNull Field field, @NotNull UserDocument userDocument) {
        this.value = value;
        this.field = field;
        this.userDocument = userDocument;
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

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public UserDocument getUserDocument() {
        return userDocument;
    }

    public void setUserDocument(UserDocument userDocument) {
        this.userDocument = userDocument;
    }
}
