package DataBaseAcces.Entities;

import lombok.Data;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "COMPLAINT")
public class Complaint {

    /** Primary key для сущности */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    /** Дата подачи жалобы */
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE", nullable = false)
    private Date date;

    /** Содержание жалобы */
    @NotNull
    @Column(name = "TEXT", nullable = false, columnDefinition = "TEXT")
    private String text;

    /** Пользователь, отправивший жалобу */
    @NotNull
    @JoinColumn(name = "ID_SENDER", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User sender;

    /** Пользователь, на которого отправлена жалоба*/
    @NotNull
    @JoinColumn(name = "ID_USER", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Complaint() {

    }

    /**
     * Жалоба пользователя
     * @param date дата подачи жалобы
     * @param text содержание жалобы
     * @param sender пользователь, отправивший жалабу
     * @param user пользователь, на которого отправлена жалоба
     */
    public Complaint(@NotNull Date date, @NotNull String text, @NotNull User sender, @NotNull User user) {
        this.date = date;
        this.text = text;
        this.sender = sender;
        this.user = user;
    }
}
