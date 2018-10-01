package pl.datingSite.model;

import javax.persistence.*;
import java.util.HashSet;
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

    @ElementCollection
    private Set<String> sendInvitations;

    public Friends() {
        this.friendsUsernames = new HashSet<>();
        this.invitationsFrom = new HashSet<>();
        this.sendInvitations = new HashSet<>();
    }

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

    public Set<String> getSendInvitations() {
        return sendInvitations;
    }

    public void setSendInvitations(Set<String> sendInvitations) {
        this.sendInvitations = sendInvitations;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "id=" + id +
                ", friendsUsernames=" + friendsUsernames +
                ", invitationsFrom=" + invitationsFrom +
                ", sendInvitations=" + sendInvitations +
                '}';
    }
}
