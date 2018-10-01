package pl.datingSite.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.datingSite.model.messages.Conversation;
import pl.datingSite.model.messages.MessageHelper;
import pl.datingSite.services.MessageService;

import java.util.Set;

@RestController
@RequestMapping("/messages")
public class MessageRestController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/getConversation", method = RequestMethod.GET)
    public ResponseEntity getConversations(@RequestParam("username") String username) {
        Set<Conversation> conversations = messageService.getConversations(username);

        if(conversations != null)
            return new ResponseEntity(conversations, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/newMessage", method = RequestMethod.PUT)
    public ResponseEntity newMessage(@RequestBody MessageHelper helper) {
        Set<Conversation> conversations = messageService.newMessage(helper);
        if(conversations != null)
            return new ResponseEntity(conversations, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/readConversation", method = RequestMethod.PUT)
    public ResponseEntity readConversation(@RequestParam("whose") String whose, @RequestParam("fromWho") String fromWho) {
        messageService.readConversation(whose, fromWho);
        return new ResponseEntity(HttpStatus.OK);
    }

}
