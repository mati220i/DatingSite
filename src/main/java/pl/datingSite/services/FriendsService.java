package pl.datingSite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.datingSite.model.Friends;
import pl.datingSite.model.User;
import pl.datingSite.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Set;

@Service
public class FriendsService {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Integer countInvitations(String username) {
        User user = userRepository.getUserByUserName(username);
        Set<String> invitations = user.getFriends().getInvitationsFrom();

        if(invitations != null)
            return new Long(invitations.stream().count()).intValue();
        else
            return 0;
    }

    @Transactional
    public Friends sendInvitation(String from, String to) {
        User userFrom = userRepository.getUserByUserName(from);
        User userTo = userRepository.getUserByUserName(to);

        userFrom.getFriends().getSendInvitations().add(to);
        userTo.getFriends().getInvitationsFrom().add(from);

        entityManager.merge(userFrom);
        entityManager.merge(userTo);

        Friends friends = userFrom.getFriends();

        if(friends != null)
            return friends;
        else
            throw new NoResultException("Not found friends");
    }

    @Transactional
    public Friends cancelInvitation(String from, String to) {
        User userFrom = userRepository.getUserByUserName(from);
        User userTo =  userRepository.getUserByUserName(to);

        userFrom.getFriends().getSendInvitations().remove(to);
        userTo.getFriends().getInvitationsFrom().remove(from);

        entityManager.merge(userFrom);
        entityManager.merge(userTo);

        Friends friends = userFrom.getFriends();

        if(friends != null)
            return friends;
        else
            throw new NoResultException("Not found friends");
    }

    @Transactional
    public Friends acceptInvitation(String from, String to) {
        User userFrom = userRepository.getUserByUserName(from);
        User userTo = userRepository.getUserByUserName(to);

        userFrom.getFriends().getSendInvitations().remove(to);
        userTo.getFriends().getInvitationsFrom().remove(from);

        userFrom.getFriends().getFriendsUsernames().add(to);
        userTo.getFriends().getFriendsUsernames().add(from);

        notificationService.newAcceptFriendNotification(from, to);
        entityManager.merge(userFrom);
        entityManager.merge(userTo);

        Friends friends = userTo.getFriends();

        if(friends != null)
            return friends;
        else
            throw new NoResultException("Not found friends");
    }

    @Transactional
    public Friends removeFriend(String who, String from) {
        User userFrom = userRepository.getUserByUserName(from);
        User userWho = userRepository.getUserByUserName(who);

        userFrom.getFriends().getFriendsUsernames().remove(who);
        userWho.getFriends().getFriendsUsernames().remove(from);

        notificationService.newRemoveFriendNotification(who, from);
        entityManager.merge(userFrom);
        entityManager.merge(userWho);

        Friends friends = userFrom.getFriends();
        if(friends != null)
            return friends;
        else
            throw new NoResultException("Not found friends");
    }

    public Friends getFriends(String username) {
        return userRepository.getUserByUserNameWithFriends(username).getFriends();
    }

}
