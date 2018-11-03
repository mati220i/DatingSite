package pl.datingSite.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.datingSite.model.messages.Conversation;
import pl.datingSite.model.messages.MessageHelper;
import pl.datingSite.services.MessageService;

import javax.persistence.NoResultException;
import java.util.Set;

@RestController
@RequestMapping("/messages")
public class MessageRestController {

    final static Logger logger = Logger.getLogger(MessageRestController.class);

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/getConversation", method = RequestMethod.GET)
    public ResponseEntity getConversations(@RequestParam("username") String username) {
        try {
            Set<Conversation> conversations = messageService.getConversations(username);
            return new ResponseEntity(conversations, HttpStatus.OK);
        } catch (NoResultException e) {
            logger.warn(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/newMessage", method = RequestMethod.PUT)
    public ResponseEntity newMessage(@RequestBody MessageHelper helper) {
        try {
            Set<Conversation> conversations = messageService.newMessage(helper);
            return new ResponseEntity(conversations, HttpStatus.OK);
        } catch (NoResultException e) {
            logger.warn(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/readConversation", method = RequestMethod.PUT)
    public ResponseEntity readConversation(@RequestParam("whose") String whose, @RequestParam("fromWho") String fromWho) {
        messageService.readConversation(whose, fromWho);
        return new ResponseEntity(HttpStatus.OK);
    }

}
