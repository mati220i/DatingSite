package pl.datingSite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String topic, content;
    @NotNull
    private String receiveDate;

    private boolean isReaded;

    @ManyToOne
    @JsonIgnore
    private User user;

    public Notification() {
    }

    public Notification(@NotNull String topic, @NotNull String content) {
        this.topic = topic;
        this.content = content;
        this.isReaded = false;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        this.receiveDate = format.format(new Date());

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isReaded() {
        return isReaded;
    }

    public void setReaded(boolean readed) {
        isReaded = readed;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return isReaded == that.isReaded &&
                Objects.equals(id, that.id) &&
                Objects.equals(topic, that.topic) &&
                Objects.equals(content, that.content) &&
                Objects.equals(receiveDate, that.receiveDate) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, topic, content, receiveDate, isReaded, user);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", receiveDate=" + receiveDate +
                ", isReaded=" + isReaded +
//                ", user=" + user +
                '}';
    }
}
