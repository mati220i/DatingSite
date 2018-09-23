package pl.datingSite.web;

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

    @Autowired
    private ActivationCodeService activationCodeService;

    @RequestMapping(value = "/addCode", method = RequestMethod.PUT)
    public void addCode(@RequestBody ActivationCode activationCode) {
        activationCodeService.addCode(activationCode);
    }

    @RequestMapping(value = "/deleteCode", method = RequestMethod.DELETE)
    public void deleteCode(@RequestBody ActivationCode activationCode) {
        activationCodeService.deleteCode(activationCode);
    }

    @RequestMapping(value = "/checkCode", method = RequestMethod.POST)
    public ResponseEntity checkCode(@RequestBody ActivationCode activationCode) {
        if(activationCodeService.checkCode(activationCode))
            return new ResponseEntity(true, HttpStatus.OK);
        else
            return new ResponseEntity(false, HttpStatus.NOT_FOUND);
    }

}
