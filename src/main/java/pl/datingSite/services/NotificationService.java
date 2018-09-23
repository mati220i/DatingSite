package pl.datingSite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.datingSite.model.Notification;
import pl.datingSite.model.User;
import pl.datingSite.repository.NotificationRepository;
import pl.datingSite.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
