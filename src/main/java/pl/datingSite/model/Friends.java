package pl.datingSite.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    private Set<String> friendsUsernames;

    @ElementCollection
    private Set<String> invitationsFrom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<String> getFriendsUsernames() {
        return friendsUsernames;
    }

    public void setFriendsUsernames(Set<String> friendsUsernames) {
        this.friendsUsernames = friendsUsernames;
    }

    public Set<String> getInvitationsFrom() {
        return invitationsFrom;
    }

    public void setInvitationsFrom(Set<String> invitationsFrom) {
        this.invitationsFrom = invitationsFrom;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "id=" + id +
                ", friendsUsernames=" + friendsUsernames +
                ", invitationsFrom=" + invitationsFrom +
                '}';
    }
}
