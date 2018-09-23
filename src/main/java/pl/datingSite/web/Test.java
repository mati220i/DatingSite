package pl.datingSite.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.datingSite.model.Notification;
import pl.datingSite.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class Test {

//    @RequestMapping(value = "/test", method = RequestMethod.POST)
//    public ResponseEntity test(@RequestBody User usr) {
//        System.out.println(usr);
//        return new ResponseEntity("Chuj", HttpStatus.OK);
//    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity test2() {
        return new ResponseEntity("OK", HttpStatus.OK);
    }

}
