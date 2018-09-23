package pl.datingSite.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.datingSite.model.Notification;
import pl.datingSite.services.NotificationService;

@RestController
@RequestMapping(value = "/notification")
public class NotificationRestController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity countUserNotification(@RequestParam("username") String username) {
        return new ResponseEntity(notificationService.countUnreadedUserNotification(username), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity getUserNotification(@RequestParam("username") String username) {
        return new ResponseEntity(notificationService.getUserNotifications(username), HttpStatus.OK);
    }

    @RequestMapping(value = "/read", method = RequestMethod.PUT)
    public ResponseEntity readNotification(@RequestParam("id") Long id) {
        notificationService.readNotification(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteNotification(@RequestBody Notification notification) {
        notificationService.deleteNotification(notification);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/newWave", method = RequestMethod.PUT)
    public ResponseEntity newWaveNotification(@RequestParam("from") String from, @RequestParam("to") String to) {
        notificationService.newWaveNotification(from, to);
        return new ResponseEntity(HttpStatus.OK);
    }
}
