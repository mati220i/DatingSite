package pl.datingSite.model.messages;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class MessageBox {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Conversation> conversationList;



    public MessageBox() {
        this.conversationList = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Conversation> getConversationList() {
        return conversationList;
    }

    public void setConversationList(Set<Conversation> conversationList) {
        this.conversationList = conversationList;
    }

    @Override
    public String toString() {
        return "MessageBox{" +
                "id=" + id +
                ", conversationList=" + conversationList +
                '}';
    }
}
