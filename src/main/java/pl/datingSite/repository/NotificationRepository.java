package pl.datingSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.datingSite.model.Notification;

import javax.transaction.Transactional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Transactional
    @Modifying
    @Query(value = "update Notification n set n.is_readed = 1 where n.id = :notificationId", nativeQuery = true)
    public void readNotificaction(@Param(value = "notificationId") Long id);
}
