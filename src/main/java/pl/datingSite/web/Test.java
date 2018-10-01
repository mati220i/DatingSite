package pl.datingSite.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Test {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity test2() {
        return new ResponseEntity("OK", HttpStatus.OK);
    }

}
