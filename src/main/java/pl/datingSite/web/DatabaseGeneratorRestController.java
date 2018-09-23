package pl.datingSite.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.datingSite.model.City;
import pl.datingSite.services.DatabaseGeneratorService;

@RestController
@RequestMapping(value = "/databaseGenerator")
public class DatabaseGeneratorRestController {

    @Autowired
    private DatabaseGeneratorService databaseGeneratorService;

    @RequestMapping(value = "/generatePrepare", method = RequestMethod.POST)
    public ResponseEntity generate(@RequestBody City city) {
        databaseGeneratorService.generatePrepare(city);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public ResponseEntity generate(@RequestParam("quantity") Integer quantity) {
        databaseGeneratorService.generate(quantity);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public ResponseEntity generate(@RequestParam("quantity") Integer quantity, @RequestBody City city) {
        databaseGeneratorService.generate(quantity, true, city);
        return new ResponseEntity(HttpStatus.OK);
    }

}
