package pl.datingSite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.datingSite.model.Notification;
import pl.datingSite.model.User;
import pl.datingSite.repository.NotificationRepository;
import pl.datingSite.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.Set;

@Service
public class NotificationService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public void sendFirstNotification(User user) {
        String topic = "Witaj w systemie";
        String content = "Witaj w systemie. Od teraz możesz korzystać z funkcjonalności. Warto jednak dla celniejszego dopasowania" +
                " wypełnić swój profil wszystkimi możliwymi informacjami. Przejdź do Ustawienia > Profil";
        Notification notification = new Notification(topic, content);
        notification.setUser(user);
        entityManager.merge(notification);
    }

    public Integer countUnreadedUserNotification(String username) {
        Set<Notification> list = userRepository.getUserByUserName(username).getNotifications();
        Iterator<Notification> iterator = list.iterator();

        Integer quantity = 0;
        while (iterator.hasNext()) {
            if (!iterator.next().isReaded())
                quantity++;
        }
        return quantity;
    }

    public Set<Notification> getUserNotifications(String username) {
        return userRepository.getUserByUserName(username).getNotifications();
    }

    public void readNotification(Long id) {
        notificationRepository.readNotificaction(id);
    }

    public void deleteNotification(Notification notification) {
        notificationRepository.delete(notification);
    }

    @Transactional
    public void newWaveNotification(String from, String to) {
        User userFrom = userRepository.getUserByUserName(from);
        User userTo = userRepository.getUserByUserName(to);

        String topic = "Użytkownik " + userFrom.getName() + " (" + userFrom.getUsername() + ") pomachał Ci";
        String content = "Użytkownik o imieniu " + userFrom.getName() + " '" + userFrom.getUsername() + "' pomachał Ci. " +
                "Ty też możesz to zrobic wchodząc na jego profil a następnie klikając przycisk \"łapki\".";

        Notification notification = new Notification(topic, content);
        newNotification(notification, userTo);
    }

    @Transactional
    public void newKissNotification(String from, String to) {
        User userFrom = userRepository.getUserByUserName(from);
        User userTo = userRepository.getUserByUserName(to);

        String topic = "Użytkownik " + userFrom.getName() + " (" + userFrom.getUsername() + ") wysłał Ci całusa";
        String content = "Użytkownik o imieniu " + userFrom.getName() + " '" + userFrom.getUsername() + "' wysłał Ci całusa. " +
                "Ty też możesz to zrobic wchodząc na jego profil a następnie klikając przycisk \"ust\".";

        Notification notification = new Notification(topic, content);
        newNotification(notification, userTo);
    }

    @Transactional
    public void newAcceptFriendNotification(String from, String to) {
        User userFrom = userRepository.getUserByUserName(from);
        User userTo = userRepository.getUserByUserName(to);

        String content = "Użytkownik o imieniu " + userTo.getName() + " '" + userTo.getUsername() + "' zaakceptował zaproszenie do znajomych. " +
                "Od teraz będziesz mieć łatwy dostęp do tego użytkownika z panelu znajomych.";
        String topic = "Użytkownik " + userTo.getName() + " (" + userTo.getUsername() + ") zaakceptował zaproszenie do znajomych";

        Notification notification = new Notification(topic, content);
        newNotification(notification, userFrom);
    }

    @Transactional
    public void newRemoveFriendNotification(String from, String to) {
        User userTo = userRepository.getUserByUserName(to);
        User userFrom = userRepository.getUserByUserName(from);

        String content = "Użytkownik o imieniu " + userTo.getName() + " '" + userTo.getUsername() + "' usunął Cię ze znajomych. " +
                "Bardzo nam przykro z tego powodu.";
        String topic = "Użytkownik " + userTo.getName() + " (" + userTo.getUsername() + ") usunął Cię ze znajomych";

        Notification notification = new Notification(topic, content);
        newNotification(notification, userFrom);
    }

    @Transactional
    public void newNotification(Notification notification, User user) {
        notification.setUser(user);
        entityManager.merge(notification);
    }
}
