package Entities;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMPLAINT")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @NotNull
    @Column(name = "DATE", nullable = false)
    private Date date;

    @NotNull
    @Column(name = "TEXT", nullable = false)
    private String text;

    @NotNull
    @JoinColumn(name = "ID_SENDER", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User sender;

    @NotNull
    @JoinColumn(name = "ID_USER", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Complaint() {

    }

    public Complaint(@NotNull Date date, @NotNull String text, @NotNull User sender, @NotNull User user) {
        this.date = date;
        this.text = text;
        this.sender = sender;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
