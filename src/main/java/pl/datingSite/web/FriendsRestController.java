package pl.datingSite.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.datingSite.model.Friends;
import pl.datingSite.services.FriendsService;

import javax.persistence.NoResultException;


@RestController
@RequestMapping(value = "/friends")
public class FriendsRestController {

    final static Logger logger = Logger.getLogger(FriendsRestController.class);

    @Autowired
    private FriendsService friendsService;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity count(@RequestParam("username") String username) {
        return new ResponseEntity(friendsService.countInvitations(username), HttpStatus.OK);
    }

    @RequestMapping(value = "/sendInvitation", method = RequestMethod.PUT)
    public ResponseEntity sendInvitation(@RequestParam("from") String from, @RequestParam("to") String to) {
        try {
            Friends friends = friendsService.sendInvitation(from, to);
            return new ResponseEntity(friends, HttpStatus.OK);
        } catch (NoResultException e) {
            logger.warn(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/undoInvitation", method = RequestMethod.PUT)
    public ResponseEntity undoInvitation(@RequestParam("from") String from, @RequestParam("to") String to) {
        try {
            Friends friends = friendsService.cancelInvitation(from, to);
            return new ResponseEntity(friends, HttpStatus.OK);
        } catch (NoResultException e) {
            logger.warn(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/cancelInvitation", method = RequestMethod.PUT)
    public ResponseEntity cancelInvitation(@RequestParam("from") String from, @RequestParam("to") String to) {
        try {
            Friends friends = friendsService.cancelInvitation(from, to);
            return new ResponseEntity(friends, HttpStatus.OK);
        } catch (NoResultException e) {
            logger.warn("Canceled failed: " + e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/acceptInvitation", method = RequestMethod.PUT)
    public ResponseEntity acceptInvitation(@RequestParam("from") String from, @RequestParam("to") String to) {
        try {
            Friends friends = friendsService.acceptInvitation(from, to);
            return new ResponseEntity(friends, HttpStatus.OK);
        } catch (NoResultException e) {
            logger.warn(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/removeFriend", method = RequestMethod.PUT)
    public ResponseEntity removeFriend(@RequestParam("who") String who, @RequestParam("from") String from) {
        try {
            Friends friends = friendsService.removeFriend(who, from);
            return new ResponseEntity(friends, HttpStatus.OK);
        } catch (NoResultException e) {
            logger.warn(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/getFriends", method = RequestMethod.GET)
    public ResponseEntity getFriends(@RequestParam("username") String username) {
        return new ResponseEntity(friendsService.getFriends(username), HttpStatus.OK);
    }
}
