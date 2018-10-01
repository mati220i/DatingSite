package pl.datingSite.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.datingSite.model.Friends;
import pl.datingSite.services.FriendsService;


@RestController
@RequestMapping(value = "/friends")
public class FriendsRestController {

    @Autowired
    private FriendsService friendsService;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity count(@RequestParam("username") String username) {
        return new ResponseEntity(friendsService.countInvitations(username), HttpStatus.OK);
    }

    @RequestMapping(value = "/sendInvitation", method = RequestMethod.PUT)
    public ResponseEntity sendInvitation(@RequestParam("from") String from, @RequestParam("to") String to) {
        Friends friends = friendsService.sendInvitation(from, to);

        if(friends != null)
            return new ResponseEntity(friends, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/undoInvitation", method = RequestMethod.PUT)
    public ResponseEntity undoInvitation(@RequestParam("from") String from, @RequestParam("to") String to) {
        Friends friends = friendsService.cancelInvitation(from, to);

        if(friends != null)
            return new ResponseEntity(friends, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/cancelInvitation", method = RequestMethod.PUT)
    public ResponseEntity cancelInvitation(@RequestParam("from") String from, @RequestParam("to") String to) {
        Friends friends = friendsService.cancelInvitation(from, to);

        if(friends != null)
            return new ResponseEntity(friends, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/acceptInvitation", method = RequestMethod.PUT)
    public ResponseEntity acceptInvitation(@RequestParam("from") String from, @RequestParam("to") String to) {
        Friends friends = friendsService.acceptInvitation(from, to);

        if(friends != null)
            return new ResponseEntity(friends, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/removeFriend", method = RequestMethod.PUT)
    public ResponseEntity removeFriend(@RequestParam("who") String who, @RequestParam("from") String from) {
        Friends friends = friendsService.removeFriend(who, from);

        if(friends != null)
            return new ResponseEntity(friends, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/getFriends", method = RequestMethod.GET)
    public ResponseEntity getFriends(@RequestParam("username") String username) {
        return new ResponseEntity(friendsService.getFriends(username), HttpStatus.OK);
    }
}
