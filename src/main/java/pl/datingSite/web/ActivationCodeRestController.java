package pl.datingSite.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.datingSite.model.ActivationCode;
import pl.datingSite.services.ActivationCodeService;

@RestController
@RequestMapping(value = "/activationCode")
public class ActivationCodeRestController {

    final static Logger logger = Logger.getLogger(ActivationCodeRestController.class);

    @Autowired
    private ActivationCodeService activationCodeService;

    @RequestMapping(value = "/addCode", method = RequestMethod.PUT)
    public ResponseEntity addCode(@RequestBody ActivationCode activationCode) {
        activationCodeService.addCode(activationCode);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteCode", method = RequestMethod.DELETE)
    public ResponseEntity deleteCode(@RequestBody ActivationCode activationCode) {
        activationCodeService.deleteCode(activationCode);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/checkCode", method = RequestMethod.POST)
    public ResponseEntity checkCode(@RequestBody ActivationCode activationCode) {
        if(activationCodeService.checkCode(activationCode))
            return new ResponseEntity(true, HttpStatus.OK);
        else {
            logger.warn("Activation Code not found");
            return new ResponseEntity(false, HttpStatus.NOT_FOUND);
        }
    }

}
