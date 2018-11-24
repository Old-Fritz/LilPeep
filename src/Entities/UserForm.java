package Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class UserForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int documentCount;
    private String name;
    private String url;
    private User user;

    private List<FormDocument> formDocuments;

    public UserForm()
    {

    }

    public UserForm(int documentCount, String name, String url, User user) {
        this.documentCount = documentCount;
        this.name = name;
        this.url = url;
        this.user = user;
    }

    public long getId() {
        return id;
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
