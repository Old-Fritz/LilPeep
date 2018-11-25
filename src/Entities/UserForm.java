package Entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER_FORM")
public class UserForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "DOCUMENT_COUNT", nullable = false)
    private int documentCount;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @Column(name = "URL", nullable = false)
    private String url;

    @NotNull
    @Column(name = "ORDER", nullable = false)
    private int order;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USER", nullable = false)
    private User user;

    @NotNull
    @OneToMany(mappedBy = "userForm", fetch = FetchType.LAZY)
    private List<FormDocument> formDocuments;

    public UserForm()
    {

    }

    public UserForm(@NotNull int documentCount, @NotNull String name, @NotNull String url, @NotNull int order, @NotNull User user) {
        this.documentCount = documentCount;
        this.name = name;
        this.url = url;
        this.user = user;
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getDocumentCount() {
        return documentCount;
    }

    public void setDocumentCount(int documentCount) {
        this.documentCount = documentCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<FormDocument> getFormDocuments() {
        return formDocuments;
    }

    public void setFormDocuments(List<FormDocument> formDocuments) {
        this.formDocuments = formDocuments;
    }
}
